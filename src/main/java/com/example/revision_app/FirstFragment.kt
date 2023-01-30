package com.example.revision_app
import java.sql.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.revision_app.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.randomButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }
        view.findViewById<Button>(R.id.submit_button).setOnClickListener{
            val myToast = Toast.makeText(context, getString(R.string.toastPopUp), Toast.LENGTH_SHORT)
            myToast.show()
        }

        /*view.findViewById<Button>(R.id.count_button).setOnClickListener{
            countMe(view)
        }*/


    }


    private fun countMe(view: View){
        // Get text view
        val showCountTextView = view.findViewById<TextView>(R.id.textview_first)
        // Get the value of the text view
        val countString = showCountTextView.text.toString()
        //convert to int and increment
        var count = countString.toInt()
        count++
        // Display the new value in the text view
        showCountTextView.text = count.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.revision_app

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.revision_app.databinding.FragmentSecondBinding



/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var question:EditText
    lateinit var answer:EditText
    lateinit var submit:Button
    interface OnClickListener
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //nav back to first frag

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }


        // adding new questions

        var BUT = view?.findViewById<Button>(R.id.newQSubmit)
        BUT?.setOnClickListener {
            Log.e("MYAPP", "HHH")
            var newQ : String = view.findViewById<EditText>(R.id.EnterQuestion).text.toString()
            var newA : String = view.findViewById<EditText>(R.id.EnterAnswer).text.toString()
            val ff = Connection(view.context,null)
            // sending data gathered to Connection class
            ff.newQuestion(newA, newQ)
            //logs to show its been completed used for testing
            Log.e("MYAPP", "HHH")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
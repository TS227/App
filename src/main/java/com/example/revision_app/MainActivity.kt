package com.example.revision_app
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.revision_app.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var userAnswer:EditText
    lateinit var textBox:TextView
    lateinit var submit:Button
    lateinit var set:MenuItem
    lateinit var rand:Button
    lateinit var count:Button
    lateinit var check:Button


    lateinit var question:EditText
    lateinit var answer:EditText
    lateinit var submitQ:Button



    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        var helper = Connection(applicationContext,null)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM QUESTIONS",null)
        if (rs.moveToNext())

            Toast.makeText(applicationContext,rs.getString(1),Toast.LENGTH_LONG).show()

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        //getting question from class Connection
        val gq = Connection(this,null)
        gq.getQuestion()
        val (quest, ans) = gq.getQuestion()
        textBox = findViewById(R.id.showInput)
        textBox.text = quest


        submit = findViewById(R.id.submit_button)
        submit.setOnClickListener{
            //answerCheck
            submit = findViewById(R.id.submit_button)
            userAnswer = findViewById(R.id.answerBox)
            textBox = findViewById(R.id.showInput)
            var stringan : String = findViewById<EditText>(R.id.answerBox).text.toString()
            Log.d("print",stringan)
            Log.e("MYAPP", "HHH")
            val db = Connection(this, null)

            if (stringan == ans){
                textBox.text = "correct"
            }else
                textBox.text = "incorrect"

        }
        count = findViewById(R.id.count_button)
        count.setOnClickListener {
            var helper = Connection(applicationContext, null)
            var db = helper.readableDatabase
            var rs = db.rawQuery("SELECT * FROM QUESTIONS WHERE ID=3", null)
            if (rs.moveToNext())
                Toast.makeText(applicationContext, rs.getString(2), Toast.LENGTH_LONG).show()
        }


/*        val ff = Connection(this, null)
        check = findViewById(R.id.random_button)
        check.setOnClickListener{
            Log.e("MYAPP", "HHH")
            ff.newQuestion()
        }*/


    }

    private fun answerCheck(){
       /* submit = findViewById(R.id.submit_button)
        userAnswer = findViewById(R.id.answerBox)
        textBox = findViewById(R.id.showInput)
        var stringan : String = findViewById<EditText>(R.id.answerBox).text.toString()
        Log.d("print",stringan)
        var correctAnswer = "sui"
        Log.e("MYAPP", "HHH")
        val db = Connection(this, null)



        if (stringan == db.){
            textBox.text = "correct"
        }else
            textBox.text = "incorrect"*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
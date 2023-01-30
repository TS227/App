package com.example.revision_app

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import kotlin.random.Random

class Connection(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context,DATABASE_NAME,factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        //creating DB and adding row
        db?.execSQL("CREATE TABLE QUESTIONS(ID INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION TEXT, ANSWER TEXT)")
        db?.execSQL("INSERT INTO QUESTIONS(QUESTION, ANSWER) VALUES('What does ronaldo say', 'sui')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    //Adding new question to db

    fun newQuestion(newA : String, newQ :String){
        var values = ContentValues()
        values.put(QUESTION_COL, newQ)
        values.put(ANSWER_COL, newA)
        val db = this.writableDatabase
        //inserting to db
        db.insert(TABLE_NAME,null,values)
        db.close()
        Log.e("MYAPP", "HHH")
    }

    fun showAll(){

    }


    //Getting question then sends the data to MainActivity to present to the user
    fun getQuestion(): Pair<String, String>{
        val rand = Random.nextInt(42,50).toString()
        Log.e("MYAPP", rand)
        val db = this.readableDatabase
        var ques = "d"
        var answ ="s"
        //query the db for rand question
        var rs = db.rawQuery("SELECT * FROM QUESTIONS WHERE ID='"+ rand +"'", null)

        if (rs.moveToNext()){
            ques = rs.getString(1).toString() //question
            answ = rs.getString(2).toString() //answer
        }
        //returning the values
        return Pair(ques, answ)
    }




    companion object {
        private val DATABASE_NAME = "revision"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "QUESTIONS"
        val ID_COL = "id"
        val QUESTION_COL = "QUESTION"
        val ANSWER_COL = "ANSWER"
    }
}
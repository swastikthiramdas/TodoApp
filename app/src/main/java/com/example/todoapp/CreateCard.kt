package com.example.todoapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_create_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCard : AppCompatActivity() {

    private lateinit var mydatabase: database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        mydatabase = Room.databaseBuilder(
            applicationContext,database::class.java,"To_Do").build()

         save_save.setOnClickListener{

             if(check()){
                 var tittle = edittext_save.text.toString()
                 var priority = priority_save.text.toString()

                 if(checkpri(priority)) {
                     DataObject.setdata(tittle, priority)
                     GlobalScope.launch {
                         mydatabase.DAO().inserttaskroom(Entity(0,tittle, priority))
                     }
                     intent()
                 }
                 else{
                     priority = "default"
                     DataObject.setdata(tittle, priority)
                     GlobalScope.launch {
                         mydatabase.DAO().inserttaskroom(Entity(0,tittle, priority))

                     }
                     intent()
                 }
             }
             else{
                 Toast.makeText(this,"Enter Valid Text",Toast.LENGTH_LONG).show()
             }
         }

    }

    fun intent(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkpri(pri : String ): Boolean {

        when(pri.toLowerCase()){

            "low"-> return true
            "medium"-> return true
            "high"-> return true
            else -> return false
        }

    }

    private fun check(): Boolean {
        if(edittext_save.text.toString().trim{it <=' '}.isNotEmpty()){
            if (priority_save.text.toString().trim{it <=' '}.isNotEmpty()){
                return true
            }
        }
        return false
    }
}
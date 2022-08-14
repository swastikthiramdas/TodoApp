package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class updateActivity : AppCompatActivity() {

    private lateinit var mydatabase: database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        mydatabase = Room.databaseBuilder(
            applicationContext,database::class.java,"To_Do").build()

        var pos = intent.getIntExtra("id",1)
        if (pos != 1) {

            var tittle = DataObject.getdata(pos).tittle
            var priority = DataObject.getdata(pos).priority

            edittext_update.setText(tittle)
            priority_update.setText(priority)

            update_update.setOnClickListener{
                if (edittext_update.text.toString().trim{it<=' '}.isNotEmpty() &&
                    priority_update.text.toString().trim{it<=' '}.isNotEmpty()) {

                    var tittle1 = edittext_update.text.toString()
                    var priority1 = priority_update.text.toString()
                    DataObject.updatedata(pos, tittle1!!, priority1!!)
                    globalscope(pos,tittle1,priority1)
                    intent()
                }
                else{
                    Toast.makeText(this,"Enter Valid Sentence",Toast.LENGTH_LONG).show()

                }
            }
            delete_update.setOnClickListener{

                DataObject.deletedata(pos)
                GlobalScope.launch {
                    mydatabase.DAO().deletedataroom(
                        Entity(pos+1,tittle!!, priority!!)
                    )
                }
                intent()
            }
        }
    }

    private fun globalscope(pos: Int, tittle1: String, priority1: String) {
        GlobalScope.launch {
            mydatabase.DAO().taskupdateroom(
                Entity(pos+1,tittle1,priority1)
            )
        }
    }


    fun intent(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
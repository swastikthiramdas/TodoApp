package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mydatabase: database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mydatabase = Room.databaseBuilder(
            applicationContext,database::class.java,"To_Do").build()

        main_clear.setOnClickListener{
            DataObject.cleardata()
            GlobalScope.launch {
                mydatabase.DAO().deletealltaskroom()
            }
            recycler()
        }
        add.setOnClickListener{
            val intent = Intent(this,CreateCard::class.java)
            startActivity(intent)
        }
        recycler()
    }

    fun recycler(){
        recyclerview_recy.adapter= Adapter(DataObject.getalldata())
        recyclerview_recy.layoutManager = LinearLayoutManager(this)
    }
}
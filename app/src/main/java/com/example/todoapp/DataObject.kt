package com.example.todoapp

object DataObject {

    var listdata = mutableListOf<cardInfo>()

    fun setdata(tittle : String , priority : String){

        listdata.add(cardInfo(
            tittle,
            priority
        ))
    }

    fun getalldata(): List<cardInfo> {

        return listdata
    }

    fun deletedata(pos: Int){

        listdata.removeAt(pos)
    }

    fun cleardata(){

        listdata.clear()
    }

    fun getdata(pos:Int): cardInfo {

        return listdata[pos]
    }

    fun updatedata(pos:Int,tittle:String,priority: String){
        listdata[pos].tittle = tittle
        listdata[pos].priority = priority
    }
}
package com.example.matt.rng4

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

val DATABASE_NAME = "myDB"
val TABLE_NAME = "Favorites"
val COL_ID = "ID"
val COL_NAME = "name"

class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE "+ TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " VARCHAR(256))"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(favorite : Favorite){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME,favorite.name)
        val result = db.insert(TABLE_NAME,null,cv)

    }

    fun readData() : MutableList<Favorite>{
        val list: MutableList<Favorite> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if (result.moveToFirst()){
            do {
                val favorite = Favorite()
                favorite.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                favorite.name = result.getString(result.getColumnIndex(COL_NAME)).toString()
                list.add(favorite)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun deleteData(ID: Int){
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, COL_ID +" = "+ID.toString(),null)

        db.close()
    }

}
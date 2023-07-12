package com.example.a621.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.a621.models.Contact
import com.example.a621.utils.Constant

class MyDB(context: Context) : SQLiteOpenHelper(context, Constant.DB_NAME,null,Constant.DB_VERSION), DBservis{
    override fun onCreate(db: SQLiteDatabase?) {
        var query = "create table contact (${Constant.ID} integer not null primary key autoincrement unique, ${Constant.NAME} text not null, ${Constant.PHONE_NUMBER} text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists ${Constant.TABLE_NAME}")
        onCreate(db)
    }

    override fun addcontact(contact: Contact) {
        val writableDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.NAME, contact.name)
        contentValues.put(Constant.PHONE_NUMBER, contact.number)
        writableDatabase.insert(Constant.TABLE_NAME,null,contentValues)
        writableDatabase.close()
    }

    override fun deletcontact(contact: Contact) {
        val writableDatabase = this.writableDatabase
        writableDatabase.delete(Constant.TABLE_NAME, "${Constant.ID}=?", arrayOf("${contact.id}"))
    }

    override fun updatecontact(contact: Contact):Int {
        val writableDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.NAME, contact.name)
        contentValues.put(Constant.PHONE_NUMBER, contact.number)
        return writableDatabase.update(Constant.TABLE_NAME, contentValues, "${Constant.ID}=?", arrayOf(contact.id.toString()))

    }

    override fun getallcontact(): ArrayList<Contact> {
        val list = ArrayList<Contact>()
        val query = "select * from ${Constant.TABLE_NAME}"
        val readableDatabase = this.readableDatabase
        val cursor = readableDatabase.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val id =cursor.getInt(0)
                val name =cursor.getString(1)
                val number =cursor.getString(2)
                list.add(Contact(name,number))
            } while (cursor.moveToNext())
        }
        return list
    }


}
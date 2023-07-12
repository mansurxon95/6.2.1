package com.example.a621.db

import com.example.a621.models.Contact

interface DBservis {
    fun addcontact(contact: Contact)
    fun deletcontact(contact: Contact)
    fun updatecontact(contact: Contact):Int
    fun getallcontact():ArrayList<Contact>
}
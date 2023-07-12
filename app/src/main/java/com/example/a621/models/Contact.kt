package com.example.a621.models

class Contact {
    var id:Int? = null
    var name:String? = null
    var number:String? = null

    constructor(name: String?, number: String?) {
        this.name = name
        this.number = number
    }

    constructor(id: Int?) {
        this.id = id
    }


}
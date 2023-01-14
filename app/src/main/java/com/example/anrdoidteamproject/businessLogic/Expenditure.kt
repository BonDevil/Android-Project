package com.example.anrdoidteamproject.businessLogic

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import java.time.LocalDate
import java.util.Date


class Expenditure {


    public var paying_person: String = ""
    public var category: String = "cat1food"
    public var value: Double = 0.0
    public var name: String = ""
    public var date: String = ""
//    public lateinit var persons: List<Int>

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(
        paying_person: String,
        category: String,
        value: Double,
        name: String,
//        persons: List<Int>,
    ) {
        this.paying_person = paying_person
        this.category = category
        this.value = value
        this.name = name
//        this.persons = persons
        this.date = LocalDate.now().toString()
    }



    //testowy
    constructor(
        constructortestowytest:String,
        paying_person: String,
        category: String,
        value: Double,
        name: String,
//        persons: List<Int>,
        date: String
    ) {
        this.paying_person = paying_person
        this.category = category
        this.value = value
        this.name = name
//        this.persons = persons
        this.date = date
    }

    constructor()
}
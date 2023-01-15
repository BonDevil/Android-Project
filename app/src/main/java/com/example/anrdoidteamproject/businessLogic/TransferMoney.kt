package com.example.anrdoidteamproject.businessLogic

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class TransferMoney {
    public var paying_person: String = ""
    public var receiving_person: String = ""
    public var value: Double = 0.0
    public var date: String = ""


    @RequiresApi(Build.VERSION_CODES.O)
    constructor(paying_person: String, receiving_person: String, value: Double) {
        this.paying_person = paying_person
        this.receiving_person = receiving_person
        this.value = value
        this.date = LocalDate.now().toString()
    }

    constructor()

//testowy
    constructor(testowy:String,paying_person: String, receiving_person: String, value: Double,date:String) {
        this.paying_person = paying_person
        this.receiving_person = receiving_person
        this.value = value
        this.date = date
    }


}



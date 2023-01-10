package com.example.anrdoidteamproject.businessLogic

import androidx.compose.ui.input.key.Key.Companion.K

class User{
    public var firstName: String = ""
    public var lastName: String = ""
    public var phoneNumber: String = ""


    constructor(firstName: String, lastName: String, phoneNumber: String){
        this.firstName = firstName
        this.lastName = lastName
        this.phoneNumber = phoneNumber
    }

    constructor()
}
package com.example.anrdoidteamproject.businessLogic

import androidx.compose.ui.input.key.Key.Companion.K

class User{
    public var firstName: String = ""
    public var lastName: String = ""
    public var email: String = ""


    constructor(firstName: String, lastName: String, email: String){
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }

    constructor()
}
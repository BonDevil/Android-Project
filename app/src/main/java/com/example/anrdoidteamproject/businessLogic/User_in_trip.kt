package com.example.anrdoidteamproject.businessLogic

class User_in_trip {
    constructor(id: String) {
        this.id = id
    }
    constructor(id: String, balance: Double) {
        this.id = id
        this.balance=balance
    }
    public var id: String = ""
    public var balance: Double = 0.0

    constructor()
}
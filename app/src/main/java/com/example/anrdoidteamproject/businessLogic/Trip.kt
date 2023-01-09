package com.example.anrdoidteamproject.businessLogic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class Trip {
    public var tripName: String = ""
    public var tripDescription: String = ""
    public var plannedAmount: Int = 0
    public var numberOfDays: Int = 0

    constructor(firstName: String, tripDescription: String, plannedAmount: Int, numberOfDays: Int) {
        this.tripName = tripName
        this.tripDescription = tripDescription
        this.plannedAmount = plannedAmount
        this.numberOfDays = numberOfDays
    }
    constructor()
}

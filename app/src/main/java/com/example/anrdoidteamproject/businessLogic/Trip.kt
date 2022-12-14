package com.example.anrdoidteamproject.businessLogic

class Trip {
    public var tripName: String = ""
    public var tripDescription: String = ""
    public var plannedAmount: Double = 0.0
    public var numberOfDays: Int = 0

    public var cat1foodMax: Double = 0.0
    public var cat2sleepMax: Double = 0.0
    public var cat3drinkMax: Double = 0.0
    public var cat4atractionsMax: Double = 0.0
    public var cat5planeMax: Double = 0.0
    public var cat6transportMax: Double = 0.0

    public var cat1food: Double = 0.0
    public var cat2sleep: Double = 0.0
    public var cat3drink: Double = 0.0
    public var cat4atractions: Double = 0.0
    public var cat5plane: Double = 0.0
    public var cat6transport: Double = 0.0

    public var TotalAmount: Double = 0.0

    public lateinit var expenses: List<Int>

    public lateinit var persons: List<Int>



    constructor(
        tripName: String,
        tripDescription: String,
        plannedAmount: Double,
        numberOfDays: Int,
        cat1foodMax: Double,
        cat2sleepMax: Double,
        cat3drinkMax: Double,
        cat4atractionsMax: Double,
        cat5planeMax: Double,
        cat6transportMax: Double,

        persons: List<Int>,
    ) {
        this.tripName = tripName
        this.tripDescription = tripDescription
        this.plannedAmount = plannedAmount
        this.numberOfDays = numberOfDays
        this.cat1foodMax = cat1foodMax
        this.cat2sleepMax = cat2sleepMax
        this.cat3drinkMax = cat3drinkMax
        this.cat4atractionsMax = cat4atractionsMax
        this.cat5planeMax = cat5planeMax
        this.cat6transportMax = cat6transportMax
        this.persons = persons
    }
}

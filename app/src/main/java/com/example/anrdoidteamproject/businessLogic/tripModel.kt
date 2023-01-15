package com.example.anrdoidteamproject.businessLogic

import com.example.anrdoidteamproject.ui.historySample
import java.lang.Double.min

public var tripID: Int = 0
public var tripName: String = "test"
public var tripDescription: String = ""
public var plannedAmount: Double = 100.0
public var numberOfDays: Int = 1

public var cat1foodMax: Double = 1.0
public var cat2sleepMax: Double = 1.0
public var cat3drinkMax: Double = 1.0
public var cat4atractionsMax: Double = 1.0
public var cat5planeMax: Double = 1.0
public var cat6transportMax: Double = 1.0

public var cat1food: Double = 0.0
public var cat2sleep: Double = 0.0
public var cat3drink: Double = 0.0
public var cat4atractions: Double = 0.0
public var cat5plane: Double = 0.0
public var cat6transport: Double = 0.0

public var TotalAmount: Double = 0.0

public lateinit var persons: List<User_in_trip>

public lateinit var persons2: List<String>
public lateinit var personsUser_In_Trip_inCreate: List<User_in_trip>


public var cat1foodBalance = min((cat1food / cat1foodMax), (1.0)).toFloat()

public var cat2sleepBalance = min((cat2sleep / cat2sleepMax), (1.0)).toFloat()
public var cat3drinkBalance = min((cat3drink / cat3drinkMax), (1.0)).toFloat()
public var cat4atractionsBalance = min((cat4atractions / cat4atractionsMax), (1.0)).toFloat()
public var cat5planeBalance = min((cat5plane / cat5planeMax), (1.0)).toFloat()
public var cat6transportBalance = min((cat6transport / cat6transportMax), (1.0)).toFloat()

public var historyEmptySample = listOf(
    Expenditure(),
)

public var historyReturnSample = listOf(
    TransferMoney(),
)

public var expenses: List<Expenditure> = historyEmptySample

public var historyReturns: List<TransferMoney> = historyReturnSample

//w pozniejszej wersji do zmiany :D
public var today = (plannedAmount / numberOfDays)


public var cat1foodBalanceTotal = min((cat1food / plannedAmount), (1.0)).toFloat()
public var cat2sleepBalanceTotal = min((cat2sleep / plannedAmount), (1.0)).toFloat()
public var cat3drinkBalanceTotal = min((cat3drink / plannedAmount), (1.0)).toFloat()
public var cat4atractionsBalanceTotal = min((cat4atractions / plannedAmount), (1.0)).toFloat()
public var cat5planeBalanceTotal = min((cat5plane / plannedAmount), (1.0)).toFloat()
public var cat6transportBalanceTotal = min((cat6transport / plannedAmount), (1.0)).toFloat()

public var cat1foodBalanceTotalALL = min((cat1food / TotalAmount), (1.0)).toFloat()
public var cat2sleepBalanceTotalALL = min((cat2sleep / TotalAmount), (1.0)).toFloat()
public var cat3drinkBalanceTotalALL = min((cat3drink / TotalAmount), (1.0)).toFloat()
public var cat4atractionsBalanceTotalALL = min((cat4atractions / TotalAmount), (1.0)).toFloat()
public var cat5planeBalanceTotalALL = min((cat5plane / TotalAmount), (1.0)).toFloat()
public var cat6transportBalanceTotalALL = min((cat6transport / TotalAmount), (1.0)).toFloat()


fun transferData(trip:Trip){
    //TODO()
    //to trzeba zmienic
    tripID = 0

    tripName=trip.tripName
    tripDescription= trip.tripDescription
    plannedAmount=trip.plannedAmount
    numberOfDays=trip.numberOfDays
    TotalAmount=trip.TotalAmount

    cat1foodMax=trip.cat1foodMax
    cat2sleepMax=trip.cat2sleepMax
    cat3drinkMax=trip.cat3drinkMax
    cat4atractionsMax=trip.cat4atractionsMax
    cat5planeMax=trip.cat5planeMax
    cat6transportMax=trip.cat6transportMax

    cat1food=trip.cat1food
    cat2sleep=trip.cat2sleep
    cat3drink=trip.cat3drink
    cat4atractions=trip.cat4atractions
    cat5plane=trip.cat5plane
    cat6transport=trip.cat6transport

    cat1foodBalance = min((cat1food / cat1foodMax), (1.0)).toFloat()
    cat2sleepBalance = min((cat2sleep / cat2sleepMax), (1.0)).toFloat()
    cat3drinkBalance = min((cat3drink / cat3drinkMax), (1.0)).toFloat()
    cat4atractionsBalance = min((cat4atractions / cat4atractionsMax), (1.0)).toFloat()
    cat5planeBalance = min((cat5plane / cat5planeMax), (1.0)).toFloat()
    cat6transportBalance = min((cat6transport / cat6transportMax), (1.0)).toFloat()

    today = (plannedAmount / numberOfDays)


    cat1foodBalanceTotal = min((cat1food / plannedAmount), (1.0)).toFloat()
    cat2sleepBalanceTotal = min((cat2sleep / plannedAmount), (1.0)).toFloat()
    cat3drinkBalanceTotal = min((cat3drink / plannedAmount), (1.0)).toFloat()
    cat4atractionsBalanceTotal = min((cat4atractions / plannedAmount), (1.0)).toFloat()
    cat5planeBalanceTotal = min((cat5plane / plannedAmount), (1.0)).toFloat()
    cat6transportBalanceTotal = min((cat6transport / plannedAmount), (1.0)).toFloat()

    cat1foodBalanceTotalALL = min((cat1food / TotalAmount), (1.0)).toFloat()
    cat2sleepBalanceTotalALL = min((cat2sleep / TotalAmount), (1.0)).toFloat()
    cat3drinkBalanceTotalALL = min((cat3drink / TotalAmount), (1.0)).toFloat()
    cat4atractionsBalanceTotalALL = min((cat4atractions / TotalAmount), (1.0)).toFloat()
    cat5planeBalanceTotalALL = min((cat5plane / TotalAmount), (1.0)).toFloat()
    cat6transportBalanceTotalALL = min((cat6transport / TotalAmount), (1.0)).toFloat()




    expenses=trip.expenses

    historyReturns=trip.historyReturns


//TODO()
//    persons=trip.persons
}
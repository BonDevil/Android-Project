package com.example.anrdoidteamproject.businessLogic

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.example.anrdoidteamproject.ui.BarGroup
import java.lang.Double.min
import java.time.LocalDate
import kotlin.math.abs

public var tripID: String? = ""
public var tripName: String = "test"
public var tripDescription: String = ""
public var plannedAmount: Double = 100.0

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
public var maxiii:Double=0.0


public var TotalAmount: Double = 0.0

public lateinit var tripUsers: List<User_in_trip>

public var persons2: ArrayList<String> = ArrayList()
public var personsUser_In_Trip_inCreate: ArrayList<User_in_trip> =ArrayList()


public var cat1foodBalance = min((cat1food / cat1foodMax), (1.0)).toFloat()

public var cat2sleepBalance = min((cat2sleep / cat2sleepMax), (1.0)).toFloat()
public var cat3drinkBalance = min((cat3drink / cat3drinkMax), (1.0)).toFloat()
public var cat4atractionsBalance = min((cat4atractions / cat4atractionsMax), (1.0)).toFloat()
public var cat5planeBalance = min((cat5plane / cat5planeMax), (1.0)).toFloat()
public var cat6transportBalance = min((cat6transport / cat6transportMax), (1.0)).toFloat()


public var expenses: ArrayList<Expenditure> = ArrayList()
public var expensescat1: ArrayList<Expenditure> = ArrayList()
public var expensescat2: ArrayList<Expenditure> = ArrayList()
public var expensescat3: ArrayList<Expenditure> = ArrayList()
public var expensescat4: ArrayList<Expenditure> = ArrayList()
public var expensescat5: ArrayList<Expenditure> = ArrayList()
public var expensescat6: ArrayList<Expenditure> = ArrayList()
public var expensestoday: ArrayList<Expenditure> = ArrayList()


public var expensescattemp: ArrayList<Expenditure> = expensescat1

public var historyReturns: ArrayList<TransferMoney> = ArrayList()

public var today = 0.0



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

public var todayBalance = 0.0f


@RequiresApi(Build.VERSION_CODES.O)
fun transferData(trip:Trip, key:String?){
    tripID = key

    tripName=trip.tripName
    tripDescription= trip.tripDescription
    plannedAmount=trip.plannedAmount
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

    tripUsers=trip.tripUsers

    expensestoday = ArrayList()
    today=0.0
    for(item in expenses){
        if (item.date.toString()== LocalDate.now().toString()){
            today+=item.value
            expensestoday.add(item)
        }

    }


    expensescat1 = ArrayList()
    expensescat2 = ArrayList()
    expensescat3 = ArrayList()
    expensescat4 = ArrayList()
    expensescat5 = ArrayList()
    expensescat6 = ArrayList()
    for(item in expenses){
        when (item.category) {
            0 -> {
                expensescat1.add(item)}
            1 -> {
                expensescat2.add(item)}
            2 -> {
                expensescat3.add(item)}
            3 -> {
                expensescat4.add(item)}
            4 -> {
                expensescat5.add(item)}
            5 -> {
                expensescat6.add(item)}
        }
    }



    if (TotalAmount!=0.0){
        todayBalance= (today/ TotalAmount).toFloat()}




    maxiii= abs(tripUsers[0].balance)
    for (i in tripUsers) {

        if(abs(i.balance) >maxiii) maxiii= abs(i.balance)
    }

}
package bok.artenes.budgetcontrol

import android.app.Application

class BudgetControlApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Repository.initDatabase(this)
    }

}
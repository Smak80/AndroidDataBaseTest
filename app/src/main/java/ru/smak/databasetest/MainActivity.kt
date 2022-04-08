package ru.smak.databasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.smak.databasetest.database.DataBaseHelper
import ru.smak.databasetest.ui.main.MainFragment
import ru.smak.databasetest.ui.main.Person

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Person.format = getString(R.string.format)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
package ru.smak.databasetest.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.smak.databasetest.database.DataBaseHelper

class AddViewModel : ViewModel() {

    var person: Person? = null

    fun save(context: Context) {
        person?.let{
            DataBaseHelper.getInstance(context).save(it)
        }
    }

}
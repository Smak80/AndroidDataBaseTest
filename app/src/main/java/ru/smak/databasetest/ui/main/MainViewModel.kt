package ru.smak.databasetest.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.smak.databasetest.database.DataBaseHelper

class MainViewModel : ViewModel() {
    val data = MutableLiveData<List<Person>>()

    fun update(context: Context){
        val list = DataBaseHelper.getInstance(context).load()
        data.postValue(list)
    }
}
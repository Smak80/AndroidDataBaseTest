package ru.smak.databasetest.ui.main

data class Person(
    val name: String = "",
    val age: Int = 0,
    val salary: Float = 0F,
){
    override fun toString() = String.format(format, name, age, salary)

    companion object {
        var format = "%s, %d, %f"
    }
}

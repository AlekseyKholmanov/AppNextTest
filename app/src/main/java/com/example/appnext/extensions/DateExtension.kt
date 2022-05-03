package com.example.appnext.extensions

//TODO getStrings from resources
fun getDayName(position: Int): String {
    return when (position) {
        1 -> {
            "Mon"
        }
        2 -> {
            "Tue"
        }
        3 -> {
            "Wen"
        }
        4 -> {
            "Thu"
        }
        5 -> {
            "Fri"
        }
        6 -> {
            "Sat"
        }
        7 -> {
            "Sun"
        }
        else -> {
            "Error"
        }
    }
}

//TODO getStrings from resources
fun getMonthName(month: Int): String {
    return when (month) {
        1 -> {
            "Januar"
        }
        2 -> {
            "February"
        }
        3 -> {
            "March"
        }
        4 -> {
            "April"
        }
        5 -> {
            "May"
        }
        6 -> {
            "June"
        }
        7 -> {
            "July"
        }
        8 -> {
            "August"
        }
        9 -> {
            "September"
        }
        10 -> {
            "October"
        }
        11 -> {
            "November"
        }
        12 -> {
            "December"
        }
        else -> {
            "Error"
        }
    }
}
package com.example.prj1114.data

import java.util.*

data class MyTimestamp(
    var year: Int,
    var month: Int,
    var day: Int,
    var hour: Int,
    var minute: Int,
    var second: Int,
) {
    constructor(
        now: Calendar = Calendar.getInstance(),
    ) : this(
        now.get(Calendar.YEAR),
        now.get(Calendar.MONTH),
        now.get(Calendar.DAY_OF_MONTH),
        now.get(Calendar.HOUR_OF_DAY),
        now.get(Calendar.MINUTE),
        now.get(Calendar.SECOND)
    )

    fun asTimeMillis(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(this.year, this.month, this.day, this.hour, this.minute, this.second)
        return calendar.timeInMillis
    }
}
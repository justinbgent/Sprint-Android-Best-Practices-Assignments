package com.schoolwork.daggerguided

import java.util.*

object DateExample {
    private val date: Long = Date().time

    fun getDate(): Long {
        return date
    }
}
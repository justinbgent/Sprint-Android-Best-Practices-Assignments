package com.schoolwork.daggerguided.di

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.schoolwork.daggerguided.DaggerDemoApplication
import com.schoolwork.daggerguided.R
import kotlinx.android.synthetic.main.activity_dagger.*
import java.util.*
import javax.inject.Inject

class DaggerActivity : AppCompatActivity() {

    @Inject
    lateinit var dateExample: DateExampleContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)

        (application as DaggerDemoApplication).dateComponent.inject(this)
        text_view.text = dateExample.getDate().toString()
    }
}

interface DateExampleContract{
    fun getDate(): Long
}

class DateExampleImplementation: DateExampleContract {
    override fun getDate(): Long {
        return date
    }
    private val date: Long = Date().time
}

class DateExampleTest: DateExampleContract {
    override fun getDate(): Long {
        return 1572542294221L
    }
}
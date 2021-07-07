package com.schoolwork.daggerguided

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val example = DateExample
        text_view.text = example.getDate().toString()

        val calendar = Calendar.DAY_OF_YEAR
        text_view.text = calendar.toString()
    }
}

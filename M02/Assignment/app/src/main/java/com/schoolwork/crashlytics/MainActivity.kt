package com.schoolwork.crashlytics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var string: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_null_pointer.setOnClickListener {
            val int: Int? = null
            int!!.plus(6)
        }

        btn_not_initialized.setOnClickListener {
            "Test$string"
        }

        btn_divide_by_0.setOnClickListener {
            val divisionByZero = 6/0
        }

        btn_bad_cast.setOnClickListener {
            var double: Double = 7.3
            val fakeInt: Int = double as Int
        }
    }
}

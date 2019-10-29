package com.schoolwork.crashlytics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.crashlytics.android.Crashlytics
import kotlinx.android.synthetic.main.activity_main.*

fun dropBreadCrumb(className: String, methodName: String, generalInfo: String){
    Crashlytics.log("$className -- $methodName -- $generalInfo")
}

class MainActivity : AppCompatActivity() {
    lateinit var string: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_null_pointer.setOnClickListener {
            dropBreadCrumb("MainActivity", "btn_null_pointer listener", "Unsafe null call")
            val int: Int? = null
            int!!.plus(6)
        }

        btn_not_initialized.setOnClickListener {
            dropBreadCrumb("MainActivity", "btn_not_initialized listener", "Uses uninitialized lateinit")
            "Test$string"
        }

        btn_divide_by_0.setOnClickListener {
            dropBreadCrumb("MainActivity", "btn_divide_by_zero listener", "Divides with 0")
            val divisionByZero = 6/0
        }

        btn_bad_cast.setOnClickListener {
            dropBreadCrumb("MainActivity", "btn_bad_cast listener", "Casts double as Int")
            val double: Double = 7.3
            val fakeInt: Int = double as Int
        }
    }
}
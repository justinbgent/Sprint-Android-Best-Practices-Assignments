package com.schoolwork.crashanalytics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnForceCrash.setOnClickListener {
            dropBreadCrumb("MainActivity", "forceCrashListener", 0L, "Button Click Force Crash")
            throw RuntimeException("This is a forced crash")
        }

        btnOtherActivity.setOnClickListener {
            startActivity(Intent(this, OtherActivity::class.java))
        }
    }
}

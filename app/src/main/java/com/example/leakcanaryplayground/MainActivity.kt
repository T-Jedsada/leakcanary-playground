package com.example.leakcanaryplayground

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val user : UserData by lazy { UserData(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnTest.setOnClickListener { startAsyncTask() }
    }

    @SuppressLint("StaticFieldLeak")
    private fun startAsyncTask() {
        // This async task is an anonymous class and therefore has a hidden reference to the outer
        // class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
        // the activity instance will leak.
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void): Void? {
                // Do some slow work in background
                user.clearContext()
                SystemClock.sleep(20000)
                return null
            }
        }.execute()
    }
}

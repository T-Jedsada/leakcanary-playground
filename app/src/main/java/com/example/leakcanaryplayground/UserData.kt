package com.example.leakcanaryplayground

import android.content.Context

class UserData(private var context: Context? = null) {

    fun clearContext() {
        context = null
    }
}
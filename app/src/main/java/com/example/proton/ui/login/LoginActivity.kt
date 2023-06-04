package com.example.proton.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.proton.R
import com.example.proton.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun clickToRegister(view: View) {
        val intent = Intent(  this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
    }
}
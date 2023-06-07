package com.example.proton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.proton.databinding.ActivityMainBinding
import com.example.proton.ui.login.LoginActivity
import com.example.proton.ui.management.ManagementActivity
import com.example.proton.ui.recommendation.RecommendationActivity
import com.example.proton.ui.register.RegisterActivity
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun clickToManage(view: View) {
        when{
            view.id == binding.cardProduct.id ->{
                val intent = Intent(  this, ManagementActivity::class.java)
                startActivity(intent)
            }

            view.id == binding.cardStore.id ->{
                val intent = Intent(  this, RecommendationActivity::class.java)
                startActivity(intent)
            }
        }

    }
}
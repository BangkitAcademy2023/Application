    package com.example.proton.ui.register

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.example.proton.R
import com.example.proton.databinding.ActivityRegisterBinding

    class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        val fragmentManager = supportFragmentManager
        val identityFragment = IdentityFragment()
        val fragment = fragmentManager.findFragmentByTag(IdentityFragment::class.java.simpleName)
        if (fragment !is IdentityFragment) {
            android.util.Log.d("MyFlexibleFragment", "Fragment Name :" + IdentityFragment::class.java.simpleName)
            fragmentManager
                .beginTransaction()
                .add(R.id.frame_container, identityFragment, IdentityFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}
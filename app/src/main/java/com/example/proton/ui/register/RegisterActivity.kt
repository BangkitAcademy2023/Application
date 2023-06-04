    package com.example.proton.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proton.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
}
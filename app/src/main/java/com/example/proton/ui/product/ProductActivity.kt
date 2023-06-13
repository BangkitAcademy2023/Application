package com.example.proton.ui.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proton.R
import com.example.proton.databinding.ActivityProductBinding
import com.example.proton.ui.register.IdentityFragment

@Suppress("DEPRECATION")
class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val fieldOneFragment = FieldOneFragment()
        val fragment = fragmentManager.findFragmentByTag(FieldOneFragment::class.java.simpleName)
        if (fragment !is FieldOneFragment) {
            android.util.Log.d("MyFlexibleFragment", "Fragment Name :" + FieldOneFragment::class.java.simpleName)
            fragmentManager
                .beginTransaction()
                .add(R.id.frame_container, fieldOneFragment, FieldOneFragment::class.java.simpleName)
                .commit()
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
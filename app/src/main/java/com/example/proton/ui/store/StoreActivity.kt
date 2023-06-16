package com.example.proton.ui.store

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proton.R
import com.example.proton.databinding.ActivityStoreBinding
import com.example.proton.model.StoreModel

@Suppress("DEPRECATION")
class StoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
            title = ""
        }

        val store = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(DATA_STORE, StoreModel::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(DATA_STORE)
        }

        if(store != null){
            binding.photoStore.setImageResource(store.image)
            binding.nameStore.text = store.name
            binding.addressStore.text = store.address
            binding.numberStore.text = store.noHp
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val DATA_STORE = "DATA_STORE"
    }
}
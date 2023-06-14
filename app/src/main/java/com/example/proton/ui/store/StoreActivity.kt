package com.example.proton.ui.store

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proton.R
import com.example.proton.adapter.ListStoreAdapter
import com.example.proton.data.remote.response.DataItem
import com.example.proton.databinding.ActivityStoreBinding
import com.example.proton.model.StoreModel
import com.example.proton.ui.ViewModelFactory
import com.example.proton.ui.managementDetailProduct.ManagementDetailProductActivity
import com.example.proton.ui.recommendation.RecommendationViewModel
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class StoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoreBinding
    private lateinit var storeViewModel: RecommendationViewModel
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
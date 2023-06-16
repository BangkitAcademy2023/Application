package com.example.proton.ui.recommendation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proton.R
import com.example.proton.adapter.ListRecommendationStoreAdapter
import com.example.proton.databinding.ActivityRecommendationBinding
import com.example.proton.ui.ViewModelFactory
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class RecommendationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecommendationBinding
    private lateinit var storeViewModel: RecommendationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storeViewModel = viewModels<RecommendationViewModel> {
            ViewModelFactory.getInstance()
        }.value

        showRecyclerList()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
            title = ""
        }

        lifecycleScope.launch {
            storeViewModel.groupedStore.collect { groupedStore ->
                binding.viewCard.setData(ListRecommendationStoreAdapter(groupedStore.values.flatten()))
            }
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Kosongkan atau biarkan sesuai kebutuhan
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Panggil fungsi search() di ManagementViewModel
                Log.i("search", s.toString())
                storeViewModel.searchStore(s.toString())

            }

            override fun afterTextChanged(s: Editable?) {
                // Kosongkan atau biarkan sesuai kebutuhan
            }
        })


    }

    private fun RecyclerView.setData(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
    }

    private fun showRecyclerList() {
        val layoutManager = LinearLayoutManager(this)
        binding.viewCard.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.viewCard.addItemDecoration(itemDecoration)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
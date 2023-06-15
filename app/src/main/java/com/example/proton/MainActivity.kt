package com.example.proton

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proton.adapter.ListPredictProductAdapter
import com.example.proton.adapter.ListProductAdapter
import com.example.proton.adapter.ListStoreAdapter
import com.example.proton.databinding.ActivityMainBinding
import com.example.proton.model.FakeProductDataSource
import com.example.proton.model.FakeStoreDataSource
import com.example.proton.model.ProductModel
import com.example.proton.model.UserPreferences
import com.example.proton.ui.DataSourceManager
import com.example.proton.ui.login.LoginActivity
import com.example.proton.ui.management.ManagementActivity
import com.example.proton.ui.recommendation.RecommendationActivity


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MainActivity : AppCompatActivity() {

    private lateinit var mainDataSource: MainDataSource
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        mainDataSource = ViewModelProvider(
            this,
            DataSourceManager(UserPreferences.getInstance(dataStore))
        )[MainDataSource::class.java]


        mainDataSource.getUser().observe(this) { user ->
            if (!user.isLogin && user.token == "") {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()

            } else {
                binding = ActivityMainBinding.inflate(layoutInflater)
                setContentView(binding.root)
                showRecyclerList()
                binding.viewCard.setData(ListPredictProductAdapter(FakeProductDataSource.dummyProduct))


                binding.email.text = user.email
            }
        }

    }

    fun logOut(view: View) {
//        mainDataSource.logout()

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

    private fun RecyclerView.setData(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
    }


    private fun showRecyclerList() {
        val layoutManager = LinearLayoutManager(this)
        binding.viewCard.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.viewCard.addItemDecoration(itemDecoration)
    }
}
package com.example.proton.ui.managementDetailProduct

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.proton.R
import com.example.proton.databinding.ActivityManagementDetailProductBinding
import com.example.proton.model.ProductModel
import com.example.proton.ui.recommendation.RecommendationActivity
import com.example.proton.utils.DefaultFormat

@Suppress("DEPRECATION")
class ManagementDetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManagementDetailProductBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagementDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val nameProduct = intent.getStringExtra(NAME_PRODUCT)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
            title = nameProduct
        }

        val product = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(DATA_PRODUCT, ProductModel::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(DATA_PRODUCT)
        }


        if(product != null){
            binding.date.text = DefaultFormat.getFormattedDate()
            binding.totalProfit.text = DefaultFormat.formatRupiah(margin(
                product.price, product.sellingPrice, 0
            ).toLong())

            binding.valueName.text = product.name
            binding.valueCode.text = product.code
            binding.valueStock.text = getString(R.string.value_stock, product.stock.toString())
            binding.valueCatergory.text = product.category
            binding.valueType.text = product.type
            binding.valueExpDate.text = DefaultFormat.dateFormat(product.dateExp)
            binding.valuePrice.text = DefaultFormat.formatRupiah(product.price.toLong())
            binding.valueSellingPrice.text = DefaultFormat.formatRupiah(product.sellingPrice.toLong())
        }

    }

    private fun margin(harga: Int, hargaJual: Int, terjual: Int) : Int{
        return terjual *(hargaJual - harga)

    }

    fun addStore(view: View) {
        val intent = Intent(this, RecommendationActivity::class.java)
        startActivity(intent)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val DATA_PRODUCT = "DATA_PRODUCT"
        const val NAME_PRODUCT = "NAME_PRODUCT"
    }
}
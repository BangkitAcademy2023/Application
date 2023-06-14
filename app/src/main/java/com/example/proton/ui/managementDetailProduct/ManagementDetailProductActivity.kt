package com.example.proton.ui.managementDetailProduct

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proton.R
import com.example.proton.data.remote.response.DataItem
import com.example.proton.databinding.ActivityManagementDetailProductBinding
import com.example.proton.utils.DefaultFormat

@Suppress("DEPRECATION", "INFERRED_TYPE_VARIABLE_INTO_POSSIBLE_EMPTY_INTERSECTION")
class ManagementDetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManagementDetailProductBinding
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
            intent.getParcelableExtra(DATA_PRODUCT, DataItem::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(DATA_PRODUCT)
        }


        if(product != null){
            binding.date.text = DefaultFormat.getFormattedDate()
            binding.totalProfit.text = DefaultFormat.formatRupiah(margin(product.jumlahProduk!!,product.harga!!,product.hargaJual!!,
                product.hargaJual
            ).toLong())

            binding.valueName.text = product.namaProduk
            binding.valueCode.text = product.kodeProduk
            binding.valueStock.text = getString(R.string.value_stock, product.jumlahProduk.toString())
            binding.valueCatergory.text = product.kategori
            binding.valueType.text = product.tipe
            binding.valueExpDate.text = product.expiredDate
            binding.valuePrice.text = DefaultFormat.formatRupiah(product.harga.toLong())
            binding.valueSellingPrice.text = DefaultFormat.formatRupiah(product.hargaJual.toLong())
        }

    }

    private fun margin(stok: Int, harga: Int, hargaJual: Int, terjual: Int) : Int{
        return (stok - terjual)*(hargaJual - harga)

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
package com.example.proton.ui.managementDetailStore

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.proton.R
import com.example.proton.data.remote.Result
import com.example.proton.databinding.ActivityManagementDetailStoreBinding
import com.example.proton.ui.ViewModelFactory
import com.example.proton.ui.custom.CustomArrayAdapter
import com.example.proton.ui.product.ProductActivity
import com.example.proton.utils.DefaultFormat

@Suppress("DEPRECATION")
class ManagementDetailStoreActivity : AppCompatActivity() {

    private lateinit var detailStoreViewModel: DetailStoreViewModel

    private lateinit var binding: ActivityManagementDetailStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagementDetailStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailStoreViewModel = viewModels<DetailStoreViewModel> {
            ViewModelFactory.getInstance()
        }.value

        val nameStore = intent.getStringExtra(NAME_STORE)


        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
            title = nameStore
        }

        binding.date.text = DefaultFormat.getFormattedDate()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun addProduct(view: View) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Nama Produk")


        detailStoreViewModel.getAllProduct()
        detailStoreViewModel.listProduct.observe(this){ result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val data = result.data
                    if(data.data != null){
                        val listProduct = data.data
                        val items = listProduct.map { it?.namaProduk ?: "pilihan" }.toTypedArray()
                        val adapter = CustomArrayAdapter(this, items)


                        val builderView = LayoutInflater.from(this).inflate(R.layout.content_dropdown, null)
                        val spinner = builderView.findViewById<Spinner>(R.id.spinner)
                        spinner.adapter = adapter
                        builder.setView(builderView)


                        builder.setPositiveButton("Simpan") { dialog, _ ->
                            val selectedItem = spinner.selectedItem.toString()
                            dialog.dismiss()
                        }

                        builder.setNegativeButton("Batal") { dialog, _ ->
                            dialog.dismiss()
                        }

                        val dialog = builder.create()
                        dialog.show()

                        val positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
                        positiveButton?.setTextAppearance(R.style.AlertDialogButtonStyle)

                        val negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                        negativeButton?.setTextAppearance(R.style.AlertDialogButtonStyle)

                    }

                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Data Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }



    }

    fun addProductRegular(view: View){
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)

    }

    companion object {
        const val NAME_STORE = "NAME_STORE"
    }
}
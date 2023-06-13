package com.example.proton.ui.managementDetailStore

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.proton.R
import com.example.proton.databinding.ActivityManagementDetailStoreBinding
import com.example.proton.ui.custom.CustomArrayAdapter
import com.example.proton.ui.product.ProductActivity
import com.example.proton.utils.DefaultFormat

@Suppress("DEPRECATION")
class ManagementDetailStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManagementDetailStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagementDetailStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

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


        val items = arrayOf("Pilihan 1", "Pilihan 2", "Pilihan 3")
        val adapter = CustomArrayAdapter(this, items)


        val builderView = LayoutInflater.from(this).inflate(R.layout.content_dropdown, null)
        val spinner = builderView.findViewById<Spinner>(R.id.spinner)
        spinner.adapter = adapter
        builder.setView(builderView)


        builder.setPositiveButton("Simpan") { dialog, which ->
            // Action to be taken when the OK button is clicked
            // For example, get the selected item from the Spinner
            val selectedItem = spinner.selectedItem.toString()
            // Do something with the selected item
            dialog.dismiss()
        }

        builder.setNegativeButton("Batal") { dialog, which ->
            // Action to be taken when the Cancel button is clicked
            // For example, do something or close the dialog
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()

        val positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        positiveButton?.setTextAppearance(R.style.AlertDialogButtonStyle)

        val negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        negativeButton?.setTextAppearance(R.style.AlertDialogButtonStyle)
    }

    fun addProductRegular(view: View){
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)

    }

    companion object {
        const val NAME_STORE = "NAME_STORE"
    }
}
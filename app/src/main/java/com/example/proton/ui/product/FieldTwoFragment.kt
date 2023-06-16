package com.example.proton.ui.product

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.proton.MainActivity
import com.example.proton.R
import com.example.proton.data.remote.Result
import com.example.proton.databinding.FragmentFieldTwoBinding
import com.example.proton.model.ProductRequestBody
import com.example.proton.ui.ViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FieldTwoFragment : Fragment(), View.OnClickListener {


    private val productViewModel: ProductViewModel by viewModels {
        ViewModelFactory.getInstance()
    }
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentFieldTwoBinding? = null
    private val binding get() = _binding!!

    private var datePickerButton: TextInputEditText? = null



    private var _name: String? = null
    private var _code: String? = null
    private var _price: String? = null
    private var _sellingPrice: String? = null
    private var _expDate: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFieldTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAddProduct: Button = view.findViewById(R.id.addProductButton)
        btnAddProduct.setOnClickListener(this)



        datePickerButton = binding.datePickerEdittext

        datePickerButton?.setOnClickListener{
            showDatePickerDialog()

        }

        if (arguments != null) {
            _name = arguments?.getString(NAME_PRODUCT)
            _code = arguments?.getString(CODE_PRODUCT)
            _price = arguments?.getString(PRICE_PRODUCT)
            _sellingPrice = arguments?.getString(SELLING_PRICE_PRODUCT)

        }
    }

    override fun onClick(v: View) {
        if(v.id == binding.addProductButton.id){

            val stock = binding.stockProductEditText.text.toString()
            val category = binding.categoryProductEditText.text.toString()
            val type = binding.typeProductEditText.text.toString()

            when{
                stock.isEmpty() -> {
                    binding.stockProductEditTextLayout.error = "Masukkan stok dulu ya!"
                }
                category.isEmpty() -> {
                    binding.categoryProductEditTextLayout.error = "Masukkan kategori produkmu!"
                }
                type.isEmpty() ->{
                    binding.typeProductEditTextLayout.error = "Masukkan tipe produkmu!"
                }

                _expDate.isNullOrEmpty() -> {
                    binding.datePickerLayout.error = "Masukkan tanggalnya dulu ya!"
                }

                else -> {

                    val requestBody = ProductRequestBody(
                        kodeProduk = _code!!,
                        jumlahProduk = stock.toInt(),
                        namaProduk = _name!!,
                        kategori = category,
                        tipe = type,
                        harga = _price!!.toInt(),
                        hargaJual = _sellingPrice!!.toInt(),
                        expiredDate = _expDate!!,
                        jumlahTerjual = 0
                    )

                    productViewModel.postProduct(requestBody)
                    productViewModel.productPost.observe(this){result ->
                        when (result) {
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                AlertDialog.Builder(requireContext()).apply {
                                    setTitle("Yeah!")
                                    setMessage("Anda berhasil menambahkan $_name ke list produk.")
                                    setPositiveButton("Lanjut") { _, _ ->
                                        val intent = Intent(requireActivity(), MainActivity::class.java)

                                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                        startActivity(intent)
                                        requireActivity().finish()
                                    }
                                    create()
                                    show()
                                }


                            }
                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(requireContext(), "Unggah produk gagal, coba lagi ya!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }


    fun showDatePickerDialog(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)

                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                dateFormat.timeZone = TimeZone.getTimeZone("UTC")

                val dateFormatHint = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDateHint = dateFormatHint.format(selectedDate.time)
                val formattedDate = dateFormat.format(selectedDate.time)
                datePickerButton?.setText(formattedDateHint)
                _expDate = formattedDate
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    companion object {
        var NAME_PRODUCT = "name_product"
        var CODE_PRODUCT = "code_product"
        var PRICE_PRODUCT = "price_product"
        var SELLING_PRICE_PRODUCT = "selling_price_product"

    }

}
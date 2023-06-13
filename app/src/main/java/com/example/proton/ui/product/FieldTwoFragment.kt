package com.example.proton.ui.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.proton.R
import com.example.proton.databinding.FragmentFieldOneBinding
import com.example.proton.databinding.FragmentFieldTwoBinding
import com.example.proton.ui.register.DataFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FieldTwoFragment : Fragment(), View.OnClickListener {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentFieldTwoBinding? = null
    private val binding get() = _binding!!


    private var _name: String? = null
    private var _code: String? = null
    private var _price: String? = null
    private var _sellingPrice: String? = null

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

        if (arguments != null) {
            _name = arguments?.getString(NAME_PRODUCT)
            _code = arguments?.getString(CODE_PRODUCT)
            _price = arguments?.getString(PRICE_PRODUCT)
            _sellingPrice = arguments?.getString(SELLING_PRICE_PRODUCT)

        }
    }

    override fun onClick(v: View) {
        if(v.id == binding.addProductButton.id){
            Log.i("DATA", "INI ADALAH NAMA = $_name")
            Log.i("DATA", "INI ADALAH KODE = $_code")
            Log.i("DATA", "INI ADALAH HARGA = $_price")
            Log.i("DATA", "INI ADALAH HARGA JUAL = $_sellingPrice")

        }


    }

    companion object {
        var NAME_PRODUCT = "name_product"
        var CODE_PRODUCT = "code_product"
        var PRICE_PRODUCT = "price_product"
        var SELLING_PRICE_PRODUCT = "selling_price_product"
    }

}
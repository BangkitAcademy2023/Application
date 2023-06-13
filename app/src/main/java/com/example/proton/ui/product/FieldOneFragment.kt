package com.example.proton.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.proton.R
import com.example.proton.databinding.FragmentFieldOneBinding
import com.example.proton.ui.register.DataFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FieldOneFragment : Fragment(), View.OnClickListener  {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentFieldOneBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentFieldOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnNext: Button = view.findViewById(R.id.nextButton2)
        btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == binding.nextButton2.id) {
            val name = binding.nameProductEditText.text.toString()
            val code = binding.codeProductEditText.text.toString()
            val price = binding.priceProductEditText.text.toString()
            val sellingPrice = binding.sellingPriceProductEditText.text.toString()

            when{
                name.isEmpty() ->{
                    binding.nameProductEditTextLayout.error = "Masukkan Nama Produk"
                }
                code.isEmpty() ->{
                    binding.codeProductEditTextLayout.error = "Masukkan Kode Produk"
                }
                price.isEmpty() ->{
                    binding.priceProductEditTextLayout.error = "Masukkan Harga Produk"
                }
                sellingPrice.isEmpty() ->{
                    binding.sellingPriceProductEditTextLayout.error = "Masukkan Harga Jual Produk"
                }

                else->{
                    val fieldTwoFragment = FieldTwoFragment()
                    val bundle = Bundle()
                    bundle.putString(FieldTwoFragment.NAME_PRODUCT, name)
                    bundle.putString(FieldTwoFragment.CODE_PRODUCT, code)
                    bundle.putString(FieldTwoFragment.PRICE_PRODUCT, price)
                    bundle.putString(FieldTwoFragment.SELLING_PRICE_PRODUCT, sellingPrice)
                    fieldTwoFragment.arguments = bundle
                    val fragmentManager = parentFragmentManager
                    fragmentManager.beginTransaction().apply {
                        replace(R.id.frame_container, fieldTwoFragment, FieldTwoFragment::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }

                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
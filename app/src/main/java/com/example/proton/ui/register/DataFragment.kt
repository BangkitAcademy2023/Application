package com.example.proton.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.proton.R
import com.example.proton.databinding.FragmentDataBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DataFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!

    private var _name: String? = null
    private var _phoneNumber: String? = null
    private var _email: String? = null
    private var _password: String? = null

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
        _binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnNext: Button = view.findViewById(R.id.registerButton)
        btnNext.setOnClickListener(this)

        if (arguments != null) {
            _name = arguments?.getString(NAME)
            _phoneNumber = arguments?.getString(PHONE_NUMBER)
            _email = arguments?.getString(EMAIL)
            _password = arguments?.getString(PASSWORD)

        }
    }

    override fun onClick(v: View) {
        if (v.id == binding.registerButton.id) {
            val nameBusiness = binding.nameBusinessEditText.text.toString()
            val typeBusiness = binding.typeBusinessEditText.text.toString()
            val categoryBusiness = binding.categoryEditText.text.toString()
            val addressBusiness = binding.addressEditText.text.toString()

            when{
                nameBusiness.isEmpty() ->{
                    binding.nameBusinessEditTextLayout.error = "Masukkan Nama Usaha"
                }
                typeBusiness.isEmpty() ->{
                    binding.typeBusinessEditTextLayout.error = "Masukkan Nomor Telephone"
                }
                categoryBusiness.isEmpty() -> {
                    binding.categoryEditTextLayout.error = "Masukkan Email"
                }
                addressBusiness.isEmpty() ->{
                    binding.addressEditTextLayout.error = "Masukkan Password"
                }

                else->{
                    Log.i("DATA", "INI ADALAH NAMA = $_name")
                    Log.i("DATA", "INI ADALAH NOMOR TLP = $_phoneNumber")
                    Log.i("DATA", "INI ADALAH EMAIL = $_email")
                    Log.i("DATA", "INI ADALAH PASSWORD = $_password")

                }

            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var NAME = "name"
        var PHONE_NUMBER = "phone_number"
        var EMAIL = "email"
        var PASSWORD = "password"
    }
}
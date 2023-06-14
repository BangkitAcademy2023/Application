package com.example.proton.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.proton.R
import com.example.proton.databinding.FragmentDataBinding
import com.example.proton.model.RegisterRequestBody
import com.example.proton.ui.ViewModelFactory
import com.example.proton.data.remote.Result


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DataFragment : Fragment(), View.OnClickListener {

    private val registerViewModel: RegisterViewModel by viewModels {
        ViewModelFactory.getInstance()
    }


    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!

    private var _name: String? = null
    private var _phoneNumber: String? = null
    private var _email: String? = null
    private var _password: String? = null
    private var _confirmPassword: String? = null

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
            _confirmPassword = arguments?.getString(CONFIRM_PASSWORD)

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

                    val requestBody = RegisterRequestBody(
                        name = _name!!,
                        email = _email!!,
                        password = _password!!,
                        confirmPassword = _confirmPassword!!,
                    )

                    registerViewModel.postRegister(requestBody)
                    registerViewModel.registerPost.observe(this){result ->
                        when (result) {
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                AlertDialog.Builder(requireContext()).apply {
                                    setTitle("Yeah!")
                                    setMessage("Akunnya sudah jadi nih. Yuk, login!.")
                                    setPositiveButton("Lanjut") { _, _ ->
                                        requireActivity().finish()
                                    }
                                    create()
                                    show()
                                }
                            }
                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(requireContext(), "Register Gagal! Coba lagi yaa!", Toast.LENGTH_SHORT).show()
//                                binding.emailEditTextLayout.error = "Email bermasalah! coba lagi ya!"
                            }
                        }
                    }
                    Log.i("DATA", "INI ADALAH NAMA = $_name")
                    Log.i("DATA", "INI ADALAH NOMOR TLP = $_phoneNumber")
                    Log.i("DATA", "INI ADALAH EMAIL = $_email")
                    Log.i("DATA", "INI ADALAH PASSWORD = $_password")
                    Log.i("DATA", "INI ADALAH CONFIRM PASSWORD = $_confirmPassword")
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
        var CONFIRM_PASSWORD = "confirmPassword"
    }
}
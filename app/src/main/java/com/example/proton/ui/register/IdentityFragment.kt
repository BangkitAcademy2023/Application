package com.example.proton.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.proton.R
import com.example.proton.databinding.FragmentIdentityBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class IdentityFragment : Fragment(), View.OnClickListener {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentIdentityBinding? = null
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
        _binding = FragmentIdentityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnNext: Button = view.findViewById(R.id.nextButton)
        btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == binding.nextButton.id) {
            val name = binding.nameEditText.text.toString()
            val phoneNumber = binding.phoneNumberEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val passwordConfirmation = binding.confirmPasswordEditText.text.toString()

            when{
                name.isEmpty() ->{
                    binding.nameEditTextLayout.error = "Masukkan Nama"
                }
                phoneNumber.isEmpty() ->{
                    binding.phoneNumberEditTextLayout.error = "Masukkan Nomor Telephone"
                }
                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = "Masukkan Email"
                }

                binding.emailEditTextLayout.error?.isNotEmpty() == true -> {
                    binding.emailEditTextLayout.error = "Email tidak sesuai format"
                }

                password.isEmpty() ->{
                    binding.passwordEditTextLayout.error = "Masukkan Password"
                }
                passwordConfirmation.isEmpty() -> {
                    binding.confirmPasswordEditTextLayout.error = "Masukkan Konfirmasi Password"
                }
                password.length < 8 -> {
                    binding.passwordEditTextLayout.error = "Minimal 8 karakter ya!"
                }
                passwordConfirmation != password -> {
                    binding.confirmPasswordEditTextLayout.error = "Konfirmasi Password Salah"
                }

                else->{
                    val dataFragment = DataFragment()
                    val bundle = Bundle()
                    bundle.putString(DataFragment.NAME, name)
                    bundle.putString(DataFragment.PHONE_NUMBER, phoneNumber)
                    bundle.putString(DataFragment.EMAIL, email)
                    bundle.putString(DataFragment.PASSWORD, password)
                    bundle.putString(DataFragment.CONFIRM_PASSWORD, passwordConfirmation)
                    dataFragment.arguments = bundle
                    val fragmentManager = parentFragmentManager
                    fragmentManager.beginTransaction().apply {
                        replace(R.id.frame_container, dataFragment, DataFragment::class.java.simpleName)
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
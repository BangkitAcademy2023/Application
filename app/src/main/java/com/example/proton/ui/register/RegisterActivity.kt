    package com.example.proton.ui.register

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.proton.data.remote.Result
import com.example.proton.databinding.ActivityRegisterBinding
import com.example.proton.model.RegisterRequestBody
import com.example.proton.ui.ViewModelFactory

    class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = viewModels<RegisterViewModel> {
            ViewModelFactory.getInstance()
        }.value

        setupView()
        binding.registerButton.setOnClickListener {
            onClick()
        }

    }

        fun onClick() {

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
                    val requestBody = RegisterRequestBody(
                        name = name,
                        email = email,
                        password = password,
                        confirmPassword = passwordConfirmation,
                    )

                    registerViewModel.postRegister(requestBody)
                    registerViewModel.registerPost.observe(this){result ->
                        when (result) {
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                AlertDialog.Builder(this).apply {
                                    setTitle("Yeah!")
                                    setMessage("Akunnya sudah jadi nih. Yuk, login!.")
                                    setPositiveButton("Lanjut") { _, _ ->
                                        finish()
                                    }
                                    create()
                                    show()
                                }
                            }
                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this, "Register Gagal! Coba lagi yaa!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }


                }

            }

        }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}
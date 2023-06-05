package com.example.proton.ui.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.proton.R
import com.example.proton.databinding.ActivityLoginBinding
import com.example.proton.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
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

    private fun setupViewModel() {
//        loginDataSource = ViewModelProvider(
//            this,
//            DataSourceManager(UserPreferences.getInstance(dataStore))
//        )[LoginDataSource::class.java]
//
//        loginDataSource.getUser().observe(this) { user ->
//            this.user = user
//        }
//        loginViewModel = viewModels<LoginViewModel> {
//            ViewModelFactory.getInstance()
//        }.value
    }

    private fun setupAction() {

        binding.loginButton.setOnClickListener {
            if(binding.passwordEditText.error.isNullOrEmpty()){
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()
                when {
                    email.isEmpty() -> {
                        binding.emailEditTextLayout.error = "Masukkan email"
                    }
                    password.isEmpty() -> {
                        binding.passwordEditTextLayout.error = "Masukkan password"
                    }

                    else -> {
//                        val loginRequestBody = LoginRequestBody(email, password)
//                        loginViewModel.postLogin(loginRequestBody)
//                        loginViewModel.loginPost.observe(this){ result ->
//                            when (result) {
//                                is Result.Loading -> {
//                                    binding.progressBar.visibility = View.VISIBLE
//                                }
//                                is Result.Success -> {
//                                    binding.progressBar.visibility = View.GONE
//                                    val data = result.data
//                                    if(data.loginResult != null){
//                                        val name = data.loginResult.name
//                                        val token = data.loginResult.token
//
//                                        name?.let { it ->
//                                            if (token != null) {
//                                                loginDataSource.saveUser(UserModel(it,email,password, true, token))
//                                            }
//                                        }
//                                        AlertDialog.Builder(this@LoginActivity).apply {
//                                            setTitle("Yeah!")
//                                            setMessage("Anda berhasil login. Yuk langsung share ceritamu!")
//                                            setPositiveButton("Lanjut") { _, _ ->
//                                                val intent = Intent(context, MainActivity::class.java)
//                                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                                                startActivity(intent)
//                                                finish()
//                                            }
//                                            create()
//                                            show()
//                                        }
//
//                                    }
//                                }
//                                is Result.Error -> {
//                                    binding.progressBar.visibility = View.GONE
//                                    Toast.makeText(this@LoginActivity, "Coba cek email dan password dengan benar!", Toast.LENGTH_SHORT).show()
//                                }
//                            }
//                        }
                    }
                }

            }else{
                Toast.makeText(this@LoginActivity, "Coba password dengan benar!", Toast.LENGTH_SHORT).show()

            }

        }
    }

    fun clickToRegister(view: View) {
        val intent = Intent(  this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
    }
}
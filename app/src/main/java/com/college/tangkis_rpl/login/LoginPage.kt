package com.college.tangkis_rpl.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.college.tangkis_rpl.databinding.ActivityLoginBinding
import com.college.tangkis_rpl.register.RegisterControl

class LoginPage : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginControl: LoginControl
    private lateinit var registerControl: RegisterControl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginControl = ViewModelProvider(this)[LoginControl::class.java]
        registerControl = ViewModelProvider(this)[RegisterControl::class.java]
        loginControl.checkLoginStatus(this)
        binding.apply {
            tvRegister.setOnClickListener {
                showRegisterPage()
            }
            btnLogin.setOnClickListener {
                submit()
            }
        }
    }

    private fun showRegisterPage() {
        registerControl.showRegisterPage(this@LoginPage)
    }

    private fun submit() {
        val errorMessage = validate()
        if (errorMessage == "")
            loginControl.getUser(
                nim = binding.edtNim.text.toString(),
                password = binding.edtPassword.text.toString(),
                activity = this
            )
        else showAlert(errorMessage)
    }

    private fun validate(): String {
        val nim = binding.edtNim.text.toString()
        val password = binding.edtPassword.text.toString()
        var errorMessage = ""

        if (nim.isEmpty() || password.isEmpty()) {
            errorMessage = "Data tidak boleh kosong"
        }
        return errorMessage
    }

    fun showAlert(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
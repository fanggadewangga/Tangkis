package com.college.tangkis_rpl.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.college.tangkis_rpl.MainActivity
import com.college.tangkis_rpl.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.checkLoginStatus(this)
        binding.apply {
            tvRegister.setOnClickListener {
               viewModel.showRegisterPage(this@LoginActivity)
            }
            btnLogin.setOnClickListener {
                submit()
            }
        }
    }

    private fun submit() {
        val isValid = validate()
        if (isValid)
            viewModel.getUser(
                nim = binding.edtNim.text.toString(),
                password = binding.edtPassword.text.toString(),
                activity = this
            )
    }

    private fun validate(): Boolean {
        val nim = binding.edtNim.text.toString()
        val password = binding.edtPassword.text.toString()
        var errorMessage = ""

        if (nim.isEmpty() || password.isEmpty()) {
            errorMessage = "Data tidak boleh kosong"
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
        return errorMessage == ""
    }

    fun showAlert(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showHomepage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
package com.college.tangkis_rpl.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.college.tangkis_rpl.MainActivity
import com.college.tangkis_rpl.databinding.ActivityLoginBinding
import com.college.tangkis_rpl.register.RegisterActivity

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
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
            btnLogin.setOnClickListener {
                submit()
            }
           validate()
        }
    }

    private fun submit() {
        viewModel.getUser(
            nim = binding.edtNim.text.toString(),
            password = binding.edtPassword.text.toString(),
            activity = this
        )
    }

    private fun validate() {
        binding.edtNim.apply {
            val text = this.text.toString().trim()
            if (text.isEmpty()) {
                this.error = "Data tidak boleh kosong"

            }
            else {
                binding.btnLogin.isEnabled = false
                this.error = null
            }
        }
        binding.edtPassword.apply {
            val text = this.text.toString().trim()
            if (text.isEmpty()) {
                binding.btnLogin.isEnabled = false
                this.error = "Data tidak boleh kosong"
            }
            else {
                this.error = null
                binding.btnLogin.isEnabled = true
            }
        }
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
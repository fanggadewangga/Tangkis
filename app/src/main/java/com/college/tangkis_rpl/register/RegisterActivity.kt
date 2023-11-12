package com.college.tangkis_rpl.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.college.tangkis_rpl.databinding.ActivityRegisterBinding
import com.college.tangkis_rpl.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.apply {
            tvLogin.setOnClickListener {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            btnRegister.setOnClickListener {
                submit()
            }
            validate()
        }
    }

    private fun submit() {
        viewModel.register(
            binding.edtName.text.toString(),
            binding.edtNim.text.toString(),
            binding.edtPassword.text.toString(),
            this
        )
    }

    private fun validate() {
        binding.edtName.apply {
            val text = this.text.toString().trim()
            if (text.isEmpty()) {
                this.error = "Data tidak boleh kosong"

            }
            else {
                binding.btnRegister.isEnabled = false
                this.error = null
            }
        }
        binding.edtNim.apply {
            val text = this.text.toString().trim()
            if (text.isEmpty()) {
                this.error = "Data tidak boleh kosong"

            }
            else {
                binding.btnRegister.isEnabled = false
                this.error = null
            }
        }
        binding.edtPassword.apply {
            val text = this.text.toString().trim()
            if (text.isEmpty()) {
                binding.btnRegister.isEnabled = false
                this.error = "Data tidak boleh kosong"
            }
            else {
                this.error = null
                binding.btnRegister.isEnabled = true
            }
        }
    }

    fun showErrorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    fun show() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
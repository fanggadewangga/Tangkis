package com.college.tangkis_rpl.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.college.tangkis_rpl.MainActivity
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
        }
    }

    private fun submit() {
        val isValid = validate()
        if (isValid)
            viewModel.register(
                binding.edtName.text.toString(),
                binding.edtNim.text.toString(),
                binding.edtPassword.text.toString(),
                this
            )
    }

    private fun validate(): Boolean {
        val nama = binding.edtName.text.toString()
        val nim = binding.edtNim.text.toString()
        val password = binding.edtPassword.text.toString()
        var errorMessage = ""

        if (nama.isEmpty() || nim.isEmpty() || password.isEmpty()) {
            errorMessage = "Data tidak boleh kosong"
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
        return errorMessage == ""
    }

    fun showErrorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    fun show() {
//        val intent = Intent(this, LoginActivity::class.java)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
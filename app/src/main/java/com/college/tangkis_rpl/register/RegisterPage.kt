package com.college.tangkis_rpl.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.college.tangkis_rpl.databinding.ActivityRegisterBinding
import com.college.tangkis_rpl.login.LoginPage

class RegisterPage : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerControl: RegisterControl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerControl = ViewModelProvider(this)[RegisterControl::class.java]

        binding.apply {
            tvLogin.setOnClickListener {
                val intent = Intent(this@RegisterPage, LoginPage::class.java)
                startActivity(intent)
            }
            btnRegister.setOnClickListener {
                submit()
            }
        }
    }

    private fun submit() {
        val errorMessage = validate()
        if (errorMessage == "")
            registerControl.register(
                binding.edtName.text.toString(),
                binding.edtNim.text.toString(),
                binding.edtPassword.text.toString(),
                this
            )
        else showAlert(errorMessage)
    }

    private fun validate(): String {
        val nama = binding.edtName.text.toString()
        val nim = binding.edtNim.text.toString()
        val password = binding.edtPassword.text.toString()
        var errorMessage = ""

        if (nama.isEmpty() || nim.isEmpty() || password.isEmpty()) {
            errorMessage = "Data tidak boleh kosong"
        }
        return errorMessage
    }

    fun showAlert(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
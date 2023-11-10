package com.college.tangkis_rpl

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.college.tangkis_rpl.databinding.ActivityMainBinding
import com.college.tangkis_rpl.pesan_darurat.PesanDaruratActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = fragmentManager.navController
        binding.bottomNavigation.setupWithNavController(navController)
        binding.fabSos.setOnClickListener {
            sendPesanDarurat()
        }
    }

    private fun sendPesanDarurat() {
        val intent = Intent(this, PesanDaruratActivity::class.java)
        startActivity(intent)
    }
}
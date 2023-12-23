package com.college.tangkis_rpl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.college.tangkis_rpl.databinding.ActivityMainBinding
import com.college.tangkis_rpl.pesan_darurat.PesanDaruratControl
import com.college.tangkis_rpl.pesan_darurat.PesanDaruratPage

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pesanDaruratControl: PesanDaruratControl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pesanDaruratControl = ViewModelProvider(this)[PesanDaruratControl::class.java]
        val fragmentManager =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = fragmentManager.navController
        binding.bottomNavigation.setupWithNavController(navController)
        binding.fabSos.setOnClickListener {
            sendPesanDarurat()
        }
    }

    private fun sendPesanDarurat() {
        val intent = makeIntent(this, PesanDaruratPage::class.java)
        startActivity(intent)
        pesanDaruratControl.showPesanDarurat()
    }

    private fun makeIntent(context: android.content.Context, target: Class<PesanDaruratPage>): Intent {
        return Intent(context, target)
    }
}
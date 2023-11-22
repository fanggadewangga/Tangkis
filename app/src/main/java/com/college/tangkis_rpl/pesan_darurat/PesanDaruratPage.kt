package com.college.tangkis_rpl.pesan_darurat

import android.Manifest
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.college.tangkis_rpl.R
import com.college.tangkis_rpl.databinding.ActivityPesanDaruratBinding

class PesanDaruratPage : AppCompatActivity() {

    private lateinit var binding: ActivityPesanDaruratBinding
    private lateinit var pesanDaruratControl: PesanDaruratControl
    private lateinit var countDownTimer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesanDaruratBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pesanDaruratControl = ViewModelProvider(this)[PesanDaruratControl::class.java]
        showPage()
    }

    fun showPage() {
        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                when {
                    permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                        pesanDaruratControl.initiatePesanDaruratButton(this)
                    }
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                        pesanDaruratControl.initiatePesanDaruratButton(this)
                    }
                }
            }
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.SEND_SMS
            )
        )
    }

    fun activateButton(valid: Boolean) {
        if (valid) {
            binding.apply {
                tvTitle.text = "Kamu dalam bahaya?"
                tvDesc.text = "Tekan tombol SOS maka, pesan darurat akan dikirimkan ke kontak darurat kamu melalui SMS"
                btnSos.isEnabled = true
                btnSos.setImageResource(R.drawable.ic_sos_active_button)
                pesanDaruratTimer.tvTitle.visibility = View.GONE
                pesanDaruratTimer.tvDesc.visibility = View.GONE
                pesanDaruratTimer.desc2.visibility = View.GONE
                pesanDaruratTimer.tvCountdown.visibility = View.GONE
                btnSos.setOnClickListener { startPesanDarurat() }
                btnBatalkan.setOnClickListener { cancelPesanDarurat() }
            }
        } else {
            binding.apply {
                tvTitle.text = "SOS tidak aktif"
                tvDesc.text = "Tombol tidak aktif karena kamu berada di luar area Fakultas Ilmu Komputer Universitas Brawijaya."
                btnSos.isEnabled = false
                btnSos.setImageResource(R.drawable.ic_sos_inactive_button)
                pesanDaruratTimer.tvTitle.visibility = View.GONE
                pesanDaruratTimer.tvDesc.visibility = View.GONE
                pesanDaruratTimer.desc2.visibility = View.GONE
                pesanDaruratTimer.tvCountdown.visibility = View.GONE
            }
        }
    }

    private fun startPesanDarurat() {
        binding.apply {
            tvTitle.visibility = View.GONE
            tvDesc.visibility = View.GONE
            btnSos.visibility = View.GONE
            btnBatalkan.visibility = View.VISIBLE
            pesanDaruratTimer.tvTitle.visibility = View.VISIBLE
            pesanDaruratTimer.tvDesc.visibility = View.VISIBLE
            pesanDaruratTimer.desc2.visibility = View.VISIBLE
            pesanDaruratTimer.tvCountdown.visibility = View.VISIBLE
        }
        startCountdown()
    }

    private fun startCountdown() {
        val countdownMillis: Long = 6000
        countDownTimer = object : CountDownTimer(countdownMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                binding.pesanDaruratTimer.tvCountdown.text = "0$secondsLeft"
            }

            override fun onFinish() {
                pesanDaruratControl.initiatePesanDarurat(this@PesanDaruratPage)
            }
        }
        countDownTimer.start()
    }

    private fun cancelPesanDarurat() {
        pesanDaruratControl.cancelPesanDarurat(this)
    }

    fun show() {
        countDownTimer.cancel()
        binding.apply {
            tvTitle.visibility = View.VISIBLE
            tvDesc.visibility = View.VISIBLE
            btnSos.visibility = View.VISIBLE
            btnBatalkan.visibility = View.GONE
            pesanDaruratTimer.tvTitle.visibility = View.GONE
            pesanDaruratTimer.tvDesc.visibility = View.GONE
            pesanDaruratTimer.desc2.visibility = View.GONE
            pesanDaruratTimer.tvCountdown.visibility = View.GONE
        }
    }
}
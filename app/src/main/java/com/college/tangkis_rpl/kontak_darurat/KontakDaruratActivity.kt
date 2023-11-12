package com.college.tangkis_rpl.kontak_darurat

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.college.tangkis_rpl.R
import com.college.tangkis_rpl.adapter.DaftarKontakDaruratAdapter
import com.college.tangkis_rpl.databinding.ActivityKontakDaruratBinding
import com.college.tangkis_rpl.model.KontakDarurat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton

@Suppress("DEPRECATION")
class KontakDaruratActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKontakDaruratBinding
    private lateinit var daftarKontakDaruratAdapter: DaftarKontakDaruratAdapter
    private lateinit var viewModel: KontakDaruratViewModel
    private lateinit var sheetDialog: BottomSheetDialog
    private lateinit var hapusDialog: Dialog

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupView() {
        binding = ActivityKontakDaruratBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[KontakDaruratViewModel::class.java]
        viewModel.getKontakDarurat(this)
        setContentView(binding.root)

        // deleteKontak()
        daftarKontakDaruratAdapter = DaftarKontakDaruratAdapter { showConfirmationBox(it) }
        binding.rvContact.apply {
            adapter = daftarKontakDaruratAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        binding.btnAddContact.setOnClickListener {
            showKontakPerangkat()
        }
    }

    private fun showConfirmationBox(kontakDarurat: KontakDarurat) {
        hapusDialog = Dialog(this)
        hapusDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        hapusDialog.setCancelable(false)
        hapusDialog.setContentView(R.layout.dialog_hapus_kontak)
        hapusDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnBatal: MaterialButton = hapusDialog.findViewById(R.id.btn_batal)
        val btnHapus: MaterialButton = hapusDialog.findViewById(R.id.btn_hapus)
        val namaKontak: TextView = hapusDialog.findViewById(R.id.tv_contact_name)
        namaKontak.text = kontakDarurat.nama
        btnBatal.setOnClickListener {
             confirm(false, kontakDarurat)
            // confirm = false
//            dismissConfirmationBox()
        }
        btnHapus.setOnClickListener {
            confirm(true, kontakDarurat)
            // confirm = true
//            dismissConfirmationBox()
        }
        hapusDialog.show()
    }

    private fun confirm(confirm: Boolean, kontakDarurat: KontakDarurat) {
        dismissConfirmationBox()
        if (confirm)
            viewModel.deleteKontak(kontakDarurat.nomor, this)
    }

    fun showDaftarKontak(daftarKontak: List<KontakDarurat>) {
        daftarKontakDaruratAdapter.contacts = daftarKontak
        daftarKontakDaruratAdapter.notifyDataSetChanged()
        binding.apply {
            rvContact.visibility = View.VISIBLE
            contactEmpty.ivContactEmpty.visibility = View.GONE
            contactEmpty.tvDescContactEmpty.visibility = View.GONE
        }
    }

    private fun showKontakPerangkat() {
        val dialogView = layoutInflater.inflate(R.layout.contact_bottom_sheet, null)
        sheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        sheetDialog.setContentView(dialogView)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.rv_contact)
        recyclerView.apply {
            adapter = daftarKontakDaruratAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        sheetDialog.show()
        val bottomSheetBehavior = sheetDialog.behavior
    }

    private fun dismissConfirmationBox() {
        hapusDialog.dismiss()
    }

    fun showEmpty() {
        binding.apply {
            rvContact.visibility = View.GONE
            contactEmpty.ivContactEmpty.visibility = View.VISIBLE
            contactEmpty.tvDescContactEmpty.visibility = View.VISIBLE
        }
    }

    fun showAlert() {
        Toast.makeText(this, "Gagal menghapus kontak darurat", Toast.LENGTH_SHORT).show()
    }

    fun showUpdate() {
        viewModel.getKontakDarurat(this)
    }
}
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
import com.college.tangkis_rpl.adapter.DaftarKontakPerangkatAdapter
import com.college.tangkis_rpl.databinding.ActivityKontakDaruratBinding
import com.college.tangkis_rpl.model.KontakDarurat
import com.college.tangkis_rpl.model.KontakPerangkat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton

@Suppress("DEPRECATION")
class KontakDaruratPage : AppCompatActivity() {

    private lateinit var binding: ActivityKontakDaruratBinding
    private lateinit var daftarKontakDaruratAdapter: DaftarKontakDaruratAdapter
    private lateinit var daftarKontakPerangkatAdapter: DaftarKontakPerangkatAdapter
    private lateinit var viewModel: KontakDaruratControl
    private lateinit var sheetDialog: BottomSheetDialog
    private lateinit var hapusDialog: Dialog

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKontakDaruratBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[KontakDaruratControl::class.java]
        viewModel.getKontakDarurat(this)
        setContentView(binding.root)

        // deleteKontak()
        daftarKontakDaruratAdapter = DaftarKontakDaruratAdapter { showConfirmationBox(it) }
        binding.rvContact.apply {
            adapter = daftarKontakDaruratAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        // tambahKontak
        daftarKontakPerangkatAdapter = DaftarKontakPerangkatAdapter{ pilihKontak(it) }
        binding.btnAddContact.setOnClickListener {
            showDaftarKontakPerangkat()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
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
        namaKontak.text = kontakDarurat.getNama()
        btnBatal.setOnClickListener {
             confirm(false, kontakDarurat)
        }
        btnHapus.setOnClickListener {
            confirm(true, kontakDarurat)
        }
        hapusDialog.show()
    }

    private fun confirm(confirm: Boolean, kontakDarurat: KontakDarurat) {
        dismissConfirmationBox()
        if (confirm)
            viewModel.deleteKontak(kontakDarurat.getNomor(), this)
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

    private fun showDaftarKontakPerangkat() {
        viewModel.getDaftarKontakPerangkat(this)
    }

    fun showDaftarKontakPerangkat(daftarKontakPerangkat: List<KontakPerangkat>) {
        daftarKontakPerangkatAdapter.daftarKontakPerangkat = daftarKontakPerangkat
        val dialogView = layoutInflater.inflate(R.layout.contact_bottom_sheet, null)
        sheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        sheetDialog.setContentView(dialogView)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.rv_contact)
        recyclerView.apply {
            adapter = daftarKontakPerangkatAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        sheetDialog.show()
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
        daftarKontakDaruratAdapter.notifyDataSetChanged()
    }

    private fun pilihKontak(kontakPerangkat: KontakPerangkat) {
        viewModel.pilihKontak(kontakPerangkat.getNama(), kontakPerangkat.getNomor(), this)
        sheetDialog.dismiss()
    }

    fun showErrorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
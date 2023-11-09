package com.college.tangkis_rpl.contact

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.college.tangkis_rpl.R
import com.college.tangkis_rpl.adapter.KontakDaruratAdapter
import com.college.tangkis_rpl.databinding.ActivityContactBinding
import com.college.tangkis_rpl.model.KontakDarurat
import com.google.android.material.bottomsheet.BottomSheetDialog

@Suppress("DEPRECATION")
class KontakDaruratActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactBinding
    private lateinit var kontakDaruratAdapter: KontakDaruratAdapter
    private lateinit var viewModel: KontakDaruratViewModel
    private lateinit var dialog: BottomSheetDialog

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun showConfirmationBox() {

    }

    private fun dismissConfirmationBox() {}
    private fun confirm(confirm: Boolean) {}
    fun showDaftarKontak(daftarKontak: List<KontakDarurat>) {
        kontakDaruratAdapter.contacts = daftarKontak
        kontakDaruratAdapter.notifyDataSetChanged()
        binding.apply {
            rvContact.visibility = View.VISIBLE
            contactEmpty.ivContactEmpty.visibility = View.GONE
            contactEmpty.tvDescContactEmpty.visibility = View.GONE
        }
    }

    fun showEmpty() {
        binding.apply {
            rvContact.visibility = View.GONE
            contactEmpty.ivContactEmpty.visibility = View.VISIBLE
            contactEmpty.tvDescContactEmpty.visibility = View.VISIBLE
        }
    }

    private fun showAlert() {}
    private fun showUpdate() {}

    private fun setupView() {
        binding = ActivityContactBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[KontakDaruratViewModel::class.java]
        viewModel.getKontakDarurat(this)
        setContentView(binding.root)
        kontakDaruratAdapter = KontakDaruratAdapter {}
        binding.rvContact.apply {
            adapter = kontakDaruratAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        binding.btnAddContact.setOnClickListener {
            showKontakPerangkat()
        }
    }

    private fun showKontakPerangkat() {
        val dialogView = layoutInflater.inflate(R.layout.contact_bottom_sheet, null)
        dialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.rv_contact)
        recyclerView.apply {
            adapter = kontakDaruratAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        dialog.show()
        val bottomSheetBehavior = dialog.behavior
    }
}
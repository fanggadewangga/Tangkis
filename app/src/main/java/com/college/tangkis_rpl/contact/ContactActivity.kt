package com.college.tangkis_rpl.contact

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.college.tangkis_rpl.R
import com.college.tangkis_rpl.adapter.ContactAdapter
import com.college.tangkis_rpl.databinding.ActivityContactBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

@Suppress("DEPRECATION")
class ContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactBinding
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var viewModel: ContactViewModel
    private lateinit var dialog: BottomSheetDialog

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        setContentView(binding.root)

        contactAdapter = ContactAdapter {}

        binding.rvContact.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        binding.btnAddContact.setOnClickListener {
            showDeviceContact()
        }

        viewModel.contactLiveData.observe(this) { contacts ->
            contactAdapter.contacts = contacts
            contactAdapter.notifyDataSetChanged()
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Kontak Darurat"
            elevation=0f
        }

        supportActionBar!!.apply {
            setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.md_theme_light_primary)))
            titleColor = R.color.white
        }
    }

    private fun showDeviceContact() {
        val dialogView = layoutInflater.inflate(R.layout.contact_bottom_sheet, null)
        dialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.rv_contact)
        recyclerView.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        dialog.show()
        val bottomSheetBehavior = dialog.behavior
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
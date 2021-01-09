package com.example.mycollege

import android.view.View
import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.hierfragment.*

class HireMentor :Fragment(R.layout.hierfragment) {
    override fun onStart() {
        super.onStart()
        // spinner items
        spMentorTypes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        btnPay.setOnClickListener {
            tvPaytm.isVisible =true
            tvGPay.isVisible=true
            tvPhonePay.isVisible=true
        }
    }
}
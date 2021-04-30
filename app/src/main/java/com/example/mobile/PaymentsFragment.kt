package com.example.mobile

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.payments_layout.*
import java.security.AccessController.getContext

class PaymentsFragment : Fragment()
{
    lateinit var recyclerPayments : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.payments_layout, container, false)
        recyclerPayments = view.findViewById(R.id.recyclerPayments)
        recyclerPayments.setHasFixedSize(true)
        recyclerPayments.layoutManager = LinearLayoutManager(view.context)
        recyclerPayments.adapter = MyAdapter()
        return view
    }
}
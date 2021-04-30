package com.example.mobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile.MainActivity.Companion.paymentsList
import kotlinx.android.synthetic.main.payment_month.view.*

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{

    class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val paymentRow = inflater.inflate(R.layout.payment_month, parent, false)
        return MyViewHolder(paymentRow)
    }

    override fun getItemCount(): Int {
        return paymentsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val paymentMonth = holder.view.paymentMonth
        paymentMonth.text = paymentsList[position]
    }
}
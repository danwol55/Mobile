package com.example.mobile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action.equals("android.intent.action.ACTION_POWER_CONNECTED"))
        {
            Toast.makeText(context, "Charger connected", Toast.LENGTH_SHORT).show()
        }
        if(intent?.action.equals("android.intent.action.ACTION_POWER_DISCONNECTED"))
        {
            Toast.makeText(context, "Charger disconnected", Toast.LENGTH_SHORT).show()
        }
    }
}
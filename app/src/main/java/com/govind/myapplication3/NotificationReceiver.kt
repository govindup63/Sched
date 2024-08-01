package com.govind.myapplication3

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title")
        val message = intent.getStringExtra("message")
        if (title != null && message != null) {
            val notificationHelper = NotificationHelper(context)
            notificationHelper.sendNotification(title, message)
        }
    }
}

package com.example.listener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class UserPresentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("mylog", "ЛОВИМ! from manifest UserPresentReceiver ${intent?.action}")
        if (intent?.action == Intent.ACTION_USER_PRESENT) {
            // Ваш код для реакции на разблокировку экрана
            Toast.makeText(context, "Экран разблокирован! UserPresentReceiver", Toast.LENGTH_SHORT)
                .show()
            Log.e("mylog", "Экран разблокирован! UserPresentReceiver${intent.action}")

            val workRequest = OneTimeWorkRequestBuilder<UserPresentWorker>().build()
            WorkManager.getInstance(context!!).enqueue(workRequest)
        }
    }
}


class DynamicReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("mylog", "ЛОВИМ! from DynamicReceiver ${intent?.action}")
        if (intent?.action == Intent.ACTION_USER_PRESENT) {
            // Ваш код для реакции на разблокировку экрана
            Toast.makeText(context, "Экран разблокирован! DynamicReceiver", Toast.LENGTH_SHORT)
                .show()
            Log.e("mylog", "Экран разблокирован! DynamicReceiver ${intent.action}")

            val workRequest = OneTimeWorkRequestBuilder<UserPresentWorker>().build()
            WorkManager.getInstance(context!!).enqueue(workRequest)
        }
    }
}


class ServiceReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("mylog", "ЛОВИМ! from ServiceReceiver ${intent?.action}")
        if (intent?.action == Intent.ACTION_USER_PRESENT) {
            // Ваш код для реакции на разблокировку экрана
            Toast.makeText(context, "Экран разблокирован! ServiceReceiver", Toast.LENGTH_SHORT)
                .show()
            Log.e("mylog", "Экран разблокирован! ServiceReceiver ${intent.action}")

            val workRequest = OneTimeWorkRequestBuilder<UserPresentWorker>().build()
            WorkManager.getInstance(context!!).enqueue(workRequest)
        }

    }
}



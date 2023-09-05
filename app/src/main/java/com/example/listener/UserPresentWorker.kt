package com.example.listener

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UserPresentWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        // Здесь ваш фоновый код
        Log.e("mylog UserPresentWorker", "Экран был разблокирован!")
        return Result.success()
    }
}

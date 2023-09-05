package com.example.listener

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class ForegroundService : Service() {

    private val userPresentReceiver = ServiceReceiver()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_USER_PRESENT)

            addAction(Intent.ACTION_USER_UNLOCKED)
            addAction(Intent.ACTION_MANAGED_PROFILE_UNLOCKED)
            addAction("android.intent.action.USER_CONFIRMED_UNLOCK")
            addAction("android.hardware.fingerprint.ACTION_FINGERPRINT_AUTHENTICATED")
            addAction("android.hardware.biometrics.BiometricPrompt")

            addAction(Intent.ACTION_MAIN)
            addAction(Intent.ACTION_VIEW)
            addAction(Intent.ACTION_QUICK_VIEW)
            addAction(Intent.ACTION_ATTACH_DATA)
            addAction(Intent.ACTION_EDIT)
            addAction(Intent.ACTION_INSERT_OR_EDIT)
            addAction(Intent.ACTION_PICK)
            addAction(Intent.ACTION_CREATE_REMINDER)
            addAction(Intent.ACTION_CREATE_SHORTCUT)
            addAction(Intent.ACTION_ALL_APPS)
            addAction(Intent.ACTION_ASSIST)
            addAction(Intent.ACTION_APP_ERROR)
            addAction(Intent.ACTION_APPLICATION_LOCALE_CHANGED)
            addAction(Intent.ACTION_ANSWER)
            addAction(Intent.ACTION_APPLICATION_PREFERENCES)
            addAction(Intent.ACTION_DEFAULT)
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_OKAY)
            addAction(Intent.ACTION_CAMERA_BUTTON)
            addAction(Intent.ACTION_CARRIER_SETUP)
            addAction(Intent.ACTION_CONFIGURATION_CHANGED)
            addAction(Intent.ACTION_BOOT_COMPLETED)
            addAction(Intent.ACTION_DEVICE_STORAGE_LOW)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_REBOOT)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_DATE_CHANGED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
            addAction(Intent.ACTION_DOCK_EVENT)
            addAction(Intent.ACTION_MEDIA_BUTTON)
            addAction(Intent.ACTION_MEDIA_CHECKING)
            addAction(Intent.ACTION_MEDIA_MOUNTED)
            addAction(Intent.ACTION_MEDIA_UNMOUNTED)
            addAction(Intent.ACTION_MEDIA_EJECT)
            addAction(Intent.ACTION_PACKAGE_ADDED)
            addAction(Intent.ACTION_PACKAGE_REMOVED)
            addAction(Intent.ACTION_PACKAGE_CHANGED)
            addAction(Intent.ACTION_NEW_OUTGOING_CALL)
            addAction(Intent.ACTION_CALL_BUTTON)
            addAction(Intent.ACTION_DIAL)
            addAction(Intent.ACTION_CALL)
            addAction(Intent.ACTION_SEARCH)
            addAction(Intent.ACTION_SYNC)
            addAction(Intent.ACTION_WEB_SEARCH)
            addAction(Intent.ACTION_HEADSET_PLUG)
            addAction(Intent.ACTION_INPUT_METHOD_CHANGED)
            addAction(Intent.ACTION_MY_PACKAGE_REPLACED)
            addAction(Intent.ACTION_PACKAGE_FIRST_LAUNCH)
            addAction(Intent.ACTION_PACKAGE_NEEDS_VERIFICATION)
            addAction(Intent.ACTION_PACKAGE_VERIFIED)
            addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE)
            addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE)
            addAction(Intent.ACTION_INSTALL_PACKAGE)
            addAction(Intent.ACTION_UID_REMOVED)
            addAction(Intent.ACTION_UMS_DISCONNECTED)
            addAction(Intent.ACTION_UMS_CONNECTED)
            addAction(Intent.ACTION_USER_FOREGROUND)
            addAction(Intent.ACTION_USER_BACKGROUND)
            addAction(Intent.ACTION_USER_INITIALIZE)
            addAction(Intent.ACTION_SET_WALLPAPER)
            addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
            addAction(Intent.ACTION_DEVICE_STORAGE_OK)
            addAction(Intent.ACTION_DREAMING_STARTED)
            addAction(Intent.ACTION_DREAMING_STOPPED)
            addAction(Intent.ACTION_INSTALL_FAILURE)
            addAction(Intent.ACTION_INSTALL_PACKAGE)
            addAction(Intent.ACTION_LOCALE_CHANGED)
            addAction(Intent.ACTION_LOCKED_BOOT_COMPLETED)
            addAction(Intent.ACTION_MANAGED_PROFILE_ADDED)
            addAction(Intent.ACTION_MANAGED_PROFILE_REMOVED)
            addAction(Intent.ACTION_MANAGE_NETWORK_USAGE)
            addAction(Intent.ACTION_MEDIA_BAD_REMOVAL)
            addAction(Intent.ACTION_MEDIA_NOFS)
            addAction(Intent.ACTION_MEDIA_REMOVED)
            addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            addAction(Intent.ACTION_MEDIA_SCANNER_STARTED)
            addAction(Intent.ACTION_MEDIA_SHARED)
            addAction(Intent.ACTION_MY_PACKAGE_SUSPENDED)
            addAction(Intent.ACTION_MY_PACKAGE_UNSUSPENDED)
            addAction(Intent.ACTION_PACKAGE_DATA_CLEARED)
            addAction(Intent.ACTION_PACKAGE_FULLY_REMOVED)
            addAction(Intent.ACTION_PACKAGE_INSTALL)
            addAction(Intent.ACTION_PACKAGE_REPLACED)
            addAction(Intent.ACTION_PACKAGE_RESTARTED)
            addAction(Intent.ACTION_PACKAGE_VERIFIED)
            addAction(Intent.ACTION_PASTE)
            addAction(Intent.ACTION_PICK_ACTIVITY)
            addAction(Intent.ACTION_PROVIDER_CHANGED)
            addAction(Intent.ACTION_RUN)
            addAction(Intent.ACTION_SEARCH_LONG_PRESS)
            addAction(Intent.ACTION_SEND_MULTIPLE)
            addAction(Intent.ACTION_SENDTO)
            addAction(Intent.ACTION_SHUTDOWN)
            addAction(Intent.ACTION_SYNC)
            addAction(Intent.ACTION_SYSTEM_TUTORIAL)
            addAction(Intent.ACTION_UMS_CONNECTED)
            addAction(Intent.ACTION_UMS_DISCONNECTED)
            addAction(Intent.ACTION_UNINSTALL_PACKAGE)
            addAction(Intent.ACTION_USER_UNLOCKED)
            addAction(Intent.ACTION_VOICE_COMMAND)
            addAction(Intent.ACTION_WALLPAPER_CHANGED)
        }
        registerReceiver(userPresentReceiver, filter)

        val notification = createNotification()
        startForeground(NOTIFICATION_ID, notification)

        return START_STICKY
    }

    override fun onDestroy() {
        unregisterReceiver(userPresentReceiver)
        super.onDestroy()
    }

    private fun createNotification(): Notification {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
            .setContentTitle("MTS Foreground Service")
            .setContentText("MTS Service is running...")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)

        return builder.build()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channelName = "MTS Foreground Service Channel"
        val channelDescription = "Used for showing MTS foreground service notification"
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(CHANNEL_ID, channelName, importance).apply {
            description = channelDescription
        }
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel)
    }


    companion object {
        const val CHANNEL_ID = "mts_foreground_service_channel"
        const val NOTIFICATION_ID = 1
    }
}
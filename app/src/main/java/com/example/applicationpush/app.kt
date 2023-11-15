package com.example.applicationpush

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.os.Build
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import com.yandex.metrica.push.YandexMetricaPush


class app : Application() {


    override fun onCreate() {
        super.onCreate()

        val config: YandexMetricaConfig =
            YandexMetricaConfig.newConfigBuilder("27ff10a6-0605-42dd-ae5c-06f09cbfb8c1")
                .handleFirstActivationAsUpdate(false)
                .withLogs()
//            .withUserProfileID()
                .build()
        YandexMetrica.activate(applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(this)
        YandexMetricaPush.init(applicationContext)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            YandexMetricaPush.getDefaultNotificationChannel()?.apply {


                name = "AppMetricaDefault"
                description = "дефолтный канал из AppMetica"
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                setShowBadge(true)
                importance = NotificationManager.IMPORTANCE_HIGH
                enableLights(true)
                enableVibration(true)


            }
        }

        notificationChannel()


    }


    fun notificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel.
            val name = getString(R.string.voiceappmetrica)
            val descriptionText = getString(R.string.description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel("8889234", name, importance)
            mChannel.description = descriptionText
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                mChannel.isBlockable = true
            }


            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
            val groupId = "my_group_01"
// The user-visible name of the group.
            val groupName = "group"
            notificationManager.createNotificationChannelGroup(
                NotificationChannelGroup(
                    groupId,
                    groupName
                )
            )


        }
    }


}


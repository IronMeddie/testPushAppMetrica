package com.example.applicationpush.push

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.yandex.metrica.push.firebase.MetricaMessagingService


class FirebaseMessagingMasterService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)


        if (MetricaMessagingService.isNotificationRelatedToSDK(message)) {
                MetricaMessagingService().processPush(this, message)
                return
            }

        // Implement the logic for sending messages to other SDKs or handle own pushes.
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token);
        Log.d("checkToken firebase", token)
        MetricaMessagingService().processToken(this, token)

        // Implement the logic for sending tokens to other SDKs.
    }
}
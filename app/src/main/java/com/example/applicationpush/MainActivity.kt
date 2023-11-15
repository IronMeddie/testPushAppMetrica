package com.example.applicationpush

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.applicationpush.ui.screens.mainscreen.MainScreen
import com.example.applicationpush.ui.screens.mainscreen.MainScreenViewModel
import com.example.applicationpush.ui.theme.ApplicationPushTheme
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.profile.Attribute
import com.yandex.metrica.profile.GenderAttribute
import com.yandex.metrica.profile.UserProfile
import com.yandex.metrica.push.YandexMetricaPush


class MainActivity : ComponentActivity() {
    private val viewModel: MainScreenViewModel by viewModels()

    private fun handlePayload(intent: Intent) {
        // Handle your payload.
        val payload = intent.getStringExtra(YandexMetricaPush.EXTRA_PAYLOAD);

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handlePayload(getIntent());

        YandexMetrica.reportEvent("dsadsa")
        YandexMetrica.sendEventsBuffer()

        YandexMetricaPush.setTokenUpdateListener {
            Log.d("appmetrika token key", it.keys.toString())
            Log.d("appmetrika token value", it.values.toString())
        }
        sendProfile()


//        chekNotificationPermission()
        setContent {
            ApplicationPushTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }




//    fun chekNotificationPermission() {
//        val requestPermissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted: Boolean ->
//            if (!isGranted) {
//                Toast.makeText(
//                    this,
//                    "Включите уведомления в настройках приложения",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//
//
//
//        when {
//            ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) == PackageManager.PERMISSION_GRANTED -> {
//                return
//            }
//
//            else -> {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                    requestPermissionLauncher.launch(
//                        Manifest.permission.POST_NOTIFICATIONS
//                    )
//                }
//            }
//        }
//
//    }
}

fun sendProfile(){
    val paramPamPamPupupu = "string"
    val userProfile = UserProfile.newBuilder() // Updating predefined attributes.
        .apply(Attribute.name().withValue("John"))
        .apply(Attribute.customString("test").withValue(paramPamPamPupupu))
        .build()
////// Setting the ProfileID using the method of the YandexMetrica class.
////// Setting the ProfileID using the method of the YandexMetrica class.
//    YandexMetrica.setUserProfileID(null)

    YandexMetrica.reportUserProfile(userProfile)
}




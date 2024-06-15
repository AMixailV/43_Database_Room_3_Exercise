package ru.mixail_akulov.a43_database_room_exercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mixail_akulov.a43_database_room_exercise.screens.splash.SplashFragment
import ru.mixail_akulov.a43_database_room_exercise.screens.splash.SplashViewModel

/**
 * Entry point of the app.
 *
 * Splash activity contains only window background, all other initialization logic is placed to
 * [SplashFragment] and [SplashViewModel].
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Repositories.init(applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

}
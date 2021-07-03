package jp.kaleidot725.githubclient.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import jp.kaleidot725.githubclient.android.di.setupKoin
import jp.kaleidot725.githubclient.android.view.App

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupKoin(applicationContext)
        setContent { App() }
    }
}

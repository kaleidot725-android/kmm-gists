package jp.kaleidot725.githubclient.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import jp.kaleidot725.githubclient.android.di.setupKoin
import jp.kaleidot725.githubclient.android.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupKoin(applicationContext)

        setContent {
            App()
        }
    }
}

@Composable
fun App(viewModel: MainViewModel = getViewModel()) {
    val gists by viewModel.gists.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            text = gists.toString(),
            textAlign = TextAlign.Center
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            onClick = { viewModel.fetchGists() }
        ) {
            Text(text = "UPDATE")
        }
    }
}

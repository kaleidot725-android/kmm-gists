package jp.kaleidot725.githubclient.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import jp.kaleidot725.githubclient.api.apis.GistApi
import jp.kaleidot725.githubclient.api.apis.HttpClientManager
import jp.kaleidot725.githubclient.repository.GistRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val gistApi = GistApi(HttpClientManager(), "https://api.github.com/users")
    private val gistRepository = GistRepository(gistApi)
    private val userName = "kaleidot725"
    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var gistsText by remember { mutableStateOf("") }

            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    text = gistsText,
                    textAlign = TextAlign.Center
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    onClick = {
                        scope.launch {
                            gistsText = gistRepository.getGists(userName).toString()
                        }
                    }
                ) {
                    Text(text = "UPDATE")
                }
            }
        }
    }
}

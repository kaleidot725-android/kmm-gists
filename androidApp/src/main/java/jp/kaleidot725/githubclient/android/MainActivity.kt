package jp.kaleidot725.githubclient.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import jp.kaleidot725.githubclient.api.apis.GistApi
import jp.kaleidot725.githubclient.api.apis.HttpClientManager
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val gistApi = GistApi(HttpClientManager(),"https://api.github.com/users/kaleidot725")
    val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scope.launch {
            val gists = gistApi.getGists()
            val tv: TextView = findViewById(R.id.text_view)
            tv.text = gists.toString()
        }
    }
}

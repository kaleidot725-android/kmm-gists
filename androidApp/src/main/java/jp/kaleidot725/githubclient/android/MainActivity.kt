package jp.kaleidot725.githubclient.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.kaleidot725.githubclient.android.di.setupKoin
import jp.kaleidot725.githubclient.android.view.MainPage
import jp.kaleidot725.githubclient.android.view.page.DetailPage
import jp.kaleidot725.githubclient.android.viewmodel.DetailViewModel
import jp.kaleidot725.githubclient.android.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupKoin(applicationContext)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "Main") {
                composable("Main") {
                    val mainViewModel = getViewModel<MainViewModel>().apply { fetchGists() }
                    MainPage(
                        mainViewModel.gists,
                        onClickedGist = { navController.navigate("Detail") }
                    )
                }

                composable("Detail") {
                    val detailViewModel = getViewModel<DetailViewModel>().apply { fetchFiles() }
                    DetailPage(
                        detailViewModel.files
                    )
                }
            }
        }
    }
}
package jp.kaleidot725.githubclient.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import jp.kaleidot725.githubclient.android.view.GistsPage
import jp.kaleidot725.githubclient.android.view.page.DetailPage
import jp.kaleidot725.githubclient.android.viewmodel.DetailViewModel
import jp.kaleidot725.githubclient.android.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "gists") {
                composable("gists") {
                    val mainViewModel = getViewModel<MainViewModel>().apply { fetchGists() }
                    GistsPage(
                        mainViewModel.gists,
                        onClickedGist = { navController.navigate("gists/${it.id}") },
                        onFetchedGist = { mainViewModel.fetchGists() }
                    )
                }

                composable(
                    "gists/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) {
                    val id = it.arguments?.getString("id") ?: return@composable
                    val detailViewModel = getViewModel<DetailViewModel>().apply { fetchFiles(id) }
                    DetailPage(detailViewModel.files)
                }
            }
        }
    }
}
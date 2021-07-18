package jp.kaleidot725.githubclient.android.di

import android.content.Context
import jp.kaleidot725.githubclient.android.viewmodel.FilePageViewModel
import jp.kaleidot725.githubclient.android.viewmodel.GistPageViewModel
import jp.kaleidot725.githubclient.api.apis.GistApi
import jp.kaleidot725.githubclient.api.apis.HttpClientManager
import jp.kaleidot725.githubclient.repository.GistRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val apiModule = module {
    factory {
        HttpClientManager()
    }

    factory {
        GistApi(get(), "https://api.github.com")
    }
}

val repositoryModule = module {
    factory {
        GistRepository(get())
    }
}

val viewModelModule = module {
    viewModel {
        GistPageViewModel(get())
    }

    viewModel {
        FilePageViewModel(get())
    }
}

fun setupKoin(context: Context) {
    startKoin {
        androidContext(context)
        modules(apiModule, repositoryModule, viewModelModule)
    }
}
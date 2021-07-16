//
//  DataModels.swift
//  iosApp
//
//  Created by kaleidot725 on 2021/07/16.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class AppModule {
    private static let userName : String = "kaleidot725"
    private static let baseUrl : String = "https://api.github.com"
    private static let coroutineScope = CoroutineScopeFactory().create()
    
    static let gistRepositoryNative = GistRepositoryNative(
        gistRepository: GistRepository(gistApi: GistApi(manager: HttpClientManager(), baseUrl: baseUrl))
    )

    static func getGistPageViewModel() -> GistPageViewModel {
        return GistPageViewModel(
            userName: userName, gistRepository: gistRepositoryNative, coroutineScope: coroutineScope
        )
    }
    
    static func getFilePageViewModel(gistId: String) -> FilePageViewModel {
        return FilePageViewModel(
            gistId: gistId, gistRepository: gistRepositoryNative, coroutineScope: coroutineScope
        )
    }
}

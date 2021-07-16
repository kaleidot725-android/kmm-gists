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
    static let userName : String = "kaleidot725"
    static let coroutineScope = CoroutineScopeFactory().create()
    static let gistRepositoryNative = GistRepositoryNative(
        gistRepository: GistRepository(gistApi: GistApi(manager: HttpClientManager(), baseUrl:"https://api.github.com"))
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

let sampleGists = [
    GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
    GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
    GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
    GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
    GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
    GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
    GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
    GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
    GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01")
]

let sampleFiles = [
    FileItem(name: "name", language: "languge", type: "type", content: "cotent"),
    FileItem(name: "name", language: "languge", type: "type", content: "cotent"),
    FileItem(name: "name", language: "languge", type: "type", content: "cotent"),
    FileItem(name: "name", language: "languge", type: "type", content: "cotent"),
    FileItem(name: "name", language: "languge", type: "type", content: "cotent"),
    FileItem(name: "name", language: "languge", type: "type", content: "cotent"),
    FileItem(name: "name", language: "languge", type: "type", content: "cotent"),
    FileItem(name: "name", language: "languge", type: "type", content: "cotent"),
    FileItem(name: "name", language: "languge", type: "type", content: "cotent"),
    FileItem(name: "name", language: "languge", type: "type", content: "cotent"),
    FileItem(name: "name", language: "languge", type: "type", content: "cotent")
]


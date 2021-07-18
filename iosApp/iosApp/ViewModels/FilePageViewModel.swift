//
//  FilePageViewModel.swift
//  iosApp
//
//  Created by kaleidot725 on 2021/07/16.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

class FilePageViewModel: ObservableObject {
    private let gistId: String
    private let gistRepository: GistRepositoryNative
    private let coroutineScope: Kotlinx_coroutines_coreCoroutineScope
    
    @Published var state: UiState = UiState.loading
    @Published var files: [FileItem] = []
    
    init(gistId: String, gistRepository: GistRepositoryNative, coroutineScope: Kotlinx_coroutines_coreCoroutineScope) {
        self.gistId = gistId
        self.gistRepository = gistRepository
        self.coroutineScope = coroutineScope
    }
    
    func fetchFiles() {
        self.state = UiState.loading
        gistRepository.getGistFiles(gistId: gistId)
            .subscribe(
                scope: coroutineScope,
                onSuccess: { array in
                    self.files = (array != nil) ? array!.compactMap { $0 as? FileItem } : []
                    self.state = UiState.success
                },
                onThrow: { throwable in
                    print(throwable.description())
                    self.files = []
                    self.state = UiState.failed
                }
            )
    }
    
    enum UiState {
        case loading
        case success
        case failed
    }
}

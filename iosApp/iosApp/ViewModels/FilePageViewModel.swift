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
    
    @Published var files: [FileItem] = sampleFiles
    
    init(gistId: String, gistRepository: GistRepositoryNative, coroutineScope: Kotlinx_coroutines_coreCoroutineScope) {
        self.gistId = gistId
        self.gistRepository = gistRepository
        self.coroutineScope = coroutineScope
        fetchFiles()
    }
    
    func fetchFiles() {
        gistRepository.getGistFiles(gistId: gistId)
            .subscribe(
            scope: coroutineScope,
            onSuccess: { array in
                if (array != nil) {
                    self.files = array!.compactMap { $0 as? FileItem }
                }
            },
            onThrow: { throwable in
                print(throwable.description())
                self.files = []
            }
        )
    }
}

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
    @Published var files: [FileItem] = sampleFiles
}

//
//  GistPageViewModel.swift
//  iosApp
//
//  Created by kaleidot725 on 2021/07/16.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

class GistPageViewModel: ObservableObject {
    @Published var gists: [GistItem] = sampleGists
}

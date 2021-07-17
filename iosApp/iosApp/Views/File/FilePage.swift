//
//  FileList.swift
//  iosApp
//
//  Created by kaleidot725 on 2021/07/15.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FilePage: View {
    @ObservedObject var viewModel :FilePageViewModel

    var body: some View {
        switch viewModel.state {
        case FilePageViewModel.UiState.loading :
            ProgressView()
        case FilePageViewModel.UiState.success :
            List {
                ForEach(viewModel.files, id: \.name) { file in
                    FileRow(file: file)
                }
            }.navigationTitle("Files")
        case FilePageViewModel.UiState.failed :
            Text("HAS ERROR")
        }
    }
}

struct FileList_Previews: PreviewProvider {
    static var previews: some View {
        let viewModel = AppModule.getFilePageViewModel(gistId: "")
        viewModel.files = sampleFiles
        return FilePage(viewModel: viewModel)
    }
}

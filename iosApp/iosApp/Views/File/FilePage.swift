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
        List {
            ForEach(viewModel.files, id: \.name) { file in
                FileRow(file: file)
            }
        }.navigationTitle("Files")
    }
}

struct FileList_Previews: PreviewProvider {
    static let files: [FileItem] = [
        FileItem(name: "Name", language: "Language", type: "Type", content: "Content abcdefg"),
        FileItem(name: "Name", language: "Language", type: "Type", content: "Content abcdefg"),
        FileItem(name: "Name", language: "Language", type: "Type", content: "Content abcdefg"),
        FileItem(name: "Name", language: "Language", type: "Type", content: "Content abcdefg"),
        FileItem(name: "Name", language: "Language", type: "Type", content: "Content abcdefg"),
        FileItem(name: "Name", language: "Language", type: "Type", content: "Content abcdefg"),
        FileItem(name: "Name", language: "Language", type: "Type", content: "Content abcdefg"),
        FileItem(name: "Name", language: "Language", type: "Type", content: "Content abcdefg"),
        FileItem(name: "Name", language: "Language", type: "Type", content: "Content abcdefg"),
        FileItem(name: "Name", language: "Language", type: "Type", content: "Content abcdefg"),
        FileItem(name: "Name", language: "Language", type: "Type", content: "Content abcdefg"),
        FileItem(name: "Name", language: "Language", type: "Type", content: "Content abcdefg")
    ]
    
    static var previews: some View {
        let viewModel = AppModule.getFilePageViewModel(gistId: "")
        viewModel.files = self.files
        return FilePage(viewModel: viewModel)
    }
}

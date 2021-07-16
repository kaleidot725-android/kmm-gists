//
//  FileList.swift
//  iosApp
//
//  Created by kaleidot725 on 2021/07/15.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FileList: View {
    var files: [FileItem]
    
    var body: some View {
        List {
            ForEach(files, id: \.name) { file in
                FileRow(file: file)
            }
        }.navigationTitle("Files")
    }
}

struct FileList_Previews: PreviewProvider {
    static var previews: some View {
        FileList(files: sampleFiles)
    }
}

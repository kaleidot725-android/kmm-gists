//
//  FileRow.swift
//  iosApp
//
//  Created by kaleidot725 on 2021/07/16.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FileRow: View {
    var file: FileItem
    
    var body: some View {
        VStack {
            Text(file.name)
                .font(.title2)
                .frame(maxWidth: .infinity, alignment: .leading)
            
            Text(file.content)
                .font(.caption)
                .frame(maxWidth: .infinity, alignment: .leading)
        }.padding(8)
    }
}

struct FileRow_Previews: PreviewProvider {
    static var previews: some View {
        FileRow(file: sampleFiles[0])
    }
}

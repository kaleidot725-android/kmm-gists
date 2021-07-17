//
//  GistItem.swift
//  iosApp
//
//  Created by kaleidot725 on 2021/07/15.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GistRow: View {
    var gist: GistItem
    
    var body: some View {
        VStack {
            Text(gist.name)
                .font(.title2)
                .frame(maxWidth: .infinity, alignment: .leading)
            
            Text(gist.createdAt)
                .font(.caption)
                .frame(maxWidth: .infinity, alignment: .leading)
        }.padding(8)
    }
}

struct GistRow_Preview: PreviewProvider {
    static var previews: some View {
        GistRow(gist: sampleGists[0])
    }
}

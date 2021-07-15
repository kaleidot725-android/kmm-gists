//
//  GistList.swift
//  iosApp
//
//  Created by kaleidot725 on 2021/07/15.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GistList: View {
    var gists : [GistItem]

    var body: some View {
        NavigationView {
            List {
                ForEach(gists, id: \.id) { gist in
                    GistRow(gist: gist)
                }
            }.navigationTitle("Gists")
        }
    }
}

struct GistList_Previews: PreviewProvider {
    static var previews: some View {
        GistList(gists: [
            GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
            GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
            GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
            GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
            GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
            GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
            GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
            GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01"),
            GistItem(id: "id", name: "TITLE", createdAt: "2020/01/01")
     ])
    }
}

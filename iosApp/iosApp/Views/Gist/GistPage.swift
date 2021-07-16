//
//  GistList.swift
//  iosApp
//
//  Created by kaleidot725 on 2021/07/15.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GistPage: View {
    @ObservedObject var viewModel : GistPageViewModel

    var body: some View {
        NavigationView {
            List {
                ForEach(viewModel.gists, id: \.id) { gist in
                    NavigationLink(destination: FilePage(viewModel: AppModule.getFilePageViewModel(gistId: gist.id))) {
                        GistRow(gist: gist)
                    }
                }
            }
            .navigationTitle("Gists")
        }
    }
}

struct GistList_Previews: PreviewProvider {
    static let gists : [GistItem] = [
        GistItem(id: "Id", name: "Name", createdAt: "2021-07-17 1:23"),
        GistItem(id: "Id", name: "Name", createdAt: "2021-07-17 1:23"),
        GistItem(id: "Id", name: "Name", createdAt: "2021-07-17 1:23"),
        GistItem(id: "Id", name: "Name", createdAt: "2021-07-17 1:23"),
        GistItem(id: "Id", name: "Name", createdAt: "2021-07-17 1:23"),
        GistItem(id: "Id", name: "Name", createdAt: "2021-07-17 1:23"),
        GistItem(id: "Id", name: "Name", createdAt: "2021-07-17 1:23"),
        GistItem(id: "Id", name: "Name", createdAt: "2021-07-17 1:23"),
        GistItem(id: "Id", name: "Name", createdAt: "2021-07-17 1:23"),
        GistItem(id: "Id", name: "Name", createdAt: "2021-07-17 1:23"),
        GistItem(id: "Id", name: "Name", createdAt: "2021-07-17 1:23")
    ]
    
    static var previews: some View {
        let viewModel = AppModule.getGistPageViewModel()
        viewModel.gists = self.gists
        return GistPage(viewModel: viewModel)
    }
}

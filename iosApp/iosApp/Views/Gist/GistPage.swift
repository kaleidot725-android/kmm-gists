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
                    NavigationLink(destination: FilePage(viewModel: FilePageViewModel())) {
                        GistRow(gist: gist)
                    }
                }
            }
            .navigationTitle("Gists")
        }
    }
}

struct GistList_Previews: PreviewProvider {
    static var previews: some View {
        GistPage(viewModel: GistPageViewModel())
    }
}

//
//  GistList.swift
//  iosApp
//
//  Created by kaleidot725 on 2021/07/15.
//  Copyright © 2021 orgName. All rights reserved.
//b

import SwiftUI
import shared

struct GistPage: View {
    @ObservedObject var viewModel : GistPageViewModel
    
    var body: some View {
        NavigationView {
            switch viewModel.state {
            case GistPageViewModel.UiState.loading :
                ProgressView()
            case GistPageViewModel.UiState.success :
                List {
                    ForEach(viewModel.gists, id: \.id) { gist in
                        NavigationLink(destination: FilePage(viewModel: AppModule.getFilePageViewModel(gistId: gist.id))) {
                            GistRow(gist: gist)
                        }
                    }
                }
                .navigationTitle("Gists")
            case GistPageViewModel.UiState.failed :
                VStack {
                    Text("Loading Error!!").font(.title2)
                    Button(action: { viewModel.fetchGists() }) { Text("Retry") }
                }
            }
        }.onAppear(perform: {
            viewModel.fetchGists()
        })
    }
}

struct GistList_Previews: PreviewProvider {
    static var previews: some View {
        let viewModel = AppModule.getGistPageViewModel()
        viewModel.state = GistPageViewModel.UiState.success
        viewModel.gists = sampleGists
        return GistPage(viewModel: viewModel)
    }
}

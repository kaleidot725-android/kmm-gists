import SwiftUI
import shared

struct ContentView: View {
    
    @State var gists: String = ""
    
    var body: some View {
        GistPage(viewModel: AppModule.getGistPageViewModel())
        
//        Button("UPDATE") {
//            gistRepositoryNative.getGists(userName: userName).subscribe(
//                scope: coroutineScope,
//                onSuccess: { array in
//                    if (array != nil) {
//                       let gists = array!.compactMap { $0 as? GistDto }
//                       let gistTexts = gists.compactMap { $0.description() }
//                       self.gists = gistTexts.joined()
//                    }
//                },
//                onThrow: { throwable in
//                    self.gists = throwable.description()
//                }
//            )
//        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
	ContentView()
	}
}

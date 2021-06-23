import SwiftUI
import shared

struct ContentView: View {
    let gistApi : GistApi = GistApi(manager: HttpClientManager(), baseUrl: "https://api.github.com/users/kaleidot725")

    @State var gists: String = ""
    
    var body: some View {
        Text(gists)
        Button("UPDATE") {
            gistApi.getGists(
                completionHandler: { (array, error) in
                    if (array != nil) {
                        let gists = array!.compactMap { $0 as? GistDto }
                        let gistTexts = gists.compactMap { $0.description() }
                        self.gists = gistTexts.joined()
                    }
                }
            )
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
	ContentView()
	}
}

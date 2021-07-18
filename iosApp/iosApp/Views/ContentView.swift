import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        GistPage(viewModel: AppModule.getGistPageViewModel())
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

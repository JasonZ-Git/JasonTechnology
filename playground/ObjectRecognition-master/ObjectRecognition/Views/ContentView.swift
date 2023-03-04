//
//  ContentView.swift
//  ObjectRecognition
//
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            CameraView()
        }
        .ignoresSafeArea(.all)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

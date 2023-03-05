//
//  ContentView.swift
//  ObjectRecognition
//
//  Created by Jason Zhang on 5/3/2023.
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

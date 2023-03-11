//
//  ContentView.swift
//  TennisDetectorV2
//
//  Created by Jason Zhang on 11/3/2023.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            MyView()
        }.ignoresSafeArea()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

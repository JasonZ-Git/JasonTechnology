//
//  ContentView.swift
//  TennisDetectorV2
//
//  Created by Jason Zhang on 11/3/2023.
//

import SwiftUI

struct ContentView: View {
    @State private var isOn = true
    var body: some View {
        VStack {
            HStack {
                Toggle(isOn: $isOn) {
                }
                .padding(.trailing, 50)
                Text("\(isOn ? "Bounding" : "Route" )")
                        .foregroundColor(isOn ? Color.blue : Color.red)
                        .padding(.trailing, 50)
            }
            if isOn {
                BoundingBoxUIViewAdaptor()
            } else {
                RouteUIViewAdaptor()
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

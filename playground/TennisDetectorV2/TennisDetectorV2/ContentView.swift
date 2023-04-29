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
                .padding(.trailing, 20)
                Text("\(isOn ? "BoundingBox" : "Route" )")
                        .foregroundColor(isOn ? Color.blue : Color.red)
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

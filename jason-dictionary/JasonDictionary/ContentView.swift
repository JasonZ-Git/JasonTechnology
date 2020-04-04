//
//  ContentView.swift
//  JasonDictionary
//
//  Created by Jason Zhang on 25/3/20.
//  Copyright © 2020 Jason Zhang. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            MapView()
                .edgesIgnoringSafeArea(.top)
                .frame(height:300)
            
            CircleImage().offset(y:-130)
                .padding(.bottom, -130)
            
            VStack(alignment: .leading) {
                Text("Botanic Garden")
                        .font(.title)
            
                HStack {
                    Text("Melbourne Park")
                        .font(.subheadline)
                    Spacer()
                    Text("Melbourne")
                }
            }
            .padding()
            
            Spacer()
        }
    }
}

struct ContentView_Previews: PreviewProvider {	
    static var previews: some View {
        ContentView()
    }
}

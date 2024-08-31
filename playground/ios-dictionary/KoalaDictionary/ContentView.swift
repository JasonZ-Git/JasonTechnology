//
//  ContentView.swift
//  KoalaDictionary
//
//  Created by Jason Zhang on 10/7/20.
//  Copyright © 2020 Jason Zhang. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
//        Button(action: {
//            playSound(sound: "ability", type: "mp3")
//        }) {
//            Text("Hello Click")
//          }
        
        TranslationView()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

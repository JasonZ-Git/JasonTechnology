//
//  TranslationView.swift
//  Jason Dictionary
//
//  Created by Jason Zhang on 10/5/20.
//  Copyright © 2020 Jason Zhang. All rights reserved.
//

import SwiftUI

struct TranslationView: View {
    
    var wordDictionary : [String:String] = [:]
    
    @State private var transalationText = ""
    
    @State private var name = ""
    
    init() {
        for current in translationData {
            self.wordDictionary[current.word] = current.translation;
        }
    }

    var body: some View {
        
        return NavigationView{
            VStack(alignment: .leading) {
                
                HStack {
                    TextField(/*@START_MENU_TOKEN@*/"Placeholder"/*@END_MENU_TOKEN@*/, text: $name)
                    Button("Go"){
                        self.transalationText = self.wordDictionary[self.name] ?? "Not Found"
                    }
                }

                Text("\(transalationText)")
                Spacer()
            }
            .navigationBarTitle("Jason Dictionary")
            .padding()
        }
    }
}

struct TranslationView_Previews: PreviewProvider {
    static var previews: some View {
        TranslationView()
    }
}

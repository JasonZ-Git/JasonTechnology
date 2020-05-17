//
//  TranslationView.swift
//  Jason Dictionary
//
//  Created by Jason Zhang on 10/5/20.
//  Copyright © 2020 Jason Zhang. All rights reserved.
//

import SwiftUI

struct TranslationView: View {
    
    @State private var transalationText = ""
    
    @State private var name = ""

    var body: some View {
        
        let binding = Binding<String>(get: {
            self.name
        }, set: {
            self.name = $0
            
            let tempTrans = wordDictionary[self.name.lowercased()] ?? "...";

            self.transalationText = tempTrans.split(separator: ",").joined(separator: "\n")
            
        })
        
        return NavigationView {
            VStack(alignment: .leading) {
                
                HStack {
                    TextField("Word To Go", text: binding)
                }

                Text("\(transalationText)")
                Spacer()
                Text("\(wordDictionary.count) in total")
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

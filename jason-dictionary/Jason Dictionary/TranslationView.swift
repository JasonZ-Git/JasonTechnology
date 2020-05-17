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
        
        return NavigationView {
            VStack(alignment: .leading) {
                
                HStack {
                    TextField("Word To Go", text: $name)
                    Button("Go") {
                        let tempTrans = wordDictionary[self.name.lowercased()] ?? "...";

                        self.transalationText = tempTrans.split(separator: ",").joined(separator: "\n")
                    }
                }

                Text("\(self.transalationText)")
                Spacer()
                Text("\(wordDictionary.count) in total")
               // Text("\(translatonProperty.count)")
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

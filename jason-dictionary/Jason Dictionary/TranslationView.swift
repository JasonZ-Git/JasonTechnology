//
//  TranslationView.swift
//  Jason Dictionary
//
//  Created by Jason Zhang on 10/5/20.
//  Copyright © 2020 Jason Zhang. All rights reserved.
//

import SwiftUI


class TranslationBinding: ObservableObject {
    
    @Published var translation = "" {
        didSet{}
    }
    
    @Published var word = "" {
        didSet {
            
            let tempTrans = wordDictionary[self.word.lowercased()] ?? "...";

            self.translation = tempTrans.split(separator: ",").joined(separator: "\n")
        }
    }
}

struct TranslationView: View {
    
    @ObservedObject var translation = TranslationBinding()

    var body: some View {
        
        return NavigationView {
            VStack(alignment: .leading) {
                
                HStack {
                    TextField("Word To Go", text: $translation.word)
                }

                Text("\(translation.translation)")
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

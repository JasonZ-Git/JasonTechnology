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
            
            let tempTrans = wordDictionary[self.word.lowercased().trimmingCharacters(in: .whitespaces)] ?? "...";

            self.translation = tempTrans.split(separator: ",").joined(separator: "\n")
        }
    }
}

struct TitleBarImage : View {
    var body: some View {
        VStack {
            Image("TitleImageKoala")
        }
        .frame(width: 400, height: 400, alignment: .center)
        .background(Color.clear)
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
            .navigationBarTitle("Koala")
            .padding()
        }
    }
}



struct TranslationView_Previews: PreviewProvider {
    static var previews: some View {
        TranslationView()
    }
}

//
//  TranslationView.swift
//  Jason Dictionary
//
//  Created by Jason Zhang on 10/5/20.
//  Copyright © 2020 Jason Zhang. All rights reserved.
//

import SwiftUI
import AVFoundation

class SoundPlay {
    
    static func play(word : String) {
        var bombSoundEffect: AVAudioPlayer?
        let path = Bundle.main.path(forResource: "Sounds/ability", ofType: "mp3")!
        let url = URL(fileURLWithPath: path)
        do {
            //try AVAudioSession.sharedInstance().setCategory(.playback, mode: .default)
            //try AVAudioSession.sharedInstance().setActive(true)
            bombSoundEffect = try AVAudioPlayer(contentsOf: url)
            bombSoundEffect?.play()
        } catch {
            print("Something Wrong")
        }
    }
}

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
    
    @ObservedObject var wordAndTranslation = TranslationBinding()

    var body: some View {
        
        return NavigationView {
            VStack(alignment: .leading) {
                
                HStack {
                    TextField("Word To Go", text: $wordAndTranslation.word)
                }
 
                Text("\(wordAndTranslation.translation)")
                Spacer()
                Text("\(wordDictionary.count) in total")
                
                Button(action: {
                    print("Button Clicked")
                    SoundPlay.play(word: self.wordAndTranslation.word)
                }) {
                    Text("Sound")
                }
            }
            .navigationBarTitle("考拉词典", displayMode: .inline)
        
            .padding()
        }
    }
}



struct TranslationView_Previews: PreviewProvider {
    
    static var previews: some View {
        TranslationView()
    }
}

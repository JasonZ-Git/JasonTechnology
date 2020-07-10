//
//  PlaySound.swift
//  KoalaDictionary
//
//  Created by Jason Zhang on 10/7/20.
//  Copyright © 2020 Jason Zhang. All rights reserved.
//

import Foundation
import AVFoundation

var audioPlayer : AVAudioPlayer?

func playSound(sound: String) {
    if let path = Bundle.main.path(forResource: "Sounds/"+sound, ofType: "mp3") {
        do {
            audioPlayer = try AVAudioPlayer (contentsOf: URL(fileURLWithPath: path))
            audioPlayer?.play()
        } catch {
            print("cannot find the file")
        }
    }
}

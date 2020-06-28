//
//  PlaySound.swift
//  Jason Dictionary
//
//  Created by Jason Zhang on 30/5/20.
//  Copyright © 2020 Jason Zhang. All rights reserved.
//

import Foundation
import AVFoundation

var audioPlayer : AVAudioPlayer?

func playSound(sound: String, type: String) {
    if let path = Bundle.main.path(forResource: sound, ofType: type) {
        do {
            audioPlayer = try AVAudioPlayer (contentsOf: URL(fileURLWithPath: path))
            audioPlayer?.play()
            print("played \(sound)")
        } catch {
            print("cannot find the file")
        }
    }
}

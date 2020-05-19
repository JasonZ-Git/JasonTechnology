//
//  WordTranslation.swift
//  Jason Dictionary
//
//  Created by Jason Zhang on 11/5/20.
//  Copyright © 2020 Jason Zhang. All rights reserved.
//

import SwiftUI
import CoreLocation

struct WordTranslation: Hashable, Codable, Identifiable {
    var id: Int
    var word: String
    var translation: String
}

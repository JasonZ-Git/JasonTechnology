//
//  Data.swift
//  Jason Dictionary
//
//  Created by Jason Zhang on 11/5/20.
//  Copyright © 2020 Jason Zhang. All rights reserved.
//

import UIKit
import SwiftUI
import CoreLocation

let translationData: [WordTranslation] = load("dictionary.json");

func load<T: Decodable>(_ filename: String) -> T {
    let data: Data
    
    guard let file = Bundle.main.url(forResource: filename, withExtension: nil)
        else {
            fatalError("Couldn't find \(filename) in main bundle.")
    }
    
    do {
        data = try Data(contentsOf: file)
    } catch {
        fatalError("Couldn't load \(filename) from main bundle:\n\(error)")
    }
    
    do {
        let decoder = JSONDecoder()
        return try decoder.decode(T.self, from: data)
    } catch {
        fatalError("Couldn't parse \(filename) as \(T.self):\n\(error)")
    }
}

// let tempDictionary : [String:String] = load("translations.plist")

//func loadPropertyToDictionary (_ filename: String) ->[String : String] {
//    var config: [String: String]?
//    // var dictData : [String : String]
//    if let infoPlistPath = Bundle.main.url(forResource: filename, withExtension: "plist") {
//        do {
//            let infoPlistData = try Data(contentsOf: infoPlistPath)
//
//            if let dict = try PropertyListSerialization.propertyList(from: infoPlistData, options: [], format: nil) as? [String: String] {
//                config = dict
//            }
//
//        } catch {
//            print(error)
//        }
//
//    }
//
//    return config ?? ["hello":"bad luck"]
//}

struct Data_Previews: PreviewProvider {
    static var previews: some View {
        /*@START_MENU_TOKEN@*/Text("Hello, World!")/*@END_MENU_TOKEN@*/
    }
}

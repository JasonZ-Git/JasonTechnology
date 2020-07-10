//
//  Data.swift
//  KoalaDictionary
//
//  Created by Jason Zhang on 10/7/20.
//  Copyright © 2020 Jason Zhang. All rights reserved.
//

import UIKit
import SwiftUI
import CoreLocation

let wordDictionary : [String : String] = loadProperty("dictionary")


func loadProperty(_ filename : String) -> [String : String] {
    let path = Bundle.main.path(forResource: filename, ofType: "properties")
    
    var text : String! = ""
    do {
        text = try String(contentsOfFile: path!, encoding: String.Encoding.utf8)
    } catch {
        print(error)
    }
    
    let lines : [String] = text.components(separatedBy: "\n")
    
    var resultDict : [String:String] = [:]
    for line in lines {
        if line.contains("=") {
            let tempStr = line.components(separatedBy: "=")
            resultDict[tempStr[0]] = tempStr[1]
        }
    }
    
    return resultDict
}

func loadJSON<T: Decodable>(_ filename: String) -> T {
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

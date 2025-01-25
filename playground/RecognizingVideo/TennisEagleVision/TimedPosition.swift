//
//  Untitled.swift
//  TennisEagleVision
//
//  Created by Jason Zhang on 2/11/2024.
//  Copyright © 2024 Apple. All rights reserved.
//

import CoreGraphics
import Foundation


struct TimedPosition : CustomStringConvertible {
    public let position: CGPoint
    public let timestamp: Date
    
    // Initializer
    init(position: CGPoint, timestamp: Date) {
        self.position = position
        self.timestamp = timestamp
    }
    
    var description: String {
            return "Position: (\(position.x), \(position.y))"
     }
    
    private var formattedTimestamp: String {
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = "HH:mm:ss.SSS" // Customize format as needed
            return dateFormatter.string(from: timestamp)
    }
}

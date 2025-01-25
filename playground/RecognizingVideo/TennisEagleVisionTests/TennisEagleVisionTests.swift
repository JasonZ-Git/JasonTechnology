//
//  TennisEagleVisionTests.swift
//  TennisEagleVisionTests
//
//  Created by Jason Zhang on 17/11/2024.
//  Copyright © 2024 Apple. All rights reserved.
//

import Testing
import UIKit
import Foundation
@testable import TennisEagleVision


struct TennisEagleVisionTests {

    @Test func testPathUtil() async throws {
        let points = [
            createTimedPosition(x: 269.375, y: 339.2578125, timestamp: "2024-11-07 19:26:28.448"),
            createTimedPosition(x: 269.4921875, y: 339.140625, timestamp: "2024-11-07 19:26:28.490"),
            createTimedPosition(x: 269.140625, y: 339.0234375, timestamp: "2024-11-07 19:26:28.531"),
            createTimedPosition(x: 268.90625, y: 339.140625, timestamp: "2024-11-07 19:26:28.573"),
            createTimedPosition(x: 269.0234375, y: 339.0234375, timestamp: "2024-11-07 19:26:28.614"),
            createTimedPosition(x: 314.4921875, y: 300.0, timestamp: "2024-11-07 19:26:28.740"),
            createTimedPosition(x: 327.03125, y: 287.109375, timestamp: "2024-11-07 19:26:28.779"),
            createTimedPosition(x: 344.375, y: 276.6796875, timestamp: "2024-11-07 19:26:28.822"),
            createTimedPosition(x: 353.28125, y: 268.59375, timestamp: "2024-11-07 19:26:28.865"),
            createTimedPosition(x: 357.03125, y: 264.7265625, timestamp: "2024-11-07 19:26:28.906"),
            createTimedPosition(x: 372.5, y: 251.71875, timestamp: "2024-11-07 19:26:28.948"),
            createTimedPosition(x: 385.625, y: 244.1015625, timestamp: "2024-11-07 19:26:28.988"),
            createTimedPosition(x: 393.59375, y: 240.0, timestamp: "2024-11-07 19:26:29.031"),
            createTimedPosition(x: 399.453125, y: 235.546875, timestamp: "2024-11-07 19:26:29.071"),
            createTimedPosition(x: 408.828125, y: 230.15625, timestamp: "2024-11-07 19:26:29.114"),
            createTimedPosition(x: 415.625, y: 225.0, timestamp: "2024-11-07 19:26:29.155"),
            createTimedPosition(x: 420.546875, y: 223.59375, timestamp: "2024-11-07 19:26:29.198"),
            createTimedPosition(x: 428.984375, y: 217.96875, timestamp: "2024-11-07 19:26:29.239"),
            createTimedPosition(x: 419.375, y: 213.515625, timestamp: "2024-11-07 19:26:29.280"),
            createTimedPosition(x: 363.828125, y: 207.65625, timestamp: "2024-11-07 19:26:29.406"),
            createTimedPosition(x: 361.71875, y: 207.65625, timestamp: "2024-11-07 19:26:29.448"),
            createTimedPosition(x: 340.625, y: 206.25, timestamp: "2024-11-07 19:26:29.490"),
            createTimedPosition(x: 323.515625, y: 205.546875, timestamp: "2024-11-07 19:26:29.531"),
            createTimedPosition(x: 309.6875, y: 201.796875, timestamp: "2024-11-07 19:26:29.573"),
            createTimedPosition(x: 294.5703125, y: 200.390625, timestamp: "2024-11-07 19:26:29.614"),
            createTimedPosition(x: 281.328125, y: 199.21875, timestamp: "2024-11-07 19:26:29.656"),
            createTimedPosition(x: 270.8984375, y: 198.28125, timestamp: "2024-11-07 19:26:29.697"),
            createTimedPosition(x: 256.6015625, y: 195.46875, timestamp: "2024-11-07 19:26:29.740"),
            createTimedPosition(x: 244.6484375, y: 194.296875, timestamp: "2024-11-07 19:26:29.781"),
            createTimedPosition(x: 231.9921875, y: 192.65625, timestamp: "2024-11-07 19:26:29.822")
        ]
        
        // Call your findBouncingPoint function
        let result = TennisPathUtil.findBouncingPoint(points)
        
        // Define expected result (based on your logic)
        let expectedResult = CGPoint(x: 269.0234375, y: 339.0234375)
        #expect(expectedResult.equalTo(result!))
        print(expectedResult)
    }

    private func createTimedPosition(x: CGFloat, y: CGFloat, timestamp: String) -> TimedPosition {
        let dateFormatter = DateFormatter()
            
        // Set the format to match the string: "yyyy-MM-dd HH:mm:ss.SSS"
        dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss.SSS"
            
        // Parse the string into a Date object
        let dateInput = dateFormatter.date(from: timestamp)
        
        return TimedPosition(position: CGPoint(x: x, y: y), timestamp: dateInput!)
    }

}

//
//  KalmanFilter2D.swift
//  TennisEagleVision
//
//  Created by Jason Zhang on 29/4/2025.
//  Copyright © 2025 Apple. All rights reserved.
//
import Foundation

class KalmanFilter2D {
    private var q: CGFloat // process noise
    private var r: CGFloat // measurement noise
    private var p: CGFloat = 1 // estimation error
    private var k: CGFloat = 0 // kalman gain

    private var x: CGFloat = 0 // estimated position x
    private var y: CGFloat = 0 // estimated position y

    init(processNoise: CGFloat, measurementNoise: CGFloat) {
        self.q = processNoise
        self.r = measurementNoise
    }

    func update(measurement: CGPoint) -> CGPoint {
        // Predict phase
        p = p + q

        // Update Kalman gain
        k = p / (p + r)

        // Update estimate with measurement
        x = x + k * (measurement.x - x)
        y = y + k * (measurement.y - y)

        // Update error
        p = (1 - k) * p

        return CGPoint(x: x, y: y)
    }

    func reset(to point: CGPoint) {
        x = point.x
        y = point.y
    }
}

//
//  TennisUtil.swift
//  TennisEagleVision
//
//  Created by Jason Zhang on 16/10/2024.
//  Copyright © 2024 Apple. All rights reserved.
//
import UIKit

class TennisPathUtil {
    
    private static let MAX_POINTS: Int = 30;
    
    public static func calculatePath(_ ballPositions: [CGPoint] , _ ballDiameter: CGFloat) -> CGPath {

        let path = UIBezierPath()
        
        let startingPoint: CGPoint = ballPositions[0];
        
        path.move(to: startingPoint);
        
        var lastPoint = startingPoint;
        for position in ballPositions.dropFirst() {
            
            if (distance(position, lastPoint) > ballDiameter * 5){
                path.close()
                path.move(to: position)
            }
            
            path.addLine(to: position)
            lastPoint = position
        }
        
        return path.cgPath;
    }
    
    // Function to find the bouncing point using average slopes
    public static func findBouncingPoint(_ data: [CGPoint]) -> CGPoint? {
        let windowSize = 5
        
        if (data.count < windowSize * 2 ) { return nil }
        
        for i in 0...(data.count - windowSize * 2) {
            let avgSlope1 = averageSlope(points: data, start: i)
            let avgSlope2 = averageSlope(points: data, start: i + windowSize)

            // Check for direction change (sign change)
            
            if avgSlope1 * avgSlope2 < 0 {
                print("slop1 is \(avgSlope1), slop2 is \(avgSlope2)")
                return data[i + 2] // Return the middle point of the first window as the bounce point
            }
        }
        return nil // No bounce point found
    }
    
    private static func averageSlope(points: [CGPoint], start: Int) -> CGFloat {
        let p1 = points[start]
        let p5 = points[start + 4]
        return (p5.y - p1.y) / (p5.x - p1.x)
    }
    
    private static func distance(_ point1: CGPoint, _ point2: CGPoint) -> CGFloat {
        let deltaX = point2.x - point1.x
        let deltaY = point2.y - point1.y
        return sqrt(deltaX * deltaX + deltaY * deltaY)
    }
    
}

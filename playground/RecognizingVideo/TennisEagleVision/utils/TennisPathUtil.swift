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
    
    public static func calculatePath(_ ballPositions: [TimedPosition] , _ ballDiameter: CGFloat) -> CGPath {
        
        let path = UIBezierPath()
        
        let startingPoint: CGPoint = ballPositions[0].position;
        
        path.move(to: startingPoint);
        
        var lastPoint = startingPoint;
        for currentTimedPosition in ballPositions.dropFirst() {
            
            if (distance(currentTimedPosition.position, lastPoint) > ballDiameter * 5){
                path.close()
                path.move(to: currentTimedPosition.position)
            }
            
            path.addLine(to: currentTimedPosition.position)
            lastPoint = currentTimedPosition.position
        }
        
        return path.cgPath;
    }
    
    public static func findBouncingPoint(_ points: [TimedPosition]) -> CGPoint? {
        guard points.count > 5 else { return nil }
        
        var previousPoint = points.first!
        var slopes: [CGFloat] = []
        var xDiffs: [CGFloat] = []
        var yDiffs: [CGFloat] = []
        
        // Loop through points, starting from the second element
        for (index, point) in points.enumerated().dropFirst(3) {
            // Calculate the differences safely
            let xDiff: CGFloat = point.position.x - previousPoint.position.x
            let yDiff: CGFloat = point.position.y - previousPoint.position.y
            let timestampDiff = point.timestamp.timeIntervalSince(previousPoint.timestamp)
            
            let slope = yDiff/xDiff
            slopes.append(slope)
            xDiffs.append(xDiff)
            yDiffs.append(yDiff)
            
            // Update previousPoint for the next iteration
            previousPoint = points[index-2]
        }
        
        print("xDiffs are \(xDiffs)")
        print("yDiffs are \(yDiffs)")
        print("slopes are \(slopes)")
        
        return nil
    }
    


    
    private static func distance(_ point1: CGPoint, _ point2: CGPoint) -> CGFloat {
        let deltaX = point2.x - point1.x
        let deltaY = point2.y - point1.y
        return sqrt(deltaX * deltaX + deltaY * deltaY)
    }
}

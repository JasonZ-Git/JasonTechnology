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
        
        // Loop through points, starting from the second element
        for (index, point) in points.enumerated().dropFirst(3) {
            // Calculate the differences safely
            let xDiff: CGFloat = point.position.x - previousPoint.position.x
            let yDiff: CGFloat = point.position.y - previousPoint.position.y
            let timestampDiff = point.timestamp.timeIntervalSince(previousPoint.timestamp)
            
            if (xDiff < -1.5 && index < points.count - 3) {
                let diff_1 = points[index - 1].position.x - points[index - 2].position.x;
                let diff_2 = points[index].position.x - points[index].position.y;
                let diff_3 = points[index+1].position.x - points[index].position.y;
                let diff_4 = points[index+2].position.x - points[index+1].position.y;
                
                if (diff_1 > 1.5 && diff_2 > 1.5 && diff_3 < -1.5 && diff_4 < -1.5) {
                    return point.position;
                }
            }
                        
            // Update previousPoint for the next iteration
            previousPoint = point
        }
        
        return nil
    }

    
    private static func distance(_ point1: CGPoint, _ point2: CGPoint) -> CGFloat {
        let deltaX = point2.x - point1.x
        let deltaY = point2.y - point1.y
        return sqrt(deltaX * deltaX + deltaY * deltaY)
    }
}

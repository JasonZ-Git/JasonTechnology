//
//  DrawingRouteView.swift
//  TennisDetectorV2
//
//  Created by Jason Zhang on 15/4/2023.
//

import Foundation

//  TennisDetectorV2
//
//  Created by Jason Zhang on 11/3/2023.
//


import UIKit
import Vision
import SwiftUI

class RouteView: UIView {
    
    private let DISTANCE_UPPER_THRESHOLD = 10.0;
    private let DISTANCE_LOWER_THRESHOLD = 1.0;
    public var predictedObjects: [VNRecognizedObjectObservation] = [] {
        didSet {
            self.drawRoute(with: predictedObjects)
        }
    }

    private func drawRoute(with predictions: [VNRecognizedObjectObservation]) {
        subviews.forEach({ $0.removeFromSuperview() })
        
        for prediction in predictions {
            drawRoute(prediction: prediction)
        }
    }
    
    private var detectionPositions: [CGPoint] = []
    private func drawRoute(prediction: VNRecognizedObjectObservation) {
        let bgRect = getTransformedBoundingBox(prediction.boundingBox)
        
        detectionPositions.append(CGPoint(x: bgRect.midX, y: bgRect.midY))
        
        let path = processPath(positions: detectionPositions);
        
        let pathView = createPathView(cgPath: path.cgPath)
    
        addSubview(pathView)
    }
    
    private func processPath(positions: [CGPoint]) -> UIBezierPath {
        
        let groupedPoints = filterValidPathPoints(originalPoints: positions);
        
        let path = UIBezierPath()
        if groupedPoints.isEmpty {
            return path
        }
        
        for currentGroup in groupedPoints {
            path.move(to: currentGroup.first!)
            currentGroup.forEach { currentPoint in
                path.addLine(to: currentPoint)
            }
        }
        
        path.move(to: detectionPositions.first!)
        detectionPositions.forEach { route in
            path.addLine(to: route)
        }
        
        return path
    }
    
    private var arrayPointGroups: [[CGPoint]] = []
    private func filterValidPathPoints(originalPoints: [CGPoint]) -> [[CGPoint]] {
        
        for currentPoint in originalPoints {
            let foundGroupToAdd = addToGroup (point: currentPoint, groups: &arrayPointGroups);
            if (!foundGroupToAdd) {
                print("Create a new group for point: ")
                printOne(currentPoint)
                let newGroup: [CGPoint] = [currentPoint]
                arrayPointGroups.append(newGroup)
            }
        }
        print("")
        
        var resultGroup: [[CGPoint]] = []
        
        for currentGroup in arrayPointGroups {
            if currentGroup.count > 10 {
                resultGroup.append(currentGroup)
            }
        }
        
        return resultGroup
    }
    
    private func addToGroup(point: CGPoint, groups: inout [[CGPoint]]) -> Bool {
        
        if groups.isEmpty {
            return false;
        }
        
        var minDistance: CGFloat = CGFloat.infinity
        var candidateGroupIndex = -1;
        for (index, currentGroup) in groups.enumerated() {
            let distanceOfCurrentGroup = distance(currentGroup.last!, point)
            if distanceOfCurrentGroup < minDistance {
                minDistance = distanceOfCurrentGroup
                candidateGroupIndex = index
            }
        }
        print("point is \(String(format: "(%.1f, %.1f)", point.x, point.y))")
        print("minDistance is \(String(format: "%.1f", minDistance))")
        
        if (minDistance > DISTANCE_UPPER_THRESHOLD) { return false }
        
        if (minDistance > DISTANCE_LOWER_THRESHOLD) {
            groups[candidateGroupIndex].append(point)
            print("new groups is: ")
            printAll(groups)
            print("")
        } else {
            print("ignore \(String(format: "(%.1f, %.1f)", point.x, point.y))) as it is close to existing ones, minimal distance is \(String(format: "%.1f", minDistance))")
            print("")
        }
        
        return true
    }
    
    private func createPathView(cgPath: CGPath) -> UIView {
        let shape = CAShapeLayer()
        shape.path = cgPath
        shape.lineWidth = 11.0
        shape.fillColor = UIColor.clear.cgColor
        shape.strokeColor = UIColor.orange.cgColor
        
        let bgView = UIView(frame: frame)
        bgView.backgroundColor = UIColor.clear
        
        bgView.layer.addSublayer(shape)
        
        return bgView
    }
    
    private func getTransformedBoundingBox(_ originalBoundingBox: CGRect) -> CGRect {
        let scale = CGAffineTransform.identity.scaledBy(x: bounds.width, y: bounds.height)
        let transform = CGAffineTransform(scaleX: 1, y: -1).translatedBy(x: 0, y: -1)
        let bgRect = originalBoundingBox.applying(transform).applying(scale)
        
        return bgRect
    }
    
    private func distance(_ a: CGPoint, _ b: CGPoint) -> CGFloat {
        let xDist = a.x - b.x
        let yDist = a.y - b.y
        return CGFloat(sqrt((xDist * xDist) + (yDist * yDist)))
    }
    
    private func printOne(_ aPoint: CGPoint) {
        let formattedNumber = String(format: "(%.1f, %.1f)", aPoint.x, aPoint.y)
        print(formattedNumber)
    }
    
    private func printAll(_ points: [CGPoint]) {
        
        var result = "  ["
        print("[")
        for aPoint in points {
            let formattedNumber = String(format: "    (%.1f, %.1f), ", aPoint.x, aPoint.y)
            result +=  formattedNumber
        }
        result += "]"
        print(result)
    }
    
    private func printAll(_ points: [[CGPoint]]) {
        print("[")
        for aPoint in points {
            printAll(aPoint)
        }
        print("]")
    }
    
    
    private var colorIndex = 0;
    private func randomColor() -> UIColor {
       let colors: [UIColor] = [UIColor.black, UIColor.red, UIColor.green, UIColor.blue, UIColor.yellow]
       colorIndex = colorIndex + 1
       return colors[colorIndex%(colors.count - 1)]
    }
    
}

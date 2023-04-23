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
    

    
    private var routePoints: [CGPoint] = []
    private func drawRoute(prediction: VNRecognizedObjectObservation) {
        let bgRect = getTransformedBoundingBox(prediction.boundingBox)
        
        addToRoute(newRoutePoint: CGPoint(x: bgRect.midX, y: bgRect.midY))
        
        let path = processPath(cgPoints: routePoints);
        
        let pathView = createPathView(cgPath: path.cgPath)
    
        addSubview(pathView)
    }
    
    private func addToRoute(newRoutePoint: CGPoint) {
        routePoints.append(newRoutePoint)
    }
    
    private func processPath(cgPoints: [CGPoint]) -> UIBezierPath {
        
        // let filteredPath = filterValidPathPoints();
        
        let path = UIBezierPath()
        if cgPoints.isEmpty {
            return path
        }
        
        path.move(to: routePoints.first!)
        routePoints.forEach {route in
            path.addLine(to: route)
        }
        
        return path
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
}

//  TennisDetectorV2
//
//  Created by Jason Zhang on 11/3/2023.
//


import UIKit
import Vision
import SwiftUI

class DrawingBoundingBoxView: UIView {
    
    public var predictedObjects: [VNRecognizedObjectObservation] = [] {
        didSet {
            // self.drawBoxs(with: predictedObjects)
            self.drawRoute(with: predictedObjects)
        }
    }
    
    private func drawBoxs(with predictions: [VNRecognizedObjectObservation]) {
        subviews.forEach({ $0.removeFromSuperview() })
        
        for prediction in predictions {
            drawBoundingBox(prediction: prediction)
            drawLabel(prediction: prediction)
            drawSolidBox(prediction: prediction)
        }
    }
    
    private func drawRoute(with predictions: [VNRecognizedObjectObservation]) {
        subviews.forEach({ $0.removeFromSuperview() })
        
        for prediction in predictions {
            drawRoute(prediction: prediction)
        }
    }
    
    private func drawBoundingBox(prediction: VNRecognizedObjectObservation) {
        let bgRect = getTransformedBoundingBox(prediction.boundingBox)
        let bgView = UIView(frame: bgRect)
        bgView.layer.borderColor = UIColor.blue.cgColor
        bgView.layer.borderWidth = 4
        bgView.backgroundColor = UIColor.clear
        addSubview(bgView)
    }
    
    private func drawLabel(prediction: VNRecognizedObjectObservation) {
        let labelString: String? = prediction.labels.first?.identifier
        
        let bgRect = getTransformedBoundingBox(prediction.boundingBox)
        
        let label = UILabel(frame: CGRect(x: 0, y: 0, width: 300, height: 300))
        label.text = labelString ?? "N/A"
        label.font = UIFont.systemFont(ofSize: 13)
        label.textColor = UIColor.black
        label.backgroundColor = UIColor.red
        label.sizeToFit()
        label.frame = CGRect(x: bgRect.origin.x, y: bgRect.origin.y - label.frame.height,
                             width: label.frame.width, height: label.frame.height)
        
        addSubview(label)
    }
    
    private func drawSolidBox(prediction: VNRecognizedObjectObservation) {
        let bgRect = getTransformedBoundingBox(prediction.boundingBox)
    
        let bgView = UIView(frame: bgRect)
        bgView.backgroundColor = UIColor.blue
        
        bgView.layer.cornerRadius = bgView.layer.bounds.width / 2
        bgView.clipsToBounds = true
        
        addSubview(bgView)
    }
    
    private func getTransformedBoundingBox(_ originalBoundingBox: CGRect) -> CGRect {
        let scale = CGAffineTransform.identity.scaledBy(x: bounds.width, y: bounds.height)
        let transform = CGAffineTransform(scaleX: 1, y: -1).translatedBy(x: 0, y: -1)
        let bgRect = originalBoundingBox.applying(transform).applying(scale)
        
        return bgRect
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
    
    private func addToRoute__1(newRoutePoint: CGPoint) {
        if routePoints.count <= 1 {
            routePoints.append(newRoutePoint)
            
            return
        }
        
        let newDistance = distance(newRoutePoint, routePoints.last!);
        
        let lastDistance = distance(routePoints[routePoints.count - 2], routePoints[routePoints.count - 1])
        
        NSLog("new distance is %f", newDistance);
        NSLog("last distance is %f", lastDistance);
        if newDistance < 3 {
            routePoints.append(newRoutePoint)
        }
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
    
    private func distance(_ a: CGPoint, _ b: CGPoint) -> CGFloat {
        let xDist = a.x - b.x
        let yDist = a.y - b.y
        return CGFloat(sqrt((xDist * xDist) + (yDist * yDist)))
    }
}

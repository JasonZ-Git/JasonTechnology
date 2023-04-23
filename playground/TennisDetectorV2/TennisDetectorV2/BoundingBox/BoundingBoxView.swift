//  TennisDetectorV2
//
//  Created by Jason Zhang on 11/3/2023.
//


import UIKit
import Vision
import SwiftUI

class BoundingBoxView: UIView {
    
    public var predictedObjects: [VNRecognizedObjectObservation] = [] {
        didSet {
            self.drawBoxs(with: predictedObjects, drawingStyle: DrawingBoxStyle.BOX_LABEL)
        }
    }
    
    private func drawBoxs(with predictions: [VNRecognizedObjectObservation], drawingStyle: DrawingBoxStyle) {
        subviews.forEach({ $0.removeFromSuperview() })
        
        switch drawingStyle {
            case .BOX_LABEL:
                for prediction in predictions {
                    drawBoundingBox(prediction: prediction)
                    drawLabel(prediction: prediction)
                }
            case .BOUNDING_BOX:
                for prediction in predictions {
                    drawBoundingBox(prediction: prediction)
                }
            case .LABEL:
                for prediction in predictions {
                    drawLabel(prediction: prediction)
                }
            case .SOLID_CIRCLE:
                for prediction in predictions {
                    drawSolidBox(prediction: prediction)
                }
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
    
    private func distance(_ a: CGPoint, _ b: CGPoint) -> CGFloat {
        let xDist = a.x - b.x
        let yDist = a.y - b.y
        return CGFloat(sqrt((xDist * xDist) + (yDist * yDist)))
    }
}


enum DrawingBoxStyle {
    case SOLID_CIRCLE
    case LABEL
    case BOUNDING_BOX
    case BOX_LABEL
}

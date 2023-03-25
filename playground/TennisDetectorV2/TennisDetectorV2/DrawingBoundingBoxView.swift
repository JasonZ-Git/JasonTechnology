//
//  DrawingBoundingBoxView.swift
//  TennisDetectorV2
//
//  Created by Jason Zhang on 11/3/2023.
//


import UIKit
import Vision

class DrawingBoundingBoxView: UIView {
    
    public var predictedObjects: [VNRecognizedObjectObservation] = [] {
        didSet {
            self.drawBoxs(with: predictedObjects)
        }
    }
    
    private func drawBoxs(with predictions: [VNRecognizedObjectObservation]) {
        subviews.forEach({ $0.removeFromSuperview() })
        
        for prediction in predictions {
            createLabelAndBox(prediction: prediction)
        }
    }
    
    private func createLabelAndBox(prediction: VNRecognizedObjectObservation) {
        let labelString: String? = prediction.labels.first?.identifier
        let color: UIColor = UIColor(hue: 0.5, saturation: 1, brightness: 1, alpha: 0.8)
        
        let scale = CGAffineTransform.identity.scaledBy(x: bounds.width, y: bounds.height)
        let transform = CGAffineTransform(scaleX: 1, y: -1).translatedBy(x: 0, y: -1)
        let bgRect = prediction.boundingBox.applying(transform).applying(scale)
        
        let bgView = UIView(frame: bgRect)
        bgView.layer.borderColor = color.cgColor
        bgView.layer.borderWidth = 4
        bgView.backgroundColor = UIColor.clear
        addSubview(bgView)
        
        let label = UILabel(frame: CGRect(x: 0, y: 0, width: 300, height: 300))
        label.text = labelString ?? "N/A"
        label.font = UIFont.systemFont(ofSize: 13)
        label.textColor = UIColor.black
        label.backgroundColor = color
        label.sizeToFit()
        label.frame = CGRect(x: bgRect.origin.x, y: bgRect.origin.y - label.frame.height,
                             width: label.frame.width, height: label.frame.height)
        
        addSubview(label)
    }
}

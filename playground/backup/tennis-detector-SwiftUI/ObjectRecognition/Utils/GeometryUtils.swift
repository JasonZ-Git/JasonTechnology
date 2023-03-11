//
//  GeometryUtils.swift
//  ObjectRecognition
//

import Foundation
import UIKit

class GeometryUtils {
    
    public static func createLayer(forRecognizedObjects objects:[RecognizedObject],
                            inFrame frame:CGRect) -> CALayer {
        let objectsLayer = CALayer()
        objectsLayer.frame = frame
        
        for object in objects {
            let boundingBoxLayer = createBoundingBox(forRecognizedRect: object.bounds,
                                                 imageFrame: frame)

            let textLayer = createTextLayer(boundingBoxLayer.bounds,
                                                                    labelString: object.label)
            objectsLayer.addSublayer(boundingBoxLayer)
            objectsLayer.addSublayer(textLayer)
        }
        
        return objectsLayer
    }
    
    private static func createBoundingBox(forRecognizedRect: CGRect, imageFrame: CGRect) -> CALayer {

        let scale = CGAffineTransform.identity.scaledBy(x: imageFrame.width, y: imageFrame.height)
        let transform = CGAffineTransform(scaleX: 1, y: -1).translatedBy(x: 0, y: -1)
        let bgRect = forRecognizedRect.applying(transform).applying(scale)
        
        let shapeLayer = CALayer()
        shapeLayer.bounds = bgRect
        shapeLayer.position = CGPoint(x: bgRect.midX, y: bgRect.midY)
        shapeLayer.borderWidth = 4;
        shapeLayer.backgroundColor = CGColor(red: 1.0, green: 0.0, blue: 0.0, alpha: 0.4)
        shapeLayer.borderColor = CGColor(red: 0.0, green: 0.0, blue: 1.0, alpha: 0.4)
        return shapeLayer
    }
    
    
    private static func createTextLayer(_ bounds: CGRect, labelString: String) -> CALayer {
        let textLayer = CATextLayer()
        let formattedString = NSMutableAttributedString(string: labelString)
        let largeFont = UIFont(name: "Helvetica", size: 18.0)!
        formattedString.addAttributes([NSAttributedString.Key.font: largeFont], range: NSRange(location: 0, length: labelString.count))
        textLayer.string = formattedString
        textLayer.bounds = CGRect(x: 0, y: 0, width: 60, height: 25)
        textLayer.position = CGPoint(x: bounds.maxX, y: bounds.minY)
        textLayer.foregroundColor = CGColor(colorSpace: CGColorSpaceCreateDeviceRGB(), components: [0.0, 0.0, 0.0, 1.0])
        textLayer.borderWidth = 2
        textLayer.borderColor = CGColor(red: 0.0, green: 1.0, blue: 0.0, alpha: 0.4)
        
        return textLayer
    }
}

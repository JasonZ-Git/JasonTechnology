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
            let rect = boundingBox(forRecognizedRect: object.bounds,
                                                 imageFrame: frame)
            
            let layer = createRectLayerWithBounds(rect)

            let textLayer = createTextLayerWithBounds(layer.bounds,
                                                                    text: object.label)
            layer.addSublayer(textLayer)
            objectsLayer.addSublayer(layer)
        }
        
        return objectsLayer
    }
    
    private static func boundingBox(forRecognizedRect: CGRect, imageFrame: CGRect) -> CGRect {
        var rect = forRecognizedRect
        
        rect.origin.x *= imageFrame.width
        rect.origin.y *= imageFrame.height
        rect.size.width *= imageFrame.width
        rect.size.height *= imageFrame.height

        // necessary as the recognized image is flipped vertically
        rect.origin.y = imageFrame.height - rect.origin.y - rect.size.height
        // ?? maybe needs to adjust original x as well
        return rect
    }
    
    private static func createRectLayerWithBounds(_ bounds: CGRect) -> CALayer {
        let shapeLayer = CALayer()
        shapeLayer.bounds = bounds
        shapeLayer.position = CGPoint(x: bounds.midX, y: bounds.midY)
        shapeLayer.borderWidth = 5;
        shapeLayer.backgroundColor = CGColor(red: 1.0, green: 0.0, blue: 0.0, alpha: 0.4)
        shapeLayer.borderColor = CGColor(red: 0.0, green: 0.0, blue: 1.0, alpha: 0.4)
        // shapeLayer.cornerRadius = 7
        return shapeLayer
    }
    
    private static func createTextLayerWithBounds(_ bounds: CGRect, text: String) -> CATextLayer {
        let textLayer = CATextLayer()
        let formattedString = NSMutableAttributedString(string: " " + text)
        let largeFont = UIFont(name: "Helvetica", size: 18.0)!
        formattedString.addAttributes([NSAttributedString.Key.font: largeFont], range: NSRange(location: 0, length: text.count))
        textLayer.string = formattedString
        textLayer.bounds = CGRect(x: 0, y: 0, width: 60, height: 25)
        textLayer.position = CGPoint(x: bounds.maxX, y: bounds.minY)
        textLayer.shadowOpacity = 0.7
        textLayer.shadowOffset = CGSize(width: 2, height: 2)
        textLayer.foregroundColor = CGColor(colorSpace: CGColorSpaceCreateDeviceRGB(), components: [0.0, 0.0, 0.0, 1.0])
        textLayer.contentsScale = 2.0 // 2.0 for retina display
        textLayer.borderWidth = 3
        textLayer.borderColor = CGColor(red: 0.0, green: 1.0, blue: 0.0, alpha: 0.4)
        return textLayer
    }
}

/*
See the LICENSE.txt file for this sample’s licensing information.

Abstract:
Contains the object recognition view controller for the Tennis Eagle Vision.
*/

import UIKit
import AVFoundation
import Vision

class TennisBallRecognitionViewController: CameraViewController {
    
    private var detectionOverlay: CALayer! = nil
    
    // Vision parts
    private var requests = [VNRequest]()
    
    private var ballPositions: [CGPoint] = []
    
    private let MAX_POINTS: Int = 30;
    
    
    override func viewDidLoad() {
        super.viewDidLoad();
        
        super.setupCamera()
        
        setupLayers()
        updateLayerGeometry()
        setupVision()
        
        super.startCaptureSession()
    }
    
    @discardableResult
    func setupVision() -> NSError? {
        // Setup Vision parts
        let error: NSError! = nil
        
        guard let modelURL = Bundle.main.url(forResource: "TennisDetectorV2", withExtension: "mlmodelc") else {
            return NSError(domain: "TennisBallRecognitionViewController", code: -1, userInfo: [NSLocalizedDescriptionKey: "Model file is missing"])
        }
        do {
            let visionModel = try VNCoreMLModel(for: MLModel(contentsOf: modelURL))
            let objectRecognition = VNCoreMLRequest(model: visionModel, completionHandler: { (request, error) in
                DispatchQueue.main.async(execute: {
                    // perform all the UI updates on the main queue
                    if let results = request.results {
                        self.drawVisionRequestResults(results)
                    }
                })
            })
            self.requests = [objectRecognition]
        } catch let error as NSError {
            print("Model loading went wrong: \(error)")
        }
        
        return error
    }
    
    func drawVisionRequestResults(_ results: [Any]) {
        CATransaction.begin()
        CATransaction.setValue(kCFBooleanTrue, forKey: kCATransactionDisableActions)
        detectionOverlay.sublayers = nil // remove all the old recognized objects
        for observation in results where observation is VNRecognizedObjectObservation {
            guard let objectObservation = observation as? VNRecognizedObjectObservation else {
                continue
            }
            // Select only the label with the highest confidence.
            let topLabelObservation = objectObservation.labels[0]
            let objectBounds = VNImageRectForNormalizedRect(objectObservation.boundingBox, Int(bufferSize.width), Int(bufferSize.height))
            
            let path = calculatePath(objectBounds)
            
            setupTrajectoryLayer(path)
            setupBallLayer(objectBounds, path)
        }
        self.updateLayerGeometry()
        CATransaction.commit()
    }
    
    func captureOutput(_ output: AVCaptureOutput, didOutput sampleBuffer: CMSampleBuffer, from connection: AVCaptureConnection) {
        guard let pixelBuffer = CMSampleBufferGetImageBuffer(sampleBuffer) else {
            return
        }
        
        let exifOrientation = exifOrientationFromDeviceOrientation()
        
        let imageRequestHandler = VNImageRequestHandler(cvPixelBuffer: pixelBuffer, orientation: exifOrientation, options: [:])
        do {
            try imageRequestHandler.perform(self.requests)
        } catch {
            print(error)
        }
    }

    
    func setupLayers() {
        detectionOverlay = CALayer()
        detectionOverlay.name = "TennisOverlay"
        detectionOverlay.bounds = CGRect(x: 0.0,
                                         y: 0.0,
                                         width: bufferSize.width,
                                         height: bufferSize.height)
        detectionOverlay.position = CGPoint(x: rootLayer.bounds.midX, y: rootLayer.bounds.midY)
        rootLayer.addSublayer(detectionOverlay)
    }
    
    func updateLayerGeometry() {
        let bounds = rootLayer.bounds
        var scale: CGFloat
        
        let xScale: CGFloat = bounds.size.width / bufferSize.height
        let yScale: CGFloat = bounds.size.height / bufferSize.width
        
        scale = fmax(xScale, yScale)
        if scale.isInfinite {
            scale = 1.0
        }
        CATransaction.begin()
        CATransaction.setValue(kCFBooleanTrue, forKey: kCATransactionDisableActions)
        
        // rotate the layer into screen orientation and scale and mirror
        detectionOverlay.setAffineTransform(CGAffineTransform(rotationAngle: CGFloat(.pi / 2.0)).scaledBy(x: scale, y: -scale))
        // center the layer
        detectionOverlay.position = CGPoint(x: bounds.midX, y: bounds.midY)
        
        CATransaction.commit()
    }
    
    func createRoundedRectLayerWithBounds(_ bounds: CGRect) -> CALayer {
        let shapeLayer = CALayer()
        shapeLayer.bounds = bounds
        shapeLayer.position = CGPoint(x: bounds.midX, y: bounds.midY)
        shapeLayer.name = "Found Tennis Ball"
        shapeLayer.backgroundColor = CGColor(colorSpace: CGColorSpaceCreateDeviceRGB(), components: [1.0, 1.0, 0.2, 0.4])
        shapeLayer.cornerRadius = 7
        return shapeLayer
    }
    
    func calculatePath(_ bounds: CGRect) -> CGPath {
        if (ballPositions.count > MAX_POINTS) {
            ballPositions.removeFirst()
        }
        
        let ballPosition = CGPoint(x: bounds.midX, y: bounds.midY)
        
        ballPositions.append(ballPosition)
        
        let path = UIBezierPath()
                
        path.move(to: ballPositions[0])
        
        for position in ballPositions {
            path.addLine(to: position)
        }
        
        return path.cgPath;
    }
    
    func setupTrajectoryLayer(_ path: CGPath) {

        let routineLayer = CAShapeLayer()
        
        routineLayer.path = path
        routineLayer.strokeColor = UIColor.green.cgColor
        routineLayer.lineWidth = 2.0
        routineLayer.fillColor = UIColor.clear.cgColor
        
        detectionOverlay.addSublayer(routineLayer)
    }
    
    func setupBallLayer(_ bounds: CGRect, _ path: CGPath) {
        
        let ballPosition = CGPoint(x: bounds.midX, y: bounds.midY)
        let ballLayer = CALayer()
        ballLayer.bounds = CGRect(x: 0, y: 0, width: bounds.width, height: bounds.height)
        ballLayer.position = ballPosition
        ballLayer.backgroundColor = UIColor.green.cgColor
        ballLayer.cornerRadius = min(bounds.width, bounds.height)/2
        
        let animation = CAKeyframeAnimation(keyPath: "position")
        animation.path = path
        animation.duration = 2.0 // Adjust the duration as needed
        animation.timingFunction = CAMediaTimingFunction(name: .easeInEaseOut)
        animation.repeatCount = .infinity // Repeat the animation if needed
        
        ballLayer.add(animation, forKey: "trajectory")
        
        detectionOverlay.addSublayer(ballLayer)
    }
}

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
    
    private var ballPositions: [TimedPosition] = []
    
    private let MAX_POINTS: Int = 30;
    
    private let kalmanFilter = KalmanFilter2D(
        processNoise: 0.01,        // ball trajectory is relatively smooth
        measurementNoise: 1.5      // real-world detection might jitter ±1-2 pixels
    )
        
    override func viewDidLoad() {
        super.viewDidLoad();
        
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
            let objectBounds = VNImageRectForNormalizedRect(objectObservation.boundingBox, Int(bufferSize.width), Int(bufferSize.height))
            
            let path = calculatePath(objectBounds)
            
            setupPathLayer(path)
            //setupBallLayer(objectBounds, path)
            setupBouncingPointLayer(objectBounds);
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
        
        let xScale: CGFloat = bounds.size.width / bufferSize.height
        let yScale: CGFloat = bounds.size.height / bufferSize.width
        
        var scale = fmax(xScale, yScale)
        if scale.isInfinite {
            scale = 1.0
        }
        CATransaction.begin()
        CATransaction.setValue(kCFBooleanTrue, forKey: kCATransactionDisableActions)
        
        // rotate the layer into screen orientation and scale and mirror
        detectionOverlay.setAffineTransform(CGAffineTransform(rotationAngle: CGFloat(.pi / 2.0)).scaledBy(x: scale, y: -scale))
        
        let smoothedPoint = kalmanFilter.update(measurement: CGPoint(x: bounds.midX, y: bounds.midY))
        detectionOverlay.position = smoothedPoint
        
        CATransaction.commit()
    }
    
    func calculatePath(_ bounds: CGRect) -> CGPath {
        
        if (ballPositions.count >= MAX_POINTS) {
            ballPositions.removeFirst()
        }
        
        let ballPosition = CGPoint(x: bounds.midX, y: bounds.midY)
        
        let ballDiameter = (bounds.size.width + bounds.size.height)/2;
        
        ballPositions.append(TimedPosition(position: ballPosition, timestamp: Date()))
        
        return TennisPathUtil.calculatePath(ballPositions, ballDiameter);
    }
    
    func setupPathLayer(_ path: CGPath) {

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
    
    func setupBouncingPointLayer (_ bounds: CGRect) {
        let bouncingPoint = TennisPathUtil.findBouncingPoint(ballPositions)
        
        if(bouncingPoint == nil) {
            print("Nothing found")
            return;
        }
        
        print("bouncing point is \(String(describing: bouncingPoint))")
        
        let bouncingBallLayer = CALayer()
        bouncingBallLayer.bounds = CGRect(x: bouncingPoint!.x, y: bouncingPoint!.y, width: bounds.width, height: bounds.height)
        bouncingBallLayer.position = bouncingPoint!
        bouncingBallLayer.backgroundColor = UIColor.red.cgColor
        bouncingBallLayer.cornerRadius = min(bounds.width, bounds.height)/2
        
        detectionOverlay.addSublayer(bouncingBallLayer)
    }
    
    func friendlyPrint(_ timePositions: [TimedPosition]) {
        
        let bounds = rootLayer.bounds
        let xScale: CGFloat = bounds.size.width / bufferSize.height
        let yScale: CGFloat = bounds.size.height / bufferSize.width
        let scale = fmax(xScale, yScale)
        
        let reversedData: [CGPoint] = timePositions.map { current in
            // Unscale the coordinates
            let unscaledX = current.position.x / scale
            let unscaledY = current.position.y / -scale // Undo mirroring

            // Unrotate by 90 degrees (counterclockwise)
            let rotatedX = unscaledY
            let rotatedY = bounds.size.width - unscaledX // Adjust for screen dimensions

            return CGPoint(x:rotatedX, y:rotatedY)
        }
        
        print("====== original data ======")
        print(timePositions)
        let difference = calculateDifferences(timePositions);
        print("====== difference ======")
        print(difference)
    }
    
    func calculateDifferences(_ points: [TimedPosition]) -> [CGPoint] {
        var differences: [CGPoint] = []
        
        guard points.count > 1 else {
            return differences // Return empty if there are not enough points
        }

        for i in 1..<points.count {
            let previousPoint = points[i - 1]
            let currentPoint = points[i]
            
            // Calculate the difference
            let dx = currentPoint.position.x - previousPoint.position.x
            let dy = currentPoint.position.y - previousPoint.position.y
            
            // Create a new CGPoint representing the difference
            let difference = CGPoint(x: dx, y: dy)
            differences.append(difference)
        }

        return differences
    }
}

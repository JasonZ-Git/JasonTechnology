//
//  MyViewController.swift
//  TennisDetectorV2
//
//  Created by Jason Zhang on 11/3/2023.
//

import UIKit
import Vision
import CoreMedia

class BoundingBoxViewController: UIViewController {
    private var cameraView = UIView()
    private var boundingBoxView = BoundingBoxView()
    
    var request: VNCoreMLRequest?
    var visionModel: VNCoreMLModel?
    var isInferencing = false
    
    var videoCapture: VideoCapture!
    let semaphore = DispatchSemaphore(value: 1)
    
    var predictions: [VNRecognizedObjectObservation] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        cameraView.frame = self.view.frame
        self.view.addSubview(cameraView)
        
        boundingBoxView.frame = self.view.frame
        self.view.addSubview(boundingBoxView)
        
        // setup the model
        setUpModel()
        
        // setup camera
        setUpCamera()
    }
    
    private func setUpModel() {
        guard let modelURL = Bundle.main.url(forResource: "TennisDetectorV2", withExtension: "mlmodelc") else { return }
        
        if let visionModel = try? VNCoreMLModel(for: MLModel(contentsOf: modelURL)) {
            self.visionModel = visionModel
            request = VNCoreMLRequest(model: visionModel, completionHandler: visionRequestDidComplete)
            request?.imageCropAndScaleOption = .scaleFill
        } else {
            fatalError("fail to create vision model")
        }
    }

   private func setUpCamera() {
        videoCapture = VideoCapture()
        videoCapture.delegate = self
        videoCapture.setUp() { success in
            
            if success {
                // add preview view on the layer
                if let previewLayer = self.videoCapture.previewLayer {
                    self.cameraView.layer.addSublayer(previewLayer)
                }
                
                // start video preview when setup is done
                self.videoCapture.start()
            }
        }
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        videoCapture.previewLayer?.frame = cameraView.frame
    }
}

extension BoundingBoxViewController: VideoCaptureDelegate {
    func videoCapture(_ capture: VideoCapture, didCaptureVideoFrame pixelBuffer: CVPixelBuffer?) {
        // the captured image from camera is contained on pixelBuffer
        if !self.isInferencing, let pixelBuffer = pixelBuffer {
            self.isInferencing = true
            
            // predict!
            self.predictUsingVision(pixelBuffer: pixelBuffer)
        }
    }
}

extension BoundingBoxViewController {
    func predictUsingVision(pixelBuffer: CVPixelBuffer) {
        guard let request = request else { fatalError() }
        // vision framework configures the input size of image following our model's input configuration automatically
        self.semaphore.wait()
        let handler = VNImageRequestHandler(cvPixelBuffer: pixelBuffer)
        try? handler.perform([request])
    }
    
    // MARK: - Post-processing
    func visionRequestDidComplete(request: VNRequest, error: Error?) {
        if let predictions = request.results as? [VNRecognizedObjectObservation] {
            
            self.predictions = predictions
            DispatchQueue.main.async {
                self.boundingBoxView.predictedObjects = predictions
                self.isInferencing = false
            }
        } else {
            self.isInferencing = false
        }
        self.semaphore.signal()
    }
}




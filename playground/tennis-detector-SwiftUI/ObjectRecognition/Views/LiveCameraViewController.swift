//
//  LiveCameraViewController.swift
//  ObjectRecognition
//
//

import AVFoundation
import UIKit
import Vision

class LiveCameraViewController: UIViewController {
    private var cameraView:UIView = UIView()
    private var isRecognizing = false
    private var objectRecognizer = ObjectRecognizer()
    private var objectsLayer:CALayer = CALayer()
    private var previewLayer:AVCaptureVideoPreviewLayer?
    private var queue:DispatchQueue = DispatchQueue(label: "LiveCameraViewController")
    private var session:AVCaptureSession?
    private var videoSize:CGSize = .zero
    var lastTimestamp = CMTime()
    
    
    required init() {
        super.init(nibName: nil, bundle: nil)
     }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        cameraView.frame = self.view.frame
        self.view.addSubview(cameraView)

        configureSession()
        configurePreview()
        session?.startRunning()
    }
    
    override func viewWillLayoutSubviews() {
        cameraView.frame = view.frame
        previewLayer?.frame = cameraView.layer.bounds
        previewLayer?.connection?.videoOrientation = OrientationUtils.videoOrientationForCurrentOrientation()
    }
    
    /// Configure the preview layer
    /// the layer is added to the cameraView
    private func configurePreview() {
        guard let session = session else {return}
        if self.previewLayer == nil {
            let previewLayer = AVCaptureVideoPreviewLayer(session: session)
            previewLayer.frame = cameraView.layer.bounds
            previewLayer.videoGravity = .resizeAspectFill
            cameraView.layer.addSublayer(previewLayer)
            self.previewLayer = previewLayer
        }
    }
    
    private func configureSession() {
        let session = AVCaptureSession()
        
        let deviceDiscoverySession = AVCaptureDevice.DiscoverySession(deviceTypes:[.builtInWideAngleCamera],
                                                                      mediaType: AVMediaType.video,
                                                                      position: .back)
        
        guard let captureDevice = deviceDiscoverySession.devices.first,
            let videoDeviceInput = try? AVCaptureDeviceInput(device: captureDevice),
            session.canAddInput(videoDeviceInput)
            else { return }
        session.addInput(videoDeviceInput)
        
        let videoOutput = AVCaptureVideoDataOutput()
        videoOutput.alwaysDiscardsLateVideoFrames = true
        videoOutput.videoSettings = [kCVPixelBufferPixelFormatTypeKey as String: Int(kCVPixelFormatType_420YpCbCr8BiPlanarFullRange)]
        videoOutput.setSampleBufferDelegate(self, queue: queue)
        
        session.addOutput(videoOutput)
        session.sessionPreset = .vga640x480
        
        let captureConnection = videoOutput.connection(with: .video)
        captureConnection?.isEnabled = true
        
        let dimensions  = CMVideoFormatDescriptionGetDimensions((captureDevice.activeFormat.formatDescription))
        videoSize.width = CGFloat(dimensions.width)
        videoSize.height = CGFloat(dimensions.height)
        
        self.session = session
    }
    
    private func drawRecognizedObjects(_ objects:[RecognizedObject]) {
        guard let previewLayer = previewLayer else { return }

        objectsLayer = GeometryUtils.createLayer(forRecognizedObjects: objects,
                                              inFrame: previewLayer.frame)
        
        previewLayer.addSublayer(objectsLayer)
        previewLayer.setNeedsDisplay()
    }
}

extension LiveCameraViewController: AVCaptureVideoDataOutputSampleBufferDelegate {
    
    func captureOutput(_ output: AVCaptureOutput,
                       didOutput sampleBuffer: CMSampleBuffer,
                       from connection: AVCaptureConnection) {
        if isRecognizing {
            return
        }
        
        guard let pixelBuffer = CMSampleBufferGetImageBuffer(sampleBuffer) else {
            return
        }
        
        objectsLayer.removeFromSuperlayer()
        isRecognizing = true
        
        objectRecognizer.recognize(fromPixelBuffer: pixelBuffer) { [weak self] objects in
            DispatchQueue.main.async {
                self?.drawRecognizedObjects(objects)
                self?.isRecognizing = false
            }
        }
        
    }
    
}

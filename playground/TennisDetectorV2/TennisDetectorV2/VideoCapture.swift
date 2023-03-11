//
//  VideoCapture.swift
//
//  Copyright © 2022 Jason Zhang. All rights reserved.
//

import UIKit
import AVFoundation
import CoreVideo

public protocol VideoCaptureDelegate: AnyObject {
    func videoCapture(_ capture: VideoCapture, didCaptureVideoFrame: CVPixelBuffer?, timestamp: CMTime)
}

public class VideoCapture: NSObject {
    public var previewLayer: AVCaptureVideoPreviewLayer?
    public weak var delegate: VideoCaptureDelegate?

    let captureSession = AVCaptureSession()
    let videoOutput = AVCaptureVideoDataOutput()
    let queue = DispatchQueue(label: "Jason Camera Queue")
    private var fps = 60 // default setting, will be override later on with the maximum fps from system
    
    var lastTimestamp = CMTime()
    
    public func setUp(completion: @escaping (Bool) -> Void) {
        self.setUpCamera(completion: { success in
            completion(success)
        })
    }
    
    func setUpCamera(completion: @escaping (_ success: Bool) -> Void) {
        
        captureSession.sessionPreset = .vga640x480
        captureSession.beginConfiguration()
        
        guard let captureDevice = AVCaptureDevice.default(for: .video) else {
            print("Error: no video devices available")
            return
        }
        
        let (activeFormat, maxRate) = findMaxFpsFormat(camera: captureDevice)
        fps = Int(maxRate)
        
        try! captureDevice.lockForConfiguration()
        captureDevice.activeFormat = activeFormat
        captureDevice.activeVideoMinFrameDuration = CMTimeMake(value: 1, timescale: Int32(maxRate))
        captureDevice.activeVideoMaxFrameDuration = CMTimeMake(value: 1, timescale: Int32(maxRate))
        try! captureDevice.unlockForConfiguration()
        
        guard let videoInput = try? AVCaptureDeviceInput(device: captureDevice) else {
            print("Error: could not create AVCaptureDeviceInput")
            return
        }
        
        if captureSession.canAddInput(videoInput) {
            captureSession.addInput(videoInput)
        }
        
        let previewLayer = AVCaptureVideoPreviewLayer(session: captureSession)
        previewLayer.videoGravity = AVLayerVideoGravity.resize
        previewLayer.connection?.videoOrientation = .portrait
        self.previewLayer = previewLayer
        
        videoOutput.alwaysDiscardsLateVideoFrames = false

        videoOutput.setSampleBufferDelegate(self, queue: queue)
        if captureSession.canAddOutput(videoOutput) {
            captureSession.addOutput(videoOutput)
        }
        
        // We want the buffers to be in portrait orientation otherwise they are
        // rotated by 90 degrees. Need to set this _after_ addOutput()!
        videoOutput.connection(with: AVMediaType.video)?.videoOrientation = .portrait
        
        captureSession.commitConfiguration()
        
        try! captureDevice.unlockForConfiguration()
        
        let success = true
        completion(success)
    }
    
    public func start() {
        if !captureSession.isRunning {
            captureSession.startRunning()
        }
    }
    
    public func stop() {
        if captureSession.isRunning {
            captureSession.stopRunning()
        }
    }
    
    private func findMaxFpsFormat(camera: AVCaptureDevice) -> (AVCaptureDevice.Format, Float64) {
        
        var maxRate: Float64 = (camera.formats.first?.videoSupportedFrameRateRanges.first!.maxFrameRate)!;
        let activeFormat = camera.formats.max(by: { format1, format2 in
            let maxRate1 = format1.videoSupportedFrameRateRanges.max(by: { range1, range2 in
                range1.maxFrameRate <= range2.maxFrameRate
            })!
            let maxRate2 = format2.videoSupportedFrameRateRanges.max(by: { range1, range2 in
                range1.maxFrameRate <= range2.maxFrameRate
            })!
            let currentMax = max(maxRate1.maxFrameRate, maxRate2.maxFrameRate)
            maxRate = currentMax > maxRate ? currentMax: maxRate
            
            return maxRate1.maxFrameRate <= maxRate2.maxFrameRate
        })!
        
        print(activeFormat)
        
        return (activeFormat, maxRate);
    }
}

extension VideoCapture: AVCaptureVideoDataOutputSampleBufferDelegate {
    public func captureOutput(_ output: AVCaptureOutput, didOutput sampleBuffer: CMSampleBuffer, from connection: AVCaptureConnection) {
        // Because lowering the capture device's FPS looks ugly in the preview,
        // we capture at full speed but only call the delegate at its desired
        // framerate.
        let timestamp = CMSampleBufferGetPresentationTimeStamp(sampleBuffer)
        NSLog("timestamp = %f", CMTimeGetSeconds(CMSampleBufferGetPresentationTimeStamp(sampleBuffer)));
        let deltaTime = timestamp - lastTimestamp
        
        if deltaTime >= CMTimeMake(value: 1, timescale: Int32(fps)) {
            lastTimestamp = timestamp
            let imageBuffer = CMSampleBufferGetImageBuffer(sampleBuffer)
            delegate?.videoCapture(self, didCaptureVideoFrame: imageBuffer, timestamp: timestamp)
        }
    }
}


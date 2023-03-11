//
//  BoundingBoxView.swift
//  TennisDetectorV2
//
//  Created by Jason Zhang on 11/3/2023.
//

import SwiftUI

//
//  DrawingBoundingBoxView.swift
//  SSDMobileNet-CoreML
//
//  Copyright © 2022 Jason Zhang. All rights reserved.
//

import UIKit
import Vision

struct MyView: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> MyViewController {
        let viewController = MyViewController()
        return viewController
    }
    func updateUIViewController(_ uiViewController: MyViewController, context: Context) {
    }
}

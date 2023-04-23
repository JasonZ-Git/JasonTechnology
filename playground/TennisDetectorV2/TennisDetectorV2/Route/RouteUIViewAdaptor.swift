//
//  RouteUIViewAdaptor.swift
//  TennisDetectorV2
//
//  Created by Jason Zhang on 23/4/2023.
//

import SwiftUI

//  Copyright © 2022 Jason Zhang. All rights reserved.
//

import UIKit
import Vision

struct RouteUIViewAdaptor: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> RouteViewController {
        let viewController = RouteViewController()
        return viewController
    }
    func updateUIViewController(_ uiViewController: RouteViewController, context: Context) {
    }
}

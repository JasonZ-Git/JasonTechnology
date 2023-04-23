
//  Created by Jason Zhang on 11/3/2023.
//

import SwiftUI

//  Copyright © 2022 Jason Zhang. All rights reserved.
//

import UIKit
import Vision

struct BoundingBoxUIViewAdaptor: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> BoundingBoxViewController {
        let viewController = BoundingBoxViewController()
        return viewController
    }
    func updateUIViewController(_ uiViewController: BoundingBoxViewController, context: Context) {
    }
}

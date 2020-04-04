//
//  CircleImage.swift
//  JasonDictionary
//
//  Created by Jason Zhang on 4/4/20.
//  Copyright © 2020 Jason Zhang. All rights reserved.
//

import SwiftUI

struct CircleImage: View {
    var body: some View {
        Image("turtlerock").clipShape(Circle())
            .overlay(Circle().stroke(Color.white, lineWidth: 4))
        .shadow(radius: 10)
    }
}

struct CircleImage_Previews: PreviewProvider {
    static var previews: some View {
        CircleImage()
    }
}

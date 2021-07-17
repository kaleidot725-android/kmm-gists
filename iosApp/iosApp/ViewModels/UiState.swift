//
//  UiState.swift
//  iosApp
//
//  Created by kaleidot725 on 2021/07/17.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

class UiState<Element> {
    let isSuccessful: Bool
    
    let value: Element
    
    init (isSuccessful: Bool, value: Element) {
        self.isSuccessful = isSuccessful
        self.value = value
    }
}

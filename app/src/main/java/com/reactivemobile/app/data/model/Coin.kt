package com.reactivemobile.app.data.model

data class Coin(val face: String) {
    val value: String
        get() = face
}
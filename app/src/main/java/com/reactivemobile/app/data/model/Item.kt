package com.reactivemobile.app.data.model

data class Item(val face: String) {
    val value: String
        get() = face
}
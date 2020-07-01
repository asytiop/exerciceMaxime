package com.example.exercicemaxime.model

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ktx.delegates.attribute
import com.parse.ktx.delegates.floatAttribute
import com.parse.ktx.delegates.stringAttribute

@ParseClassName("Product")
class Product: ParseObject() {
    var title: String by stringAttribute()
    var price: Float by floatAttribute()
    var picture: ParseFile by attribute<ParseFile>()
}
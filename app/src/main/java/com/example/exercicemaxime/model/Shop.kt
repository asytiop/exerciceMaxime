package com.example.exercicemaxime.model

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ktx.delegates.*

@ParseClassName("Shop")
class Shop: ParseObject() {
    var title: String by stringAttribute()
    var rating: Float by floatAttribute()
    var openingHour: Int by intAttribute()
    var closingHour: Int by intAttribute()
    var logo: ParseFile by attribute<ParseFile>()
    var distance: Float by floatAttribute()
}
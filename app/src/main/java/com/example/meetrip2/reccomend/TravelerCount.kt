package com.example.meetrip2.reccomend

data class TravelerCount (val raw: Raw? = null, val districtName: String = "" ){
    data class Raw(val travelerCount: Int)
}
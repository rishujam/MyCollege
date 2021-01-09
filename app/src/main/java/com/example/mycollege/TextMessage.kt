package com.example.mycollege

data class TextMessage(
       val msg:String,
       val time:Long
){
    constructor():this("",1)
}

package com.example.mycollege

data class TextMessage(
       val msg:String,
       val time:Long,
       val username:String
){
    constructor():this("",1,"Anonymous")
}

package com.quintonpyx.chatapplication

class Message {
    var message:String? = null
    var senderId: String? = null

    // default constructor
    constructor(){}

    constructor(message:String?,senderId:String?){
        this.message = message
        this.senderId = senderId
    }


}
package com.example.muber

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
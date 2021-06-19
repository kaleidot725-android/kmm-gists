package jp.kaleidot725.githubclient

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
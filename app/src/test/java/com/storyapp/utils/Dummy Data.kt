package com.storyapp.utils

import com.storyapp.response.Login
import com.storyapp.response.LoginResult

object DummyData {

    data class loginData(
        var email: String,
        var password: String
    )

    fun generateDummyRequestLogin(): loginData {
        return loginData("kopret1@gmail.com", "12345678")
    }

    fun generateDummyResponseLogin(): Login {
        val newLoginResult = LoginResult("qwerty", "kevin", "ini-token")
        return Login(newLoginResult,false, "Login successfully")
    }
}
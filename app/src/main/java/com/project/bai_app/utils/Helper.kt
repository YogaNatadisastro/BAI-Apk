package com.project.bai_app.utils

object Helper {

    fun validateInput(
        username: String,
        email: String,
        password: String,
        adminCode: String
    ): String? {

        val validations = listOf(
            username to "Username tidak boleh kosong",
            email to "Email tidak boleh kosong",
            password to "Password tidak boleh kosong",
            adminCode to "Khusus Admin"
        )

        validations.forEach { (value, error) ->
            if (value.isBlank()) return error
        }

        if (!email.contains('@') || !email.contains(".")) return "Email tidak valid"
        if (password.length < 6) return "Password minimal 6 karater"

        return null
    }
}
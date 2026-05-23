package com.project.bai_app.di.model.signup

import com.google.gson.annotations.SerializedName

data class SignupRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("admin_code")
	val adminCode: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

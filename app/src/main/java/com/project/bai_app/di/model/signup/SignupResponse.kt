package com.project.bai_app.di.model.signup

import com.google.gson.annotations.SerializedName

data class SignupResponse(

	@field:SerializedName("role")
	val role: Role? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class Role(

	@field:SerializedName("role_name")
	val roleName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

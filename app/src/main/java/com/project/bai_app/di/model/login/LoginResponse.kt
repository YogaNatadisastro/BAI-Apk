package com.project.bai_app.di.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("user_info")
	val user: User? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null
)

data class Role(

	@field:SerializedName("role_name")
	val roleName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class User(

	@field:SerializedName("role")
	val role: Role? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("username")
	val username: String? = null
)

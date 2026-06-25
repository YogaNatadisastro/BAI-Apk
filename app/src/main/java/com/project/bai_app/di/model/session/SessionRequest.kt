package com.project.bai_app.di.model.session

import com.google.gson.annotations.SerializedName

data class SessionRequest(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("patient")
	val patient: Patient? = null
)

data class Patient(
	@field:SerializedName("contact")
	val contact: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("birth_date")
	val birthDate: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

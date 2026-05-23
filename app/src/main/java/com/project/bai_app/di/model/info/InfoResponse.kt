package com.project.bai_app.di.model.info

import com.google.gson.annotations.SerializedName

data class InfoResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("patinet_name")
	val patinetName: String? = null,

	@field:SerializedName("age")
	val age: String? = null
)

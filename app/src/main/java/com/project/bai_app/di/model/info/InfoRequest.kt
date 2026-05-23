package com.project.bai_app.di.model.info

import com.google.gson.annotations.SerializedName

data class InfoRequest(

	@field:SerializedName("patinet_name")
	val patinetName: String? = null,

	@field:SerializedName("age")
	val age: Int? = null
)

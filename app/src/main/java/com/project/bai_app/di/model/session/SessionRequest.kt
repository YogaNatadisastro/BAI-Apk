package com.project.bai_app.di.model.session

import com.google.gson.annotations.SerializedName

data class SessionRequest(

	@field:SerializedName("user_id")
	val userId: Int? = null
)

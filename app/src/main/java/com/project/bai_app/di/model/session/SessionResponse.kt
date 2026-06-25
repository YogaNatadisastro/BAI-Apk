package com.project.bai_app.di.model.session

import com.google.gson.annotations.SerializedName

data class SessionResponse(

	@field:SerializedName("session_id")
	val sessionId: Int? = null
)

package com.project.bai_app.di.model.gad7

import com.google.gson.annotations.SerializedName

data class GadResponse(

	@field:SerializedName("session_id")
	val sessionId: Int? = null,

	@field:SerializedName("assessment_id")
	val assessmentId: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)

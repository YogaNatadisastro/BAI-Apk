package com.project.bai_app.di.model.hads

import com.google.gson.annotations.SerializedName

data class HadsResponse(

	@field:SerializedName("session_id")
	val sessionId: Int? = null,

	@field:SerializedName("assessment_id")
	val assessmentId: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)

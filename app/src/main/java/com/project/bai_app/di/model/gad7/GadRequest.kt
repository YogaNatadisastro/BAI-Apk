package com.project.bai_app.di.model.gad7

import com.google.gson.annotations.SerializedName

data class GadRequest(

	@field:SerializedName("answers")
	val answers: List<AnswersItem?>? = null,

	@field:SerializedName("session_id")
	val sessionId: Int? = null
)

data class AnswersItem(

	@field:SerializedName("score_value")
	val scoreValue: Int? = null,

	@field:SerializedName("question_id")
	val questionId: Int? = null
)

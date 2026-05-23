package com.project.bai_app.di.model.form

import com.google.gson.annotations.SerializedName

data class FormRequest(

	@field:SerializedName("answers")
	val answers: List<AnswersItem>
)

data class AnswersItem(

	@field:SerializedName("answers_id")
	val answersId: Int? = null,

	@field:SerializedName("question_bai_id")
	val questionBaiId: Int? = null
)

package com.project.bai_app.di.model.result

import com.google.gson.annotations.SerializedName

data class ResultResponse(

	@field:SerializedName("ResultResponse")
	val resultResponse: List<ResultResponseItem>? = null
)

data class AnswersItem(

	@field:SerializedName("question")
	val question: Question? = null,

	@field:SerializedName("answer")
	val answer: Answer? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Question(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("questions_text")
	val questionsText: String? = null
)

data class ResultResponseItem(

	@field:SerializedName("form_info")
	val formInfo: FormInfo? = null,

	@field:SerializedName("form_id")
	val formId: Int? = null,

	@field:SerializedName("answers")
	val answers: List<AnswersItem?>? = null,

	@field:SerializedName("total_score")
	val totalScore: Int? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("user")
	val user: User? = null
)

data class Answer(

	@field:SerializedName("score")
	val score: Int? = null,

	@field:SerializedName("answers")
	val answers: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class FormInfo(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("patinet_name")
	val patinetName: String? = null,

	@field:SerializedName("age")
	val age: Int? = null
)

data class User(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

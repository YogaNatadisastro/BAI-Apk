package com.project.bai_app.di.model.questions

import com.google.gson.annotations.SerializedName

data class QuestionsResponse(

	@field:SerializedName("QuestionsResponse")
	val questionsResponse: List<QuestionsResponseItem>? = null
)

data class QuestionsResponseItem(

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("sub_category")
	val subCategory: String? = null,

	@field:SerializedName("order_number")
	val orderNumber: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("question_type_id")
	val questionTypeId: Int? = null
)

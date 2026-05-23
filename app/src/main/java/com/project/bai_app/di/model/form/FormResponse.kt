package com.project.bai_app.di.model.form

import com.google.gson.annotations.SerializedName

data class FormResponse(

	@field:SerializedName("total_score")
	val totalScore: Int? = null,

	@field:SerializedName("category")
	val category: String? = null
)

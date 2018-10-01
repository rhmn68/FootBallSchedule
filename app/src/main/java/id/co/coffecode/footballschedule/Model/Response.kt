package id.co.coffecode.footballschedule.Model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)
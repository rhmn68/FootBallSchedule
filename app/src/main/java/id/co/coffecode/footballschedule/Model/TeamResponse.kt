package id.co.coffecode.footballschedule.Model

import com.google.gson.annotations.SerializedName
data class TeamResponse(

	@field:SerializedName("teams")
	val teams: List<TeamsItem>? = null
)
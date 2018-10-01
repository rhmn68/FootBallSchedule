package id.co.coffecode.footballschedule.Model

import com.google.gson.annotations.SerializedName

data class MatchResponse(
        @SerializedName("results")
        val results: List<MatchItem>
)
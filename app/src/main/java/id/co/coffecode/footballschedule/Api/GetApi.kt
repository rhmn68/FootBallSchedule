package id.co.coffecode.footballschedule.Api

import id.co.coffecode.footballschedule.BuildConfig

object GetApi{

    fun getLastMatch(id: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventslast.php?id" + id
    }

    fun getNextMatch(id: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + id
    }

}
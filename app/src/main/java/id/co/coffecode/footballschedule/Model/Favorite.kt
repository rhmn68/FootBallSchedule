package id.co.coffecode.footballschedule.Model

data class Favorite(val idEvents: String?,
                    val strAwayTeam: String?,
                    val strHomeTeam: String?,
                    val inAwayScore: String?,
                    val intHomeScore: String?,
                    val dateEvent: String?){
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_EVENTS: String = "ID_EVENTS"
        const val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
        const val STR_HOME_TEAM: String = "STR_HOME_TEAM"
        const val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
        const val INT_HOME_SCORE: String = "INT_HOME_SCORE"
        const val DATE_EVENT: String = "DATE_EVENT"
    }
}
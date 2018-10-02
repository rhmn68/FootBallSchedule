package id.co.coffecode.footballschedule.View

import id.co.coffecode.footballschedule.Model.TeamsItem

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(dataTeamHome: List<TeamsItem>?, dataTeamAway: List<TeamsItem>?)
}
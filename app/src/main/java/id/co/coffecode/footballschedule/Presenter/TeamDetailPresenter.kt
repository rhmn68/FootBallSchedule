package id.co.coffecode.footballschedule.Presenter

import com.google.gson.Gson
import id.co.coffecode.footballschedule.Api.ApiRepository
import id.co.coffecode.footballschedule.View.TeamDetailView
import org.jetbrains.anko.doAsync
import id.co.coffecode.footballschedule.Api.GetApi
import id.co.coffecode.footballschedule.Model.TeamResponse
import org.jetbrains.anko.uiThread

class TeamDetailPresenter(private val teamDetailView: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {

    fun getTeamDetail(idHomeTeam:String?, idAwayTeam:String?){
        doAsync {
            teamDetailView.showLoading()

            val dataHomeTeam = gson.fromJson(apiRepository
                    .doRequest(GetApi.getTeamDetail(idHomeTeam)),
                    TeamResponse::class.java)

            val dataAwayTeam = gson.fromJson(apiRepository
                    .doRequest(GetApi.getTeamDetail(idAwayTeam)),
                    TeamResponse::class.java)

            uiThread {
                teamDetailView.hideLoading()
                teamDetailView.showTeamDetail(dataHomeTeam.teams, dataAwayTeam.teams)
            }
        }
    }
}
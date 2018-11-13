package id.co.coffecode.footballschedule.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import id.co.coffecode.footballschedule.Api.ApiRepository
import id.co.coffecode.footballschedule.Model.EventsItem
import id.co.coffecode.footballschedule.Model.TeamsItem
import id.co.coffecode.footballschedule.Presenter.TeamDetailPresenter
import id.co.coffecode.footballschedule.R
import id.co.coffecode.footballschedule.Utils.gone
import id.co.coffecode.footballschedule.Utils.invisible
import id.co.coffecode.footballschedule.Utils.visible
import id.co.coffecode.footballschedule.View.TeamDetailView
import kotlinx.android.synthetic.main.activity_team_detail.*

const val INTENT_TEAM_DETAIL = "INTENT_TEAM_DETAIL"

class TeamDetailActivity : AppCompatActivity(), TeamDetailView{

    lateinit var teamDetailPresenter: TeamDetailPresenter

    override fun showLoading() {
        progressBarDetail.visible()
        scrollViewData.invisible()
    }

    override fun hideLoading() {
        progressBarDetail.gone()
        scrollViewData.visible()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        val item = intent.getParcelableExtra<EventsItem>(INTENT_TEAM_DETAIL)

        val apiRepository = ApiRepository()
        val gson = Gson()

        Log.d("coba","In Detail : "+item)
        teamDetailPresenter = TeamDetailPresenter(this, apiRepository, gson)
        teamDetailPresenter.getTeamDetail(item.idHomeTeam, item.idAwayTeam)

        setupLayout(item)
    }

    private fun setupLayout(item: EventsItem) {
        // Date Event
        textViewDateEvent.text = item.dateEvent

        //Name Team
        textViewStrTeamHome.text = item.strHomeTeam
        textViewStrTeamAway.text = item.strAwayTeam

        //Goal Details
        textViewstrHomeGoalDetails.text = item.strHomeGoalDetails
        textViewstrAwayGoalDetails.text = item.strAwayGoalDetails

        //Shoots Detail
        textViewintHomeShoots.text = item.intHomeShots
        textViewintAwayShoots.text = item.intAwayShots

        //Goal Keeper
        textViewstrHomeLineupGoalkeeper.text = item.strHomeLineupGoalkeeper
        textViewstrAwayLineupGoalkeeper.text = item.strAwayLineupGoalkeeper

        //Defense
        textViewstrHomeLineupDefense.text = item.strHomeLineupDefense
        textViewstrAwayLineupDefense.text = item.strAwayLineupDefense

        //Midfield
        textViewstrHomeLineupMidfield.text = item.strHomeLineupMidfield
        textViewstrAwayLineupMidfield.text = item.strAwayLineupMidfield

        //Forward
        textViewstrHomeLineupForward.text = item.strHomeLineupForward
        textViewstrAwayLineupForward.text = item.strAwayLineupForward

        //Substitutes
        textViewstrHomeLineupSubstitutes.text = item.strHomeLineupSubstitutes
        textViewstrAwayLineupSubstitutes.text = item.strAwayLineupSubstitutes
    }

    override fun showTeamDetail(dataTeamHome: List<TeamsItem>?, dataTeamAway: List<TeamsItem>?) {
        Picasso.get()
                .load(dataTeamHome!![0].strTeamBadge)
                .into(imageViewTeamBadgeHome)

        Picasso.get()
                .load(dataTeamAway!![0].strTeamBadge)
                .into(imageViewTeamBadgeAway)
    }
}

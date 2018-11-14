package id.co.coffecode.footballschedule.Activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import id.co.coffecode.footballschedule.Api.ApiRepository
import id.co.coffecode.footballschedule.Database.database
import id.co.coffecode.footballschedule.Model.EventsItem
import id.co.coffecode.footballschedule.Model.Favorite
import id.co.coffecode.footballschedule.Model.TeamsItem
import id.co.coffecode.footballschedule.Presenter.TeamDetailPresenter
import id.co.coffecode.footballschedule.R
import id.co.coffecode.footballschedule.Utils.gone
import id.co.coffecode.footballschedule.Utils.invisible
import id.co.coffecode.footballschedule.Utils.visible
import id.co.coffecode.footballschedule.View.TeamDetailView
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar

const val INTENT_TEAM_DETAIL = "INTENT_TEAM_DETAIL"

class TeamDetailActivity : AppCompatActivity(), TeamDetailView{
    private lateinit var teamDetailPresenter: TeamDetailPresenter
    private var menuItem: Menu? = null
    private var favoriteYes: Boolean = false
    private val eventItem: MutableList<EventsItem> = mutableListOf()
    private var item: EventsItem? = null

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

        item = intent.getParcelableExtra<EventsItem>(INTENT_TEAM_DETAIL)

        val apiRepository = ApiRepository()
        val gson = Gson()

        teamDetailPresenter = TeamDetailPresenter(this, apiRepository, gson)
        teamDetailPresenter.getTeamDetail(item?.idHomeTeam, item?.idAwayTeam)
        teamDetailPresenter.getEvents(item?.idEvent)

        setupLayout(item)
    }

    private fun setupLayout(item: EventsItem?) {
        // Date Event
        textViewDateEvent.text = item?.dateEvent

        //Name Team
        textViewStrTeamHome.text = item?.strHomeTeam
        textViewStrTeamAway.text = item?.strAwayTeam

        //Goal Details
        textViewstrHomeGoalDetails.text = item?.strHomeGoalDetails
        textViewstrAwayGoalDetails.text = item?.strAwayGoalDetails

        //Shoots Detail
        textViewintHomeShoots.text = item?.intHomeShots
        textViewintAwayShoots.text = item?.intAwayShots

        //Goal Keeper
        textViewstrHomeLineupGoalkeeper.text = item?.strHomeLineupGoalkeeper
        textViewstrAwayLineupGoalkeeper.text = item?.strAwayLineupGoalkeeper

        //Defense
        textViewstrHomeLineupDefense.text = item?.strHomeLineupDefense
        textViewstrAwayLineupDefense.text = item?.strAwayLineupDefense

        //Midfield
        textViewstrHomeLineupMidfield.text = item?.strHomeLineupMidfield
        textViewstrAwayLineupMidfield.text = item?.strAwayLineupMidfield

        //Forward
        textViewstrHomeLineupForward.text = item?.strHomeLineupForward
        textViewstrAwayLineupForward.text = item?.strAwayLineupForward

        //Substitutes
        textViewstrHomeLineupSubstitutes.text = item?.strHomeLineupSubstitutes
        textViewstrAwayLineupSubstitutes.text = item?.strAwayLineupSubstitutes
    }

    override fun showTeamDetail(dataTeamHome: List<TeamsItem>?, dataTeamAway: List<TeamsItem>?) {
        Picasso.get()
                .load(dataTeamHome!![0].strTeamBadge)
                .into(imageViewTeamBadgeHome)
        Picasso.get()
                .load(dataTeamAway!![0].strTeamBadge)
                .into(imageViewTeamBadgeAway)
    }

    override fun showEvent(dataEvents: List<EventsItem>?) {
        eventItem.clear()
        eventItem.addAll(dataEvents!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.match_detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favoriteMatch -> {
                if (favoriteYes) removeFromFavorite() else addToFavorite()
                favoriteYes = !favoriteYes
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.ID_EVENTS to eventItem[0].idEvent,
                        Favorite.STR_HOME_TEAM to eventItem[0].strHomeTeam,
                        Favorite.STR_AWAY_TEAM to eventItem[0].strAwayTeam,
                        Favorite.INT_HOME_SCORE to eventItem[0].intHomeScore,
                        Favorite.INT_AWAY_SCORE to eventItem[0].intAwayScore,
                        Favorite.DATE_EVENT to eventItem[0].dateEvent)
            }
            snackbar(rlTeamDetail,"Added to favorite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(rlTeamDetail, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "ID_EVENTS = {id}",
                        "id" to item?.idEvent.toString() )
            }
            snackbar(rlTeamDetail, "Removed rom favorite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(rlTeamDetail, e.localizedMessage).show()
        }
    }

    private fun setFavorite(){
        if (favoriteYes){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,R.drawable.ic_star_black_24dp)
        }else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,R.drawable.ic_star_border_black_24dp)
        }
    }
}

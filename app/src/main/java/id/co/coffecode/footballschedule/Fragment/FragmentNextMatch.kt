package id.co.coffecode.footballschedule.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import id.co.coffecode.footballschedule.Adapter.MatchAdapter
import id.co.coffecode.footballschedule.Api.ApiRepository
import id.co.coffecode.footballschedule.INTENT_TEAM_DETAIL
import id.co.coffecode.footballschedule.Model.EventsItem
import id.co.coffecode.footballschedule.Presenter.NextMatchPresenter

import id.co.coffecode.footballschedule.R
import id.co.coffecode.footballschedule.TeamDetailActivity
import id.co.coffecode.footballschedule.Utils.invisible
import id.co.coffecode.footballschedule.Utils.visible
import id.co.coffecode.footballschedule.View.NextMatchView
import kotlinx.android.synthetic.main.fragment_fragment_next_match.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class FragmentNextMatch : Fragment(), NextMatchView {

    lateinit var apiRepository: ApiRepository
    lateinit var gson: Gson
    lateinit var presenterNext: NextMatchPresenter
    lateinit var matchAdapter: MatchAdapter

    var matchItem: MutableList<EventsItem> = mutableListOf()

    override fun showLoading() {
        progressBarNextMatch.visible()
        recyclerViewNextMatch.invisible()
        imageViewEmpty.invisible()
    }

    override fun hideLoading() {
        progressBarNextMatch.invisible()
        recyclerViewNextMatch.visible()
        imageViewEmpty.invisible()
    }

    override fun showEmptyData() {
        progressBarNextMatch.invisible()
        recyclerViewNextMatch.invisible()
        imageViewEmpty.visible()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiRepository = ApiRepository()
        gson = Gson()
        presenterNext = NextMatchPresenter(this,apiRepository, gson)

        matchAdapter = MatchAdapter(context, matchItem, {
            startActivity<TeamDetailActivity>(INTENT_TEAM_DETAIL to it)
        })

        presenterNext.getNextMatch("4328")
        swipeNextMatch.onRefresh {
            presenterNext.getNextMatch("4328")
        }

        recyclerViewNextMatch.setHasFixedSize(true)
        recyclerViewNextMatch.layoutManager = LinearLayoutManager(context)
        recyclerViewNextMatch.adapter = matchAdapter
    }

    override fun showNextMatch(data: List<EventsItem>?) {
        swipeNextMatch.isRefreshing = false
        matchItem.clear()
        matchItem.addAll(data!!)
        matchAdapter.notifyDataSetChanged()
    }
}

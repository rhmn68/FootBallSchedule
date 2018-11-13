package id.co.coffecode.footballschedule.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import id.co.coffecode.footballschedule.Activity.INTENT_TEAM_DETAIL
import id.co.coffecode.footballschedule.Adapter.MatchAdapter
import id.co.coffecode.footballschedule.Api.ApiRepository
import id.co.coffecode.footballschedule.Model.EventsItem
import id.co.coffecode.footballschedule.Presenter.LastMatchPresenter

import id.co.coffecode.footballschedule.R
import id.co.coffecode.footballschedule.Activity.TeamDetailActivity
import id.co.coffecode.footballschedule.Utils.invisible
import id.co.coffecode.footballschedule.Utils.visible
import id.co.coffecode.footballschedule.View.LastMatchView
import kotlinx.android.synthetic.main.fragment_fragment_last_match.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FragmentLastMatch : Fragment(), LastMatchView {

    lateinit var apiRepository: ApiRepository
    lateinit var gson: Gson
    lateinit var presenterLast: LastMatchPresenter
    lateinit var matchAdapter: MatchAdapter

    var matchItem: MutableList<EventsItem> = mutableListOf()

    override fun showLoading() {
        progressBarLastMatch.visible()
        recyclerViewLastMatch.invisible()
        imageViewEmpty.invisible()
    }

    override fun hideLoading() {
        progressBarLastMatch.invisible()
        recyclerViewLastMatch.visible()
        imageViewEmpty.invisible()
    }

    override fun showEmptyData() {
        progressBarLastMatch.invisible()
        recyclerViewLastMatch.invisible()
        imageViewEmpty.visible()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiRepository = ApiRepository()
        gson = Gson()
        presenterLast = LastMatchPresenter(this, apiRepository, gson)

        matchAdapter = MatchAdapter(context,matchItem,{
            startActivity<TeamDetailActivity>(INTENT_TEAM_DETAIL to it)
            Log.d("coba", "Item : "+it)
        })

        presenterLast.getLastMatch("4328")
        swipeLastMatch.onRefresh {
            presenterLast.getLastMatch("4328")
        }

        recyclerViewLastMatch.setHasFixedSize(true)
        recyclerViewLastMatch.layoutManager = LinearLayoutManager(context)
        recyclerViewLastMatch.adapter = matchAdapter
    }

    override fun showLastMatch(data: List<EventsItem>?) {
        swipeLastMatch.isRefreshing = false
        matchItem.clear()
        matchItem.addAll(data!!)
        matchAdapter.notifyDataSetChanged()
    }
}

package id.co.coffecode.footballschedule.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.coffecode.footballschedule.Adapter.MatchAdapter
import id.co.coffecode.footballschedule.Model.MatchItem
import id.co.coffecode.footballschedule.Presenter.MatchPresenter

import id.co.coffecode.footballschedule.R
import id.co.coffecode.footballschedule.Utils.invisible
import id.co.coffecode.footballschedule.Utils.visible
import id.co.coffecode.footballschedule.View.LastMatchView
import kotlinx.android.synthetic.main.fragment_fragment_last_match.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

class FragmentLastMatch : Fragment(), LastMatchView {

    lateinit var adapter : MatchAdapter
    lateinit var presenter: MatchPresenter
    lateinit var matchAdapter: MatchAdapter

    var matchItem: MutableList<MatchItem> = mutableListOf()

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

        presenter = MatchPresenter(this)

        matchAdapter = MatchAdapter(context,matchItem,{
            toast(""+it.dateEvent)
        })

        presenter.getLastMatch("133602")
        swipeLastMatch.onRefresh {
            presenter.getLastMatch("133602")
        }

        recyclerViewLastMatch.setHasFixedSize(true)
        recyclerViewLastMatch.layoutManager = LinearLayoutManager(context)
        recyclerViewLastMatch.adapter = matchAdapter
    }

    override fun showLastMatch(data: List<MatchItem>) {
        swipeLastMatch.isRefreshing = false
        matchItem.clear()
        matchItem.addAll(data)
        adapter.notifyDataSetChanged()
    }

}

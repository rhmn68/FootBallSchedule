package id.co.coffecode.footballschedule.View

import id.co.coffecode.footballschedule.Model.MatchItem

interface LastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showLastMatch(data: List<MatchItem>)
}
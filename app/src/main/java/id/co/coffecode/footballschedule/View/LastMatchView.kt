package id.co.coffecode.footballschedule.View

import id.co.coffecode.footballschedule.Model.EventsItem

interface LastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showLastMatch(data: List<EventsItem>?)
}
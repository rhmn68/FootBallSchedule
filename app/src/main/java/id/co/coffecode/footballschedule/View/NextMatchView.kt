package id.co.coffecode.footballschedule.View

import id.co.coffecode.footballschedule.Model.EventsItem

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showNextMatch(data: List<EventsItem>?)
}
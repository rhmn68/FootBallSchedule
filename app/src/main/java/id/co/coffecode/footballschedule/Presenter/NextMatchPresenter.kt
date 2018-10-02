package id.co.coffecode.footballschedule.Presenter

import com.google.gson.Gson
import id.co.coffecode.footballschedule.Api.ApiRepository
import id.co.coffecode.footballschedule.View.NextMatchView
import org.jetbrains.anko.doAsync
import id.co.coffecode.footballschedule.Api.GetApi
import id.co.coffecode.footballschedule.Model.MatchResponse
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val nextMatchView: NextMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {

    fun getNextMatch(id: String){
        nextMatchView.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(GetApi.getNextMatch(id)),
                    MatchResponse::class.java)

            uiThread {
                nextMatchView.hideLoading()
                try {
                    nextMatchView.showNextMatch(data.events)
                }catch (e:NullPointerException){
                    nextMatchView.showEmptyData()
                }
            }
        }
    }
}
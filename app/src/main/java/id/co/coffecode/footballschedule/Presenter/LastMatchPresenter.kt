package id.co.coffecode.footballschedule.Presenter

import android.util.Log
import com.google.gson.Gson
import id.co.coffecode.footballschedule.Api.ApiRepository
import id.co.coffecode.footballschedule.View.LastMatchView
import org.jetbrains.anko.doAsync
import id.co.coffecode.footballschedule.Api.GetApi
import org.jetbrains.anko.uiThread
import id.co.coffecode.footballschedule.Model.MatchResponse

class LastMatchPresenter(private val viewLast: LastMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {

    fun getLastMatch(id: String?){
        viewLast.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(GetApi.getLastMatch(id)),
                    MatchResponse::class.java)

            uiThread {
                viewLast.hideLoading()

                Log.d("review","data: "+data.events)
                try {
                    viewLast.showLastMatch(data.events)
                }catch (e:NullPointerException){
                    viewLast.showEmptyData()
                }
            }
        }
    }
}
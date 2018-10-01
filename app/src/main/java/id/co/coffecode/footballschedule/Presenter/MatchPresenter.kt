package id.co.coffecode.footballschedule.Presenter

import com.google.gson.Gson
import id.co.coffecode.footballschedule.Api.ApiRepository
import id.co.coffecode.footballschedule.View.LastMatchView
import org.jetbrains.anko.doAsync
import id.co.coffecode.footballschedule.Api.GetApi
import id.co.coffecode.footballschedule.Model.MatchResponse
import org.jetbrains.anko.uiThread

class MatchPresenter(val viewLast: LastMatchView) {

    val apiRepository = ApiRepository()
    val gson = Gson()

    fun getLastMatch(id: String?){
        viewLast.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(GetApi.getLastMatch(id)),
                    MatchResponse::class.java)

            uiThread {
                viewLast.hideLoading()
                viewLast.showLastMatch(data.results)
//                try {
//                    viewLast.showLastMatch(data.results)
//                }catch (e:NullPointerException){
//                    viewLast.showEmptyData()
//                }
            }
        }
    }

//    fun getNextMatch(id: String){
//        viewLast.showLoading()
//
//        doAsync {
//            val data = gson.fromJson(apiRepository
//                    .doRequest(GetApi.getNextMatch(id)),
//                    MatchResponse::class.java)
//
//            uiThread {
//                viewLast.hideLoading()
//
//                try {
//                    viewLast.showNextMatch(data.matchItem)
//                }catch (e:NullPointerException){
//                    viewLast.showEmptyData()
//                }
//            }
//        }
//    }
}
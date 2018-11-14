package id.co.coffecode.footballschedule.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.coffecode.footballschedule.Adapter.MatchAdapter
import id.co.coffecode.footballschedule.Database.database
import id.co.coffecode.footballschedule.Model.EventsItem
import id.co.coffecode.footballschedule.Model.Favorite
import id.co.coffecode.footballschedule.R
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

class FavoriteFragment : Fragment() {
    private var favorites: MutableList<EventsItem> = mutableListOf()
    private lateinit var adapter: MatchAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MatchAdapter(ctx, favorites){
            toast(""+it.idEvent)
        }
        rvFavoriteFragment.layoutManager = LinearLayoutManager(ctx)
        rvFavoriteFragment.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            Log.d("favoriteFragment","result: "+result)
//            val favorite = result.parseList(classParser<EventsItem>())
//            favorites.addAll(favorite)
        }
    }
}

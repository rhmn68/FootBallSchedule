package id.co.coffecode.footballschedule.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.coffecode.footballschedule.Model.MatchItem
import id.co.coffecode.footballschedule.R
import kotlinx.android.synthetic.main.item_match.view.*

class MatchAdapter(val context: Context?, val items: List<MatchItem>, val listener: (MatchItem) -> Unit)
    :RecyclerView.Adapter<MatchAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: MatchItem, listener: (MatchItem) -> Unit){
            view.textViewAwayTeam.text = item.strAwayTeam
            view.textViewHomeTeam.text = item.strHomeTeam
            view.textViewScoreAway.text = item.intAwayScore
            view.textViewScoreHome.text = item.intHomeScore
            view.textViewDateEvent.text = item.dateEvent

            itemView.setOnClickListener{listener(item)}
        }
    }
}
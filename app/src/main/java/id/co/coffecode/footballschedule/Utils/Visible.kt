package id.co.coffecode.footballschedule.Utils

import android.support.transition.Visibility
import android.view.View

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.gone(){
    visibility = View.GONE
}
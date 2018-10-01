package id.co.coffecode.footballschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import id.co.coffecode.footballschedule.Fragment.FragmentLastMatch
import id.co.coffecode.footballschedule.Fragment.FragmentNextMatch
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val fragmentLastMatch = FragmentLastMatch()
    val fragmentNextMatch = FragmentNextMatch()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.prevMatch->{
                addFragment(fragmentLastMatch)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nextMatch->{
                addFragment(fragmentNextMatch)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(fragmentLastMatch)

        bottomNavigationMain.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayoutMain, fragment)
                .commit()
    }
}

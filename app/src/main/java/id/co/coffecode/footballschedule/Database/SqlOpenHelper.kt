package id.co.coffecode.footballschedule.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.co.coffecode.footballschedule.Model.Favorite
import org.jetbrains.anko.db.*

class SqlOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteTeams.db", null, 1){

    companion object {

        private var instance: SqlOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): SqlOpenHelper{
            if (instance == null){
                instance = SqlOpenHelper(ctx.applicationContext)
            }
            return instance as SqlOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        //Create table
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.ID_EVENTS to TEXT + UNIQUE,
                Favorite.STR_AWAY_TEAM to TEXT,
                Favorite.STR_HOME_TEAM to TEXT,
                Favorite.INT_AWAY_SCORE to TEXT,
                Favorite.INT_HOME_SCORE to TEXT,
                Favorite.DATE_EVENT to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //Upgrade table
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.database: SqlOpenHelper
    get() = SqlOpenHelper.getInstance(applicationContext)
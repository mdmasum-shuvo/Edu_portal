package com.masum.edu_portal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.masum.edu_portal.room.datamodel.FoodItemCart

@Database(entities = [FoodItemCart::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDatabaseDao(): DatabaseDao

    companion object {
        private var instance: MyDatabase? = null

        @Synchronized
        fun getInstance(context: Context?): MyDatabase? {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context!!, MyDatabase::class.java, "db")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback).build()
            }
            return instance
        }

        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}
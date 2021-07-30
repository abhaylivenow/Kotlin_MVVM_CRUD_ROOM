package com.eazyalgo.mvvmpractice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase : RoomDatabase() {

    abstract val subscriberDao: SubscriberDao

    // boiler-plate code for making singleton class
    // same code every time we use room
    companion object{
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null
        fun getInstance(context: Context): SubscriberDatabase{
            synchronized(this) {
                var instance: SubscriberDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, SubscriberDatabase::class.java,
                        "subscriber_data_table"
                    ).build()
                }
                return instance
            }
        }
    }

}
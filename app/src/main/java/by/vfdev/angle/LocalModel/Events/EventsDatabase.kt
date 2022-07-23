package by.vfdev.angle.LocalModel.Events

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.vfdev.angle.RemoteModel.Events.Events

@Database(entities = [Events::class], version = 2)
abstract class EventsDatabase : RoomDatabase() {
    abstract fun eventsDao(): IEventsDao

    companion object {
        private var eventsDatabase : EventsDatabase? = null
        fun getDataBase(contextApplication: Context): EventsDatabase {
            if (eventsDatabase == null) {
                eventsDatabase = Room.databaseBuilder(
                    contextApplication,
                    EventsDatabase::class.java, "events_db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return eventsDatabase!!
        }
    }
}
package by.vfdev.angle.LocalModel.Events

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.vfdev.angle.RemoteModel.Events.Events

@Database(entities = [Events::class], version = 1)
abstract class EventsDatabase : RoomDatabase() {
    abstract fun eventDao(): IEventsDao

    companion object {
        private var eventDatabase : EventsDatabase? = null
        fun getDataBase(contextApplication: Context): EventsDatabase {
            if (eventDatabase == null) {
                eventDatabase = Room.databaseBuilder(
                    contextApplication,
                    EventsDatabase::class.java, "events_db"
                ).build()
            }
            return eventDatabase!!
        }
    }
}
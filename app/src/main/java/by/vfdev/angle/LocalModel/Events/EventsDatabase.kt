package by.vfdev.angle.LocalModel.Events

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.vfdev.angle.RemoteModel.Events.Events
import by.vfdev.angle.Utils.Converters

@Database(entities = [Events::class], version = 3)
@TypeConverters(Converters::class)
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
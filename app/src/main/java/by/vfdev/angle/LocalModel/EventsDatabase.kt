package by.vfdev.angle.LocalModel

import androidx.room.Database
import androidx.room.RoomDatabase
import by.vfdev.angle.RemoteModel.EventsLocation

@Database(entities = [EventsLocation::class], version = 1)
abstract class EventsDatabase : RoomDatabase() {
    abstract fun eventDao(): iEventsDao
}
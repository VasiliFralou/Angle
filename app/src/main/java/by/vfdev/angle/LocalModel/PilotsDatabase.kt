package by.vfdev.angle.LocalModel

import androidx.room.Database
import androidx.room.RoomDatabase
import by.vfdev.angle.RemoteModel.Pilots

@Database(entities = [Pilots::class], version = 1)
abstract class PilotsDatabase : RoomDatabase() {
    abstract fun pilotsDao() : iPilotsDao
}
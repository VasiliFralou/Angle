package by.vfdev.angle.LocalModel.Pilots

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.vfdev.angle.RemoteModel.Pilots.Pilots

@Database(entities = [Pilots::class], version = 1)
abstract class PilotsDatabase : RoomDatabase() {
    abstract fun pilotsDao() : IPilotsDao

    companion object {
        private var pilotsDatabase : PilotsDatabase? = null
        fun getDataBase(contextApplication: Context): PilotsDatabase {
            if (pilotsDatabase == null) {
                pilotsDatabase = Room.databaseBuilder(
                    contextApplication,
                    PilotsDatabase::class.java, "pilots_db"
                ).build()
            }
            return pilotsDatabase!!
        }
    }
}
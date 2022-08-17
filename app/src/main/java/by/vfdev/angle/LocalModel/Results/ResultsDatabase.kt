package by.vfdev.angle.LocalModel.Results

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.vfdev.angle.RemoteModel.Results.Results

@Database(entities = [Results::class], version = 3)
abstract class ResultsDatabase : RoomDatabase() {
    abstract fun resultsDao(): IResultsDao

    companion object {
        private var resultsDatabase : ResultsDatabase? = null
        fun getDataBase(contextApplication: Context): ResultsDatabase {
            if (resultsDatabase == null) {
                resultsDatabase = Room.databaseBuilder(
                    contextApplication,
                    ResultsDatabase::class.java, "results_db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return resultsDatabase!!
        }
    }
}
package by.vfdev.angle.LocalModel.Results

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.vfdev.angle.RemoteModel.Results.Results

@Dao
interface IResultsDao {

    @Insert
    suspend fun insertResults(results: MutableList<Results>)

    @Query("SELECT * FROM Results")
    suspend fun getAllResults(): MutableList<Results>

    @Query("DELETE FROM Results")
    suspend fun deleteAllResults()
}
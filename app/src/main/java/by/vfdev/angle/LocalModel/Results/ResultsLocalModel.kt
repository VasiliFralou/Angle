package by.vfdev.angle.LocalModel.Results

import android.content.Context
import by.vfdev.angle.RemoteModel.Results.Results
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResultsLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = ResultsDatabase.getDataBase(context).resultsDao()

    suspend fun insertResults(results: MutableList<Results>) {
        database.deleteAllResults()
        database.insertResults(results)
    }

    suspend fun getAllResults(): MutableList<Results> {
        return database.getAllResults()
    }
}
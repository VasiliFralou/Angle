package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.Results.ResultsLocalModel
import by.vfdev.angle.RemoteModel.Results.Results
import by.vfdev.angle.RemoteModel.Results.ResultsRemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ResultsRepository @Inject constructor(
    private val resultsRemoteModel: ResultsRemoteModel,
    private val resultsLocalModel: ResultsLocalModel) {

    suspend fun getDataResults(): Result<MutableList<Results>> = withContext(Dispatchers.IO) {

        var resultsList = resultsRemoteModel.getResultsRemoteData()

        if (resultsList.isNullOrEmpty()) {
            resultsList = resultsLocalModel.getAllResults()
        } else {
            launch {
                updateDataResultsFromDB()
            }
        }
        return@withContext Result.success(resultsList)
    }

    private suspend fun updateDataResultsFromDB(): MutableList<Results> {

        val resultsList = resultsRemoteModel.getResultsRemoteData()
        resultsLocalModel.insertResults(resultsList)

        return resultsList
    }
}
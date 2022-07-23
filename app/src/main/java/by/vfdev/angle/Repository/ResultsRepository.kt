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

    suspend fun getDataResults():
            Result<MutableList<Results>> = withContext(Dispatchers.IO) {

        val resultsListEthernet = resultsRemoteModel.getResultsRemoteData()
        var resultsLisLocal = resultsLocalModel.getAllResults()

        if (resultsLisLocal.isEmpty()) {
            resultsLisLocal = updateDataResultsFromDB(resultsListEthernet)
        } else {
            launch {
                updateDataResultsFromDB(resultsListEthernet)
            }

        }
        return@withContext Result.success(resultsLisLocal)
    }

    private suspend fun updateDataResultsFromDB(list: MutableList<Results>): MutableList<Results> {

        if (list.isNotEmpty()) {
            resultsLocalModel.insertResults(list)
        }
        return list
    }
}
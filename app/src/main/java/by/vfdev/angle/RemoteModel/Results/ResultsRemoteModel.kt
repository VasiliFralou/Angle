package by.vfdev.angle.RemoteModel.Results

import by.vfdev.angle.Api.ApiResults
import javax.inject.Inject

class ResultsRemoteModel @Inject constructor() {

    private val apiResults = ApiResults.create()

    suspend fun getResultsRemoteData(): MutableList<Results> {
        return try {
            val results: MutableList<Results> = apiResults.getResult().results
            results
        } catch (e: Exception) {
            mutableListOf()
        }
    }
}
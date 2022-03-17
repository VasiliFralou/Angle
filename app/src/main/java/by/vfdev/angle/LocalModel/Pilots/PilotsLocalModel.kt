package by.vfdev.angle.LocalModel.Pilots

import android.content.Context
import by.vfdev.angle.RemoteModel.Pilots.Pilots
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PilotsLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = PilotsDatabase.getDataBase(context).pilotsDao()

    suspend fun insertPilots(pilots: MutableList<Pilots>) {
        database.deleteAllPilots()
        database.insertPilots(pilots)
    }

    suspend fun getAllPilots(): MutableList<Pilots> {
        return database.getAllPilots()
    }
}
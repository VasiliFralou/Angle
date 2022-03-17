package by.vfdev.angle.RemoteModel.Pilots

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PilotsRemoteModelModule {

    @Binds
    abstract fun pilotsBinds(
        pilotsRM: PilotsRemoteModel
    ): IPilotsRemoteModel
}
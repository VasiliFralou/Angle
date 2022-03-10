package by.vfdev.angle.RemoteModel.Events

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EventsRemoteModelModule {

    @Binds
    abstract fun eventsBinds(
        eventsRM: EventsRemoteModel
    ): IEventsRemoteModel
}
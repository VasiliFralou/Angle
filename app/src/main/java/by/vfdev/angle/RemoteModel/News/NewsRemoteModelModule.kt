package by.vfdev.angle.RemoteModel.News

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsRemoteModelModule {

    @Binds
    abstract fun newsBinds(
        newsRM: NewsRemoteModel
    ): INewsRemoteModel
}
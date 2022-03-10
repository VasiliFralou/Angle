package by.vfdev.angle.RemoteModel.Gallery

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GalleryRemoteModelModule {

    @Binds
    abstract fun galleryBinds(
        galleryRM: GalleryRemoteModel
    ): IGalleryRemoteModel
}
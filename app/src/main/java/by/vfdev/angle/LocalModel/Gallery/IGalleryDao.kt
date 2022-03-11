package by.vfdev.angle.LocalModel.Gallery

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.vfdev.angle.RemoteModel.Gallery.Gallery

@Dao
interface IGalleryDao {

    @Insert
    suspend fun insertGallery(gallery: MutableList<Gallery>)

    @Query("SELECT * FROM Images")
    suspend fun getAllGallery(): MutableList<Gallery>

    @Query("DELETE FROM Images")
    suspend fun deleteAllImages()
}
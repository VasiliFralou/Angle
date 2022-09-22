package by.vfdev.angle.LocalModel.Media

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.vfdev.angle.RemoteModel.Media.Media

@Dao
interface IMediaDao {

    @Insert
    suspend fun insertMedia(media: MutableList<Media>)

    @Query("SELECT * FROM Media")
    suspend fun getAllMedia(): MutableList<Media>

    @Query("DELETE FROM Media")
    suspend fun deleteAllMedia()
}
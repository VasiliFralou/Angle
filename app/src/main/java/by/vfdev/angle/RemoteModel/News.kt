package by.vfdev.angle.RemoteModel

import com.google.firebase.database.Exclude

data class News(
    val title: String? = null,
    val description: String? = null,
    val date: String? = null,
    val urlImg: String? = null,
    val urlPost: String? = null,
    @Exclude val key: String? = null) {
}
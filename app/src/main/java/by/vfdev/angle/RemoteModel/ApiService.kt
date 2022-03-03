package by.vfdev.angle.RemoteModel

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

val BASE_URL = "https://raw.githubusercontent.com/VasiliFralou/Angle/master/app/src/main/java/by/vfdev/angle/JSON/"

interface ApiService {

    @GET("Events.json")
    suspend fun getEvents(): EventCallBack

    // Возвращаем объект ретрофит
    companion object Factory {
        fun create(): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}

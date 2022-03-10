package by.vfdev.angle.Api

import by.vfdev.angle.RemoteModel.Events.EventsCallBack
import by.vfdev.angle.Utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiEvents {

    @GET("Events.json")
    suspend fun getEvents(): EventsCallBack

    // Возвращаем объект ретрофит
    companion object Factory {
        fun create(): ApiEvents {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiEvents::class.java)
        }
    }
}

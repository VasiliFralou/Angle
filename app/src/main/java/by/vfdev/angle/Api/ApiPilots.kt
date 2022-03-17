package by.vfdev.angle.Api

import by.vfdev.angle.RemoteModel.Pilots.PilotsCallBack
import by.vfdev.angle.Utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiPilots {

    @GET("Pilots.json")
    suspend fun getPilots(): PilotsCallBack

    // Возвращаем объект ретрофит
    companion object Factory {
        fun create(): ApiPilots {
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
            return retrofit.create(ApiPilots::class.java)
        }
    }
}
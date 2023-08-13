package nguyen.dev.kotlinentrancetest.repository.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Environment {
    private const val TIME_OUT = 15L
    private const val serviceUrl = "http://streaming.nexlesoft.com:3001/"

    fun getRetrofit(block: OkHttpClient.Builder.() -> Unit): Retrofit {
        val clientBuilder = OkHttpClient().newBuilder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .callTimeout(TIME_OUT, TimeUnit.SECONDS)
        clientBuilder.block()
        return Retrofit.Builder()
            .baseUrl(serviceUrl)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
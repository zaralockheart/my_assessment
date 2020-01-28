package my.com.assessments.utilities

import my.com.assessments.model.Engineer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


fun service(): AppService {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    return Retrofit.Builder()
        .client(client)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .baseUrl("http://www.mocky.io/v2/")
        .build()
        .create(AppService::class.java)
}

interface AppService {

    @GET("5e2fca44320000600085850c")
    suspend fun getEngineers(): Engineer

}
package digitalusus.net.network

import digitalusus.net.model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitService {
    @FormUrlEncoded
    @POST("api/login")
    fun postLogin(@Field("email") email: String, @Field("password") password: String): Call<User>


    companion object {
        fun create(): RetrofitService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://6fe02460.ngrok.io/")
                .client(client)
                .build()
            return retrofit.create(RetrofitService::class.java)

        }
    }
}
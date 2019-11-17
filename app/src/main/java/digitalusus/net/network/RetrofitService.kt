package digitalusus.net.network

import digitalusus.net.model.*
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitService {
    @FormUrlEncoded
    @POST("api/login")
    fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UserResponse>

    @Multipart
    @POST("/api/user/reporting")
    fun postReport(
        @Part id: Int,
        @Part ruang: String,
        @Part isi: String,
        @Part gambar: String
    ): Call<PostResponse>

    @GET("/api/user/getReport/{id}")
    fun getListReport(@Path("id") id: Int): Call<ReportResponse>

    @GET("/api/user/getReportOne/{id}")
    fun getDetailReport(@Path("id") id: Int): Call<Report>

    @GET("/api/user/getUser/{id}")
    fun getUserProfile(@Path("id") id: Int): Call<UserResponse>

    companion object {
        fun create(): RetrofitService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://dd4c5c20.ngrok.io")
                .client(client)
                .build()
            return retrofit.create(RetrofitService::class.java)

        }
    }
}
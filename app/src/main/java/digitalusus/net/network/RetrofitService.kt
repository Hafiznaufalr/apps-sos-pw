package digitalusus.net.network

import digitalusus.net.model.*
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
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


    @FormUrlEncoded
    @POST("api/user/batalReport")
    fun cancelReport(
        @Field("id") id: Int
    ): Call<CancelResponse>

    @Multipart
    @POST("/api/user/reporting")
    fun postReport(
        @Part("id_user") id: RequestBody,
        @Part("ruang") ruang: RequestBody,
        @Part("isi") isi: RequestBody,
        @Part gambar: MultipartBody.Part
    ): Call<PostResponse>

    @GET("/api/user/getReport/{id_user}")
    fun getListReport(@Path("id_user") id: Int): Call<ReportResponse>

    @GET("/api/user/getReportOne/{id}")
    fun getDetailReport(@Path("id") id: Int): Call<ReportResponse>

    @GET("/api/user/getUser/{id_user}")
    fun getUserProfile(@Path("id_user") id: Int): Call<UserResponse>

    companion object {
        fun create(): RetrofitService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://710e8007.ngrok.io/")
                .client(client)
                .build()
            return retrofit.create(RetrofitService::class.java)

        }
    }
}
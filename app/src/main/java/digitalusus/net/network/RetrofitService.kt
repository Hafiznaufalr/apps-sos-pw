package digitalusus.net.network

import digitalusus.net.model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitService {
    @FormUrlEncoded
    @POST("api/login")
    fun postLogin(@Field("email") email: String,
                  @Field("password") password: String
    ): Call<UserResponse>

    @Multipart
    @POST("/api/user/reporting")
    fun postReport(@Field("id_user") id:Int,
                   @Field("ruang") ruang:String,
                   @Field("isi") isi:String,
                   @Field("gambar") gambar:String
    ):Call<PostResponse>

    @GET("/api/user/getReport/{id}")
    fun getListReport(@Path("id") id:Int): Call<ReportResponse>

    @GET("/api/user/getReportOne/{id}")
    fun getDetailReport(@Path("id") id:Int): Call<Report>

    @GET("/api/user/getUser/{id}")
    fun getUserProfile(@Path("id") id:Int):Call<User>

    companion object {
        fun create(): RetrofitService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://31ddb9fb.ngrok.io/")
                .client(client)
                .build()
            return retrofit.create(RetrofitService::class.java)

        }
    }
}
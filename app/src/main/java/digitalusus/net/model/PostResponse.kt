package digitalusus.net.model


import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("data")
    val data: Post,
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("success")
    val success: Boolean
)
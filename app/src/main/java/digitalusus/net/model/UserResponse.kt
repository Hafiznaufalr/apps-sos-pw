package digitalusus.net.model


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: List<User>
)
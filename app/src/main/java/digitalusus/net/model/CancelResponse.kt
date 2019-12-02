package digitalusus.net.model


import com.google.gson.annotations.SerializedName

data class CancelResponse(
    @SerializedName("cancel")
    val data: Cancel,
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("success")
    val success: Boolean
)
package digitalusus.net.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
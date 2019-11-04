package digitalusus.net.model


import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("message")
    val message: String
)
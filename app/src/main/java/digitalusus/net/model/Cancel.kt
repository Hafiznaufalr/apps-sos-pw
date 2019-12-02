package digitalusus.net.model


import com.google.gson.annotations.SerializedName

data class Cancel(
    @SerializedName("message")
    val message: String
)
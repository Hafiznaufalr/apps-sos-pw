package digitalusus.net.model


import com.google.gson.annotations.SerializedName

data class ReportResponse(
    @SerializedName("data")
    val `data`: List<Report>,
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("success")
    val success: Boolean
)
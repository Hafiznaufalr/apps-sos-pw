package digitalusus.net.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("nis")
    val nis: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("updated_at")
    val updatedAt: String
)
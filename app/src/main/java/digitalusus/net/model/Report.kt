package digitalusus.net.model

import com.google.gson.annotations.SerializedName

class Report (
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("gambar")
    val gambar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_user")
    val idUser: Int,
    @SerializedName("isi")
    val isi: String,
    @SerializedName("ruang")
    val ruang: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("updated_at")
    val updatedAt: String
    )
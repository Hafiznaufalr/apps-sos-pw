package digitalusus.net.ui.fragment.pengaduan

import android.content.Context
import digitalusus.net.model.PostResponse
import digitalusus.net.model.UserResponse
import digitalusus.net.network.RetrofitService
import digitalusus.net.ui.activity.auth.LoginView
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PengaduanFragmentPresenter(val pengaduanFragmentView: PengaduanFragmentView) {
    fun doPostReport(id:Int,
                     ruang:String,
                     isi:String,
                     gambar:String)
    {
        RetrofitService.create()
            .postReport(id, ruang, isi, gambar)
            .enqueue(object : Callback<PostResponse> {
                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    pengaduanFragmentView.onDataErrorFromApi(t)
                }

                override fun onResponse(
                    call: Call<PostResponse>,
                    response: Response<PostResponse>
                ) {
                    pengaduanFragmentView.onDataCompleteFromApi(response.body() as PostResponse)
                }

            })
    }
}
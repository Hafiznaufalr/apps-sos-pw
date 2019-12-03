package digitalusus.net.ui.fragment.pengaduan

import digitalusus.net.model.PostResponse
import digitalusus.net.network.RetrofitService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PengaduanFragmentPresenter(val pengaduanFragmentView: PengaduanFragmentView) {
    fun doPostReport(id:RequestBody,
                     ruang:RequestBody,
                     isi:RequestBody,
                     gambar:MultipartBody.Part)
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
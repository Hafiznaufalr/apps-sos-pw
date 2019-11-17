package digitalusus.net.ui.fragment.riwayat

import digitalusus.net.model.PostResponse
import digitalusus.net.model.ReportResponse
import digitalusus.net.network.RetrofitService
import digitalusus.net.ui.fragment.profile.ProfileFragmentView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatFragmentPresenter(private val riwayatFragmentView: RiwayatFragmentView) {

    fun getListReport(id:Int){
        RetrofitService.create()
            .getListReport(id)
            .enqueue(object : Callback<ReportResponse> {
                override fun onFailure(call: Call<ReportResponse>, t: Throwable) {
                    riwayatFragmentView.onDataErrorFromApi(t)
                }

                override fun onResponse(
                    call: Call<ReportResponse>,
                    response: Response<ReportResponse>
                ) {
                    riwayatFragmentView.onDataCompleteFromApi(response.body() as ReportResponse)
                }

            })
    }
}
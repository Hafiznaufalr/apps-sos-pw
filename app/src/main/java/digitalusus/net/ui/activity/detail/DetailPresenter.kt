package digitalusus.net.ui.activity.detail

import android.content.Context
import digitalusus.net.model.CancelResponse
import digitalusus.net.model.Report
import digitalusus.net.model.ReportResponse
import digitalusus.net.model.UserResponse
import digitalusus.net.network.RetrofitService
import digitalusus.net.ui.activity.auth.LoginView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(context: Context) {

    val detailView = context as DetailView

    fun getDetail(id: Int) {
        RetrofitService.create()
            .getDetailReport(id)
            .enqueue(object : Callback<ReportResponse> {
                override fun onFailure(call: Call<ReportResponse>, t: Throwable) {
                    detailView.onDataErrorFromApi(t)
                }

                override fun onResponse(
                    call: Call<ReportResponse>,
                    response: Response<ReportResponse>
                ) {
                    detailView.onDataCompleteFromApi(response.body() as ReportResponse)
                }

            })
    }

    fun cancelReport(id: Int){
        RetrofitService.create()
            .cancelReport(id)
            .enqueue(object : Callback<CancelResponse>{
                override fun onFailure(call: Call<CancelResponse>, t: Throwable) {
                    detailView.onDataErrorFromApi(t)
                }

                override fun onResponse(
                    call: Call<CancelResponse>,
                    response: Response<CancelResponse>
                ) {
                    detailView.onDataStatusChanged(response.body() as CancelResponse)
                }

            })
    }

}
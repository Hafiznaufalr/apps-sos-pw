package digitalusus.net.ui.activity.auth

import android.content.Context
import digitalusus.net.model.UserResponse
import digitalusus.net.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(context: Context) {
    val loginView = context as LoginView

    fun doLogin(email: String, password: String) {
        RetrofitService.create()
            .postLogin(email, password)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    loginView.onDataErrorFromApi(t)
                }

                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    loginView.onDataCompleteFromApi(response.body() as UserResponse)
                }

            })
    }
}
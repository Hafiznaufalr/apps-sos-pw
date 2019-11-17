package digitalusus.net.ui.fragment.profile

import digitalusus.net.model.PostResponse
import digitalusus.net.model.User
import digitalusus.net.model.UserResponse
import digitalusus.net.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragmentPresenter(private val profileFragmentView: ProfileFragmentView) {
    fun getProfil(id:Int){
        RetrofitService.create()
            .getUserProfile(id)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    profileFragmentView.onDataErrorFromApi(t)
                }

                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    profileFragmentView.loadUser(response.body() as UserResponse)
                }

            })
    }
}
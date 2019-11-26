package digitalusus.net.ui.activity.auth

import digitalusus.net.model.UserResponse

interface LoginView{
    fun onDataCompleteFromApi(data: UserResponse)
    fun onDataErrorFromApi(throwable: Throwable)

}
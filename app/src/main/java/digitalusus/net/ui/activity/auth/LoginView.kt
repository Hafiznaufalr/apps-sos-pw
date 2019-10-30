package digitalusus.net.ui.activity.auth

import digitalusus.net.model.User

interface LoginView{
    fun onDataCompleteFromApi(data: User)
    fun onDataErrorFromApi(throwable: Throwable)

}
package digitalusus.net.ui.fragment.profile


import digitalusus.net.model.User
import digitalusus.net.model.UserResponse

interface ProfileFragmentView {
    fun loadUser(data: UserResponse)
    fun onDataErrorFromApi(throwable: Throwable)
}

package digitalusus.net.ui.fragment.profile


import digitalusus.net.model.UserResponse

interface ProfileFragmentView {
    fun onDataCompleteFromApi(data: UserResponse)
    fun onDataErrorFromApi(throwable: Throwable)
}

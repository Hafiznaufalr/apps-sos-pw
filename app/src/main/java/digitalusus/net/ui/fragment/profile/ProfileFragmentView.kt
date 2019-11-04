package digitalusus.net.ui.fragment.profile

interface ProfileFragmentView {
    fun loadUser(data: List<Data>)
    fun onDataErrorFromApi(throwable: Throwable)
}


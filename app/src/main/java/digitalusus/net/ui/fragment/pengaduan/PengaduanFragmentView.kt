package digitalusus.net.ui.fragment.pengaduan

import digitalusus.net.model.PostResponse

interface PengaduanFragmentView {
    fun onDataCompleteFromApi(data:PostResponse)
    fun onDataErrorFromApi(throwable: Throwable)
}
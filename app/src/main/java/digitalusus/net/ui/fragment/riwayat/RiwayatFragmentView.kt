package digitalusus.net.ui.fragment.riwayat

import digitalusus.net.model.ReportResponse
import digitalusus.net.model.User

interface RiwayatFragmentView {
    fun onDataCompleteFromApi(data: ReportResponse)
    fun onDataErrorFromApi(throwable: Throwable)
}

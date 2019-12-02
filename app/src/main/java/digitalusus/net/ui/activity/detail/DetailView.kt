package digitalusus.net.ui.activity.detail

import digitalusus.net.model.CancelResponse
import digitalusus.net.model.Report
import digitalusus.net.model.ReportResponse

interface DetailView {

    fun onDataCompleteFromApi(data: ReportResponse)
    fun onDataStatusChanged(data: CancelResponse)
    fun onDataErrorFromApi(throwable: Throwable)
}
package digitalusus.net.ui.activity.detail

import digitalusus.net.model.Report
import digitalusus.net.model.ReportResponse

interface DetailView {

    fun onDataCompleteFromApi(data: ReportResponse)
    fun onDataErrorFromApi(throwable: Throwable)
}
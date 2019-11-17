package digitalusus.net.ui.fragment.riwayat

import android.graphics.Typeface
import android.icu.util.ValueIterator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import digitalusus.net.R
import digitalusus.net.model.Report
import digitalusus.net.model.ReportResponse
import digitalusus.net.util.Preferences
import kotlinx.android.synthetic.main.fragment_riwayat.*

class RiwayatFragment : Fragment(), RiwayatFragmentView {

    lateinit var presenter: RiwayatFragmentPresenter
    lateinit var rv_riwayat: RecyclerView
    lateinit var adapter: RiwayatFragmentAdapter
    private var listRiwayat: MutableList<Report> = mutableListOf()
    private lateinit var layoutManager: LinearLayoutManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_riwayat = view!!.findViewById(R.id.rv_riwayat)
        presenter = RiwayatFragmentPresenter(this)

        adapter = RiwayatFragmentAdapter(context!!,listRiwayat)
        layoutManager = LinearLayoutManager(context)
        rv_riwayat.layoutManager = layoutManager
        rv_riwayat.adapter = adapter
        loadRiwayat()
        refresh()

    }

    private fun refresh() {
        swipe_layout.setOnRefreshListener {
            loadRiwayat()
        }
    }

    private fun loadRiwayat(){
        val idUser = Preferences.getId(context!!)
        presenter.getListReport(idUser)
    }

    override fun onDataCompleteFromApi(data: ReportResponse) {
        swipe_layout.isRefreshing = false
        pb_riwayat.visibility = View.VISIBLE
        listRiwayat.clear()
        listRiwayat.addAll(data.data)
        adapter.notifyDataSetChanged()
        if (listRiwayat.size == 0){
            listRiwayat.clear()
            tv_nodata.visibility = View.VISIBLE
        }
        pb_riwayat.visibility = View.GONE

    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        Toast.makeText(context, "Gagal Memuat Data", Toast.LENGTH_SHORT).show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_riwayat, container, false)

    }




}
package digitalusus.net.ui.fragment.riwayat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
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
    lateinit var swiper: SwipeRefreshLayout
    lateinit var pb_riwayat:ProgressBar
    lateinit var tvPlease: TextView
    private var listRiwayat: MutableList<Report> = mutableListOf()
    private lateinit var layoutManager: LinearLayoutManager



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_riwayat, container, false)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_riwayat = view!!.findViewById(R.id.rv_riwayat)
        presenter = RiwayatFragmentPresenter(this)
        swiper = view!!.findViewById(R.id.swipe_layout)
        pb_riwayat = view!!.findViewById(R.id.pb_riwayat)
        tvPlease = view!!.findViewById(R.id.tv_pleasewait)

        adapter = RiwayatFragmentAdapter(context!!,listRiwayat)
        layoutManager = LinearLayoutManager(context)
        rv_riwayat.layoutManager = layoutManager
        rv_riwayat.adapter = adapter
        loadRiwayat()
        refresh()

    }

    private fun refresh() {
        swiper.setOnRefreshListener {
            loadRiwayat()
        }
    }

    private fun loadRiwayat(){
        val idUser = Preferences.getId(context!!)
        presenter.getListReport(idUser)
    }

    override fun onDataCompleteFromApi(data: ReportResponse) {
        if (isAdded) {
            swiper.isRefreshing = false
            listRiwayat.clear()
            listRiwayat.addAll(data.data)
            listRiwayat.reverse()
            adapter.notifyDataSetChanged()
            if (listRiwayat.size == 0) {
                listRiwayat.clear()
                tv_nodata.visibility = View.VISIBLE
            }
            pb_riwayat.visibility = View.GONE
            tvPlease.visibility = View.GONE

        }

    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        Toast.makeText(context, "Gagal Memuat Data", Toast.LENGTH_SHORT).show()
    }



}
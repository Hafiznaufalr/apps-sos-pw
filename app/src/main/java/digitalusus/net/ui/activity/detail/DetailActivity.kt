package digitalusus.net.ui.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import digitalusus.net.R
import digitalusus.net.model.CancelResponse
import digitalusus.net.model.Report
import digitalusus.net.model.ReportResponse
import dmax.dialog.SpotsDialog

class DetailActivity : AppCompatActivity(), DetailView {


    lateinit var tv_detail_date: TextView
    lateinit var tv_detail_isi: TextView
    lateinit var tv_detail_lokasi: TextView
    lateinit var tv_detail_status: TextView
    lateinit var iv_report: ImageView
    lateinit var presenter: DetailPresenter
    lateinit var dialog: SpotsDialog
    lateinit var arrowBack: TextView
    lateinit var btn_batalkan: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        tv_detail_date = findViewById(R.id.tv_detail_date)
        tv_detail_isi = findViewById(R.id.tv_detail_isi)
        tv_detail_lokasi = findViewById(R.id.tv_detail_lokasi)
        tv_detail_status = findViewById(R.id.tv_detail_status)
        iv_report = findViewById(R.id.iv_laporan)
        arrowBack = findViewById(R.id.ic_arrow)
        presenter = DetailPresenter(this)
        dialog = SpotsDialog.Builder().setContext(this).build() as SpotsDialog
        btn_batalkan = findViewById(R.id.btn_batalkan)
        arrowBack.setOnClickListener {
            onBackPressed()
        }
        getDetailReport()
        cancelReport()
    }

    private fun cancelReport() {
        btn_batalkan.setOnClickListener {
            dialog.show()
            val idReport = intent.getIntExtra("idReport", 0)
            presenter.cancelReport(idReport)
        }

    }


    private fun getDetailReport() {
        dialog.show()
        val idReport = intent.getIntExtra("idReport", 0)
        presenter.getDetail(idReport)
    }



    override fun onDataStatusChanged(data: CancelResponse) {
        dialog.dismiss()
        if(data.success){
            Toast.makeText(this, "Report Dibatalkan", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
    }

    override fun onDataCompleteFromApi(data: ReportResponse) {
        if(data.success) {
            dialog.dismiss()
            val getData = data.data[0]
            tv_detail_date.text = getData.createdAt
            tv_detail_lokasi.text = getData.ruang
            tv_detail_isi.text = getData.isi
            tv_detail_status.text = getData.status

            Glide.with(this).load(getData.gambar).into(iv_report)
            when {
                tv_detail_status.text == "menunggu" -> {
                    tv_detail_status.setBackgroundResource(R.drawable.bg_menunggu)
                    btn_batalkan.visibility = View.VISIBLE
                }
                tv_detail_status.text == "proses" -> tv_detail_status.setBackgroundResource(R.drawable.bg_proses)
                tv_detail_status.text == "selesai" -> tv_detail_status.setBackgroundResource(R.drawable.bg_selesai)

                else -> tv_detail_status.setBackgroundResource(R.drawable.bg_ditolak)
            }
        }
    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

}

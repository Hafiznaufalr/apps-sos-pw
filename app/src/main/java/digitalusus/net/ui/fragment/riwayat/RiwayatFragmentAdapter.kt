package digitalusus.net.ui.fragment.riwayat

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import digitalusus.net.R
import digitalusus.net.model.Report
import digitalusus.net.ui.activity.detail.DetailActivity
import kotlinx.android.synthetic.main.item_riwayat.view.*

class RiwayatFragmentAdapter(val context: Context,
                             val result: List<Report>):
    RecyclerView.Adapter<RiwayatFragmentAdapter.RiwayatHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatHolder
        =RiwayatHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat,parent,false))


    override fun getItemCount(): Int {
        return result.size
    }

    override fun onBindViewHolder(holder: RiwayatHolder, position: Int) {
        val data = result[position]
        val status = holder.itemView.tv_item_status
        holder.itemView.tv_item_lokasi.text = "Lokasi : " + data.ruang
        holder.itemView.tv_item_date.text = data.createdAt
        holder.itemView.tv_item_isi.text = data.isi
        holder.itemView.tv_item_status.text = data.status
        when {
            status.text == "menunggu" -> status.setBackgroundResource(R.drawable.bg_menunggu)
            status.text == "proses" -> status.setBackgroundResource(R.drawable.bg_proses)
            status.text == "selesai" -> status.setBackgroundResource(R.drawable.bg_selesai)
            else -> status.setBackgroundResource(R.drawable.bg_ditolak)
        }


        val idReport = data.id
        holder.itemView.setOnClickListener {
            val i = Intent(context, DetailActivity::class.java)
            i.putExtra("idReport", idReport)
            context.startActivity(i)
        }
    }

    inner class RiwayatHolder(itemView:View): RecyclerView.ViewHolder(itemView)

}
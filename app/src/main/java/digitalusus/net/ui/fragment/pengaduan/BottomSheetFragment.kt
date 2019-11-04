package digitalusus.net.ui.fragment.pengaduan

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import digitalusus.net.R
import kotlinx.android.synthetic.main.bottom_sheet.*
import digitalusus.net.ui.activity.auth.LoginActivity
import android.content.Intent
import android.widget.Toast
import digitalusus.net.util.Preferences


class BottomSheetFragment : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.bottom_sheet, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupFont()
        btn_lapor.setOnClickListener {
            Toast.makeText(context, "Berhasil Logout", Toast.LENGTH_SHORT).show()
            Preferences.clearUser(context!!)
            startActivity(Intent(context, LoginActivity::class.java))
            activity!!.finish()
        }
    }

    private fun setupFont() {
        val NBold = Typeface.createFromAsset(activity!!.assets, "fonts/Nunito-Bold.ttf")
        val NRegular = Typeface.createFromAsset(activity!!.assets, "fonts/Nunito-Regular.ttf")
        tv_lokasi.typeface = NRegular
        et_lokasi.typeface = NBold
        tv_laporankamu.typeface = NRegular
        btn_lapor.typeface = NBold
        btn_lapor.transformationMethod = null

    }
}
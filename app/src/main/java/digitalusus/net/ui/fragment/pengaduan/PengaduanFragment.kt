package digitalusus.net.ui.fragment.pengaduan

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import digitalusus.net.R
import digitalusus.net.util.Preferences
import kotlinx.android.synthetic.main.fragment_pengaduan.*

class PengaduanFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setTvName()
        setupFont()
        showBottomSheet()
    }

    private fun setTvName() {
        val name = Preferences.getName(context!!)
        tv_name.text = name
    }

    @SuppressLint("InflateParams")
    private fun showBottomSheet() {
        btn_ayo_lapor.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
        }
    }

    private fun setupFont() {
        val NBold = Typeface.createFromAsset(activity!!.assets, "fonts/Nunito-Bold.ttf")
        val NRegular = Typeface.createFromAsset(activity!!.assets, "fonts/Nunito-Regular.ttf")

        tv_halo.typeface = NBold
        tv_name.typeface = NBold
        tv_desc.typeface = NRegular
        btn_ayo_lapor.typeface = NBold
        btn_ayo_lapor.transformationMethod = null

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pengaduan, container, false)
        return view
    }


    companion object {
        fun newInstance(): PengaduanFragment {
            val fragment = PengaduanFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
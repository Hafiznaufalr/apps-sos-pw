package digitalusus.net.ui.fragment.pengaduan

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import digitalusus.net.R
import digitalusus.net.util.Preferences
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_pengaduan.*
import kotlinx.android.synthetic.main.fragment_pengaduan.tv_desc



class PengaduanFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setTvName()
        showBottomSheet()
        btn_ayo_lapor.transformationMethod = null
    }

    private fun setTvName() {
        val name = Preferences.getName(context!!)
        tv_name.text = name
    }

    @SuppressLint("InflateParams")
    private fun showBottomSheet() {
        btn_ayo_lapor.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(fragmentManager!!, bottomSheetFragment.tag)

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_pengaduan, container, false)

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
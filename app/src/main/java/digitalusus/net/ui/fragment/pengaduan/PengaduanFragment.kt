package digitalusus.net.ui.fragment.pengaduan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import digitalusus.net.R

class PengaduanFragment : Fragment() {

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
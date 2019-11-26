package digitalusus.net.ui.fragment.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import digitalusus.net.R
import digitalusus.net.model.UserResponse
import digitalusus.net.ui.activity.auth.LoginActivity
import digitalusus.net.util.Preferences
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), ProfileFragmentView {

    lateinit var presenter: ProfileFragmentPresenter
    lateinit var tvEmail: TextView
    lateinit var tvName: TextView
    lateinit var profileImg: ImageView
    lateinit var pb_profile: ProgressBar
    lateinit var swiper: SwipeRefreshLayout
    lateinit var mainUi: RelativeLayout
    lateinit var tvPlease: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = ProfileFragmentPresenter(this)
        tvEmail = view!!.findViewById(R.id.tv_profile_email)
        tvName = view!!.findViewById(R.id.tv_profile_name)
        profileImg = view!!.findViewById(R.id.profile_image)
        swiper = view!!.findViewById(R.id.swipe_layout)
        pb_profile = view!!.findViewById(R.id.pb_profile)
        tvPlease = view!!.findViewById(R.id.tv_pleasewait)
        mainUi = view!!.findViewById(R.id.rl_content)
        mainUi.visibility = View.GONE



        loadProfile()
        logout()
        btn_logout.transformationMethod = null
    }

    private fun logout() {
        btn_logout.setOnClickListener {
            Toast.makeText(context, "Berhasil Logout", Toast.LENGTH_SHORT).show()
            Preferences.clearUser(context!!)
            startActivity(Intent(context, LoginActivity::class.java))
            activity!!.finish()
        }
    }


    private fun loadProfile() {
        val idUser = Preferences.getId(context!!)
        presenter.getProfil(idUser)
    }
    override fun onDataCompleteFromApi(data: UserResponse) {
        if(isAdded) {
            tvName.text = data.data[0].name
            tvEmail.text = data.data[0].email
            Glide.with(this).load(data.data[0].avatar).into(profileImg)
            pb_profile.visibility = View.GONE
            tvPlease.visibility = View.GONE
            mainUi.visibility = View.VISIBLE
        }

    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        Toast.makeText(context, "Gagal Memuat Data", Toast.LENGTH_SHORT).show()
    }




}
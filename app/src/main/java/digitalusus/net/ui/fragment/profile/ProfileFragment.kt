package digitalusus.net.ui.fragment.profile

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import digitalusus.net.R
import digitalusus.net.model.User
import digitalusus.net.model.UserResponse
import digitalusus.net.ui.activity.auth.LoginActivity
import digitalusus.net.util.Preferences
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), ProfileFragmentView {

    lateinit var presenter: ProfileFragmentPresenter

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
    override fun loadUser(data: UserResponse) {
        tv_profile_name.text = data.data[0].name
        tv_profile_email.text = data.data[0].email
        Glide.with(this).load(data.data[0].avatar).into(profile_image)

    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        Toast.makeText(context, "Gagal Memuat Data", Toast.LENGTH_SHORT).show()
    }




}
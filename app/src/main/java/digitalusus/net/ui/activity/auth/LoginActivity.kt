package digitalusus.net.ui.activity.auth

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import digitalusus.net.R
import digitalusus.net.model.User
import digitalusus.net.model.UserResponse
import digitalusus.net.ui.activity.main.MainActivity
import digitalusus.net.util.Preferences
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_login.*



class LoginActivity : AppCompatActivity(), LoginView {

    lateinit var presenter: LoginPresenter
    lateinit var dialog: SpotsDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        dialog = SpotsDialog.Builder().setContext(this).build() as SpotsDialog
        validateForm()
        btn_login.transformationMethod = null
    }
    override fun onStart() {
        super.onStart()
        if (Preferences.getStatus(this)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }



    private fun validateForm() {
        btn_login.setOnClickListener {
            when {
                et_email.text.toString().trim() == "" -> {
                    et_email.error = "err"
                    Toast.makeText(this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                }
                et_password.text.toString().trim() == "" -> {
                    et_password.error = "err"
                    Toast.makeText(this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    login()
                }

            }
        }
    }

    private fun login() {
        dialog.show()
        val email = et_email.text.toString()
        val password = et_password.text.toString()
        presenter.doLogin(email, password)


    }

    private fun doPref(data: UserResponse){
        Preferences.setId(this, data.data[0].id)
        Preferences.setName(this, data.data[0].name)
        Preferences.setStatus(this,true)
    }


    override fun onDataCompleteFromApi(data: UserResponse) {
        if (data.success){
            dialog.dismiss()
            doPref(data)
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()

        }else{
            dialog.dismiss()
            Toast.makeText(this, "Email / Password Salah", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onDataErrorFromApi(throwable: Throwable) {
        Toast.makeText(this, throwable.toString(), Toast.LENGTH_SHORT).show()
    }

}

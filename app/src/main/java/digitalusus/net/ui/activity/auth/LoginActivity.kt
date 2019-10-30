package digitalusus.net.ui.activity.auth

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.ViewAnimator
import digitalusus.net.R
import digitalusus.net.model.User
import digitalusus.net.ui.activity.main.MainActivity
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
        setUpFont()
    }

    private fun setUpFont() {
        val NBold = Typeface.createFromAsset(assets, "fonts/Nunito-Bold.ttf")
        val NRegular = Typeface.createFromAsset(assets, "fonts/Nunito-Regular.ttf")
        tv_welcome.typeface = NBold
        tv_desc.typeface = NRegular
        et_email.typeface = NRegular
        et_password.typeface = NRegular
        btn_login.typeface = NBold
        btn_login.transformationMethod = null
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

    override fun onDataCompleteFromApi(data: User) {
        if (data.success){
            dialog.dismiss()
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

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
import androidx.fragment.app.DialogFragment
import digitalusus.net.model.PostResponse
import digitalusus.net.util.Preferences
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.graphics.Bitmap
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import pub.devrel.easypermissions.EasyPermissions
import android.R.attr.data
import androidx.core.app.NotificationCompat.getExtras
import android.R.attr.data
import android.net.Uri
import dmax.dialog.SpotsDialog
import okhttp3.MultipartBody


class BottomSheetFragment : BottomSheetDialogFragment(), PengaduanFragmentView {
    lateinit var presenter: PengaduanFragmentPresenter
    lateinit var picked: Bitmap
    lateinit var dialog: SpotsDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.bottom_sheet, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog = SpotsDialog.Builder().setContext(context).build() as SpotsDialog
        presenter = PengaduanFragmentPresenter(this)
        pickImage()
        postReport()
        btn_lapor.transformationMethod = null

    }


    private fun pickImage() {
        iv_laporan.setOnClickListener {
            if(EasyPermissions.hasPermissions(context!!,android.Manifest.permission.CAMERA)) {
                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePicture, 1)
            }
            else{
                // tampilkan permission request saat belum mendapat permission dari user
                EasyPermissions.requestPermissions(this,"This application need your permission to access photo gallery.",991,android.Manifest.permission.CAMERA)
            }
        }
    }


    override fun onDataCompleteFromApi(data: PostResponse) {
    }

    override fun onDataErrorFromApi(throwable: Throwable) {
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
           if (resultCode == RESULT_OK) {
               picked = data!!.extras!!.get("data") as Bitmap
               iv_laporan.setImageBitmap(picked)


            }
        }


    private fun postReport() {
        val user_id = Preferences.getId(context!!)
        val lokasi = et_lokasi.text.toString()
        val isi = et_isi.text.toString()
        btn_lapor.setOnClickListener {
            dialog.show()

            presenter.doPostReport(user_id,lokasi, isi,picked.toString() )
        }
    }

    }

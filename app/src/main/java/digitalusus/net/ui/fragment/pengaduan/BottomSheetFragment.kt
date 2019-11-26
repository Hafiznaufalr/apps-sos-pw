package digitalusus.net.ui.fragment.pengaduan

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import digitalusus.net.R
import digitalusus.net.model.PostResponse
import digitalusus.net.util.Preferences
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.bottom_sheet.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import pub.devrel.easypermissions.EasyPermissions
import android.annotation.SuppressLint
import java.io.*

@Suppress("DEPRECATION")
class BottomSheetFragment : BottomSheetDialogFragment(), PengaduanFragmentView {
    private val IMAGE_CAPTURE_CODE: Int = 1001
    lateinit var imageuri: Uri
    lateinit var presenter: PengaduanFragmentPresenter
    lateinit var picked: Bitmap
    lateinit var dialog: SpotsDialog
    lateinit var id: RequestBody
    lateinit var ruang: RequestBody
    lateinit var isi: RequestBody
    lateinit var gambar: MultipartBody.Part


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
                startActivityForResult(takePicture, IMAGE_CAPTURE_CODE)
            }
            else{
                // tampilkan permission request saat belum mendapat permission dari user
                EasyPermissions.requestPermissions(this,"This application need your permission to access photo gallery.",991,android.Manifest.permission.CAMERA)
            }
        }
    }




    override fun onDataCompleteFromApi(data: PostResponse) {
        if(data.success){
            dialog.dismiss()
            Toast.makeText(context, "Berhasil", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
           if (resultCode == RESULT_OK) {
               val bitmap = data!!.extras!!.get("data") as Bitmap


               iv_laporan.setImageBitmap(bitmap )
               val tempUri = getImageUri(context!!, bitmap)
               val realUri = getRealPathFromUri(tempUri)
               iv_laporan.setBackgroundColor(Color.WHITE)
               val id_user = Preferences.getId(context!!)


               id = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), id_user.toString())
               val requestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), File(realUri))
                gambar  = MultipartBody.Part.createFormData("gambar",realUri, requestBody)

           }
        }

    private fun getImageUri(context: Context, bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path)
    }

    @SuppressLint("Recycle")
    private fun getRealPathFromUri(uri: Uri): String {
        var path = ""
        if (context!!.contentResolver != null) {
            val cursor = context!!.contentResolver.query(uri, null, null, null, null)
            if (cursor != null) {
                cursor.moveToFirst()
                val idx = cursor.getColumnIndex(Images.ImageColumns.DATA)
                path = cursor.getString(idx)
                cursor.close()
            }
        }
        return path
    }


    private fun postReport() {
        btn_lapor.setOnClickListener {
            dialog.show()
            ruang = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), et_lokasi.text.toString())
            isi = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), et_isi.text.toString())
            presenter.doPostReport(id, ruang , isi, gambar)

        }

    }

    }

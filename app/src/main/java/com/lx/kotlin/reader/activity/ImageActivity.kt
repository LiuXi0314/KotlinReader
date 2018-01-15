package com.lx.kotlin.reader.activity

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import com.bumptech.glide.Glide
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.utils.Logger
import com.lx.kotlin.reader.utils.Toaster
import kotlinx.android.synthetic.main.activity_image_detail.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream


/**
 * 图片查看下载页
 */
class ImageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)
        var url = intent.getStringExtra("url")
        Logger.log(url)
        Glide.with(this).load(url).crossFade().into(imageContent)
        close.setOnClickListener { finish() }
        download.setOnClickListener() {
            downLoad()
        }
    }


    private fun downLoad(): Boolean {
        var url = intent.getStringExtra("url")
        if (url.isEmpty()) return false
        var dialog = ProgressDialog(this)
        dialog.setMessage("下载中")
        dialog.show()
        var observable = Observable.create<Any> { Toaster.show(this, "下载成功") }

        Thread {
            var fileName = System.currentTimeMillis().toString() + ".png"
            var imageFile = File(Environment.getExternalStorageDirectory(), fileName)
            imageFile.createNewFile()
            Logger.log(imageFile.absolutePath)
            var bmp: Bitmap = Glide.with(this).load(url).asBitmap().into(1080, 1920).get()
            var outPutStream = FileOutputStream(imageFile)
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outPutStream)
            outPutStream.flush()
            outPutStream.close()
            try {
                MediaStore.Images.Media.insertImage(getContentResolver(),
                        imageFile.absolutePath, fileName, null)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, FileProvider.getUriForFile(this, packageName, imageFile)))
            dialog.dismiss()
            observable.subscribeOn(AndroidSchedulers.mainThread()).subscribe()

        }.start()

        return false
    }
}

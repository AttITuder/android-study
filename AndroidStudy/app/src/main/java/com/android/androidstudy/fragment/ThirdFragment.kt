package com.android.androidstudy.fragment

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.android.androidstudy.R
import com.android.customview.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_third.*
import java.io.*
import java.util.*

/**
 * <p>描述:  </p>
 * <p>详细描述:  </p>
 * <p>创建时间: 2020/4/22 2:38 PM</p>
 *
 * @author 1578325085@qq.com
 * @version v1.0.0
 */
class ThirdFragment:BaseFragment() {
//    val fileName:String = "webofenqi"

    //读取sd卡uuid
    var fileName =
        UUID.nameUUIDFromBytes("微博分期2".toByteArray()).toString().replace("-", "")

    override fun getLayoutId(): Int = R.layout.fragment_third

    override fun initView(view: View) {

    }

    override fun initListener(view: View) {
        btnWriteFile.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                writeFileAndroidQ(context,fileName,"123456")
            }
        }

        btnReadFile.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                readFileAndroidQ(context,getAndroidQFileUri(context,fileName))
            }
        }
    }


    override fun initData() {
        tvScreenWidth.text = "屏幕宽度："+resources.displayMetrics.widthPixels;
        tvScreenHeight.text = "屏幕高度："+resources.displayMetrics.heightPixels;
    }

    /**
     * 写入文本数据
     *
     *
     * 程序上下文
     * 文件名
     *
     * @return String, 读取到的文本内容，失败返回null
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    fun writeFileAndroidQ(
        context: Context?,
        fileName: String?,
        data: String
    ) {
        context?.run {
            val values = ContentValues()
            values.put(MediaStore.Downloads.DISPLAY_NAME, fileName)
            values.put(MediaStore.Downloads.MIME_TYPE, "*/*")
            values.put(MediaStore.Downloads.TITLE, fileName)
//            values.put(MediaStore.Downloads.RELATIVE_PATH, "Download" + File.separator + ".log")

//        Uri external = MediaStore.Downloads.getContentUri("external");
            val external = MediaStore.Downloads.EXTERNAL_CONTENT_URI
            val resolver = context.contentResolver
            val fileUrl = resolver.insert(external, values)
            try {
                val outputStream = resolver.openOutputStream(fileUrl!!)
                outputStream!!.write(data.toByteArray(charset("utf-8")))
                outputStream.close()
                textWriteContent.text = "文件路径$fileUrl"
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }


    /**
     * 读取文本数据
     *
     *
     * 程序上下文
     * 文件名
     *
     * @return String, 读取到的文本内容，失败返回null
     */
    fun readFileAndroidQ(
        context: Context?,
        uri: Uri?
    ): String? {
        var content: String? = null
        Log.d("readFileAndroidQ", "uri:$uri")

        context?.run {
            var fis: FileInputStream? = null
            if (uri == null) {
                return null
            }
            try {
                val pfd =
                    context.contentResolver.openFileDescriptor(uri, "r")
                if (pfd != null) {
                    fis = FileInputStream(pfd.fileDescriptor)
                }
                if (fis != null) {
                    val buffer = ByteArray(1024)
                    val arrayOutputStream =
                        ByteArrayOutputStream()
                    while (true) {
                        val readLength = fis.read(buffer)
                        if (readLength == -1) break
                        arrayOutputStream.write(buffer, 0, readLength)
                    }
                    fis.close()
                    arrayOutputStream.close()
                    content = String(arrayOutputStream.toByteArray())
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Log.d("readFileAndroidQ", "cucontentnl:" + content + " e:" + e.message)
            } catch (e: IOException) {
                e.printStackTrace()
                content = null
                Log.d("readFileAndroidQ", "cucontentnl:" + content + " e:" + e.message)
            } finally {
                try {
                    fis?.close()
                } catch (ioe: IOException) {
                    ioe.printStackTrace()
                }
            }
            Log.d("readFileAndroidQ", "cucontentnl:$content")
        }
        textReadContent.text = "读取文件路径$uri 文件内容：$content"
        return content
    }


    /**
     * MediaStore提供的Uri指定设备，selection参数指定过滤条件，通过ContentResolver.query接口查询文件 Uri
     * @param context
     * @param fileName
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    fun getAndroidQFileUri(
        context: Context?,
        fileName: String
    ): Uri? {
        context?.run {
            return try {
                val external = MediaStore.Downloads.EXTERNAL_CONTENT_URI
                var uri: Uri? = null
                val resolver = context.contentResolver
//                val selection = MediaStore.Downloads.TITLE + " LIKE  '$fileName%'"

                val delSelection = MediaStore.Downloads.TITLE + " LIKE  '$fileName%'" +" AND " +MediaStore.Downloads.TITLE +" not in ('$fileName')"
                var count = resolver.delete(external, delSelection, null)
                Log.d("getAndroidQFileUri", "del count:$count")

//                val cursor = resolver.query(external, null, selection, null, null)
//                if (cursor != null){
//                    while (cursor.moveToNext()){
//                            for (index in 0 until cursor.columnCount) {
//                                val id = cursor.getColumnIndex(MediaStore.Downloads._ID)
//                                val name = cursor.getColumnName(index)
//                                val cunl = cursor.getColumnIndex(name)
//                                val data = cursor.getString(cunl)
//                                Log.d(
//                                    "getAndroidQFileUri",
//                                    "cunl:$cunl name:$name data:$data"
//                                )
//                                uri = ContentUris.withAppendedId(external, cursor.getLong(id))
//                            }
//                        Log.d("getAndroidQFileUri", "uri:$uri")
//                    }
//                }

                val selection = MediaStore.Downloads.TITLE + "=?"
                val args = arrayOf(fileName)
                val cursor = resolver.query(external, null, selection, args, null)
                if (cursor != null && cursor.moveToFirst()) {
                    val id = cursor.getColumnIndex(MediaStore.Downloads._ID)
                    uri = ContentUris.withAppendedId(external, cursor.getLong(id))
                    for (index in 0 until cursor.columnCount) {
                        val name = cursor.getColumnName(index)
                        val cunl = cursor.getColumnIndex(name)
                        val data = cursor.getString(cunl)
                        Log.d(
                            "getAndroidQFileUri",
                            "cunl:$cunl name:$name data:$data"
                        )
                    }
                }
                Log.d("getAndroidQFileUri22", "uri:$uri")
                uri
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                null
            }
        }
        return null
    }

}
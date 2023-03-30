package stpious.student.ui.adapter

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import prajna.app.utills.URL_
import stpious.employee.R
import stpious.employee.utills.customFonts.Text_Normal
import stpious.student.repository.model.SyllabusModel
import java.util.*


class SyllabusAdapter(var context: Context, var dataList: ArrayList<SyllabusModel>) :
    RecyclerView.Adapter<SyllabusAdapter.ViewHolder>() {

    lateinit var manager: DownloadManager


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvSyllabusSub: Text_Normal = view.findViewById(R.id.tvSyllabusSub)
        var tvSyllabusYear_name: Text_Normal = view.findViewById(R.id.tvSyllabusYear_name)
        var tvSyllabus_sem: Text_Normal = view.findViewById(R.id.tvSyllabus_sem)
        var llSyllabusFile: LinearLayout = view.findViewById(R.id.llSyllabusFile)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.syllabus_item, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
//        Glide.with(context).load(data.ImageUrl)
//            .into(holder.ivGallery)
        holder.tvSyllabusSub.setText(data.SubjectName)
        holder.tvSyllabus_sem.setText(data.SemesterNo)
        holder.tvSyllabusYear_name.setText(data.AcademicYearRegulation+" "+ data.GroupName)

        holder.llSyllabusFile.setOnClickListener(View.OnClickListener {

            val filpath=URL_+data.FilePath
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(filpath))
            context.startActivity(browserIntent)
//            val url="https://stpiouscollegehyd.in/Uploads/Syllabus/27_10148_22%20mba%20missing%20data.xlsx"
//            val myURL = URL(url)
//            val gfgThread = Thread {
//                try {
//                    // Your network activity
//                    // code comes here
//                    downloadFile(myURL,"testing")
//
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//
//            val mimeTypes = arrayOf(
//                "image/*",
//                "application/pdf",
//                "application/msword",
//                "application/vnd.ms-powerpoint",
//                "application/vnd.ms-excel",
//                "text/plain"
//            )
//
//            val intent = Intent(Intent.ACTION_GET_CONTENT)
//            intent.addCategory(Intent.CATEGORY_OPENABLE)
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                intent.type = if (mimeTypes.size == 1) mimeTypes[0] else "*/*"
//                if (mimeTypes.size > 0) {
//                    intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
//                }
//            } else {
//                var mimeTypesStr = ""
//                for (mimeType in mimeTypes) {
//                    mimeTypesStr += "$mimeType|"
//                }
//                intent.type = mimeTypesStr.substring(0, mimeTypesStr.length - 1)
//            }
//            (context as Activity).startActivityForResult(Intent.createChooser(intent, "ChooseFile"), 0)
//
////
////            val intent = Intent(Intent.ACTION_VIEW)
////            intent.setDataAndType(filpath, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
////            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
////
////            try {
////                context.startActivity(intent)
////            } catch (e: ActivityNotFoundException) {
////                Toast.makeText(
////                    context, "No Application Available to View Excel",
////                    Toast.LENGTH_SHORT
////                ).show()
////            }
////            val reference: Long = manager.enqueue(request)
////            val intent = Intent("android.intent.action.VIEW")
////            intent.addCategory("android.intent.category.DEFAULT")
////            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
////            val uri = Uri.fromFile(File(filpath))
////            intent.setDataAndType(uri, "application/vnd.ms-excel")
//
////            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(filpath))
////            context.startActivity(browserIntent)


        })


    }
//    fun downloadFile(url: URL, fileName: String) {
//        url.openStream().use { Files.copy(it, Paths.get(fileName)) }
//    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
package stpious.student.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import prajna.app.utills.URL_
import stpious.employee.R
import stpious.employee.utills.customFonts.Text_Normal
import stpious.student.repository.model.GalleryModel
import stpious.student.repository.model.NoticeModel
import java.util.*

class NoticeAdapter(var context: Context, var dataList: ArrayList<NoticeModel>) :
    RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvCircularDate: Text_Normal = view.findViewById(R.id.tvCircularDate)
        var tvCircularsub: Text_Normal = view.findViewById(R.id.tvCircularsub)
        var llDownloadFile: LinearLayout = view.findViewById(R.id.llDownloadFile)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.notice_item, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
//        Glide.with(context).load(data.ImageUrl)
//            .into(holder.ivGallery)
        holder.tvCircularsub.setText(data.CircularSubject)
        holder.tvCircularDate.setText(data.CreatedDate)

        holder.llDownloadFile.setOnClickListener(View.OnClickListener {

            val filpath= URL_ +data.FilePath
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(filpath))
            context.startActivity(browserIntent)
        })


    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
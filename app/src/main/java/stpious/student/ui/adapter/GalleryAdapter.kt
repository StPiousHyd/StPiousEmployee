package stpious.student.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import stpious.employee.R
import stpious.employee.utills.customFonts.Text_Normal
import stpious.student.repository.model.GalleryModel
import java.util.*

class GalleryAdapter(var context: Context, var dataList: ArrayList<GalleryModel>) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivGallery: ImageView = view.findViewById(R.id.ivGallery)
        var tvGalleryName: Text_Normal = view.findViewById(R.id.tvGalleryName)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.gallery_item, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        Glide.with(context).load(data.ImageUrl)
            .into(holder.ivGallery)
        holder.tvGalleryName.setText(data.ImageName)



    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
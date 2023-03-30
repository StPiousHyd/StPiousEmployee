package stpious.student.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import stpious.employee.R
import stpious.employee.utills.customFonts.Text_Normal
import stpious.student.repository.model.TimeTableModel
import java.util.*


class TimeTableAdapter(var context: Context, var dataList: ArrayList<TimeTableModel>) :
    RecyclerView.Adapter<TimeTableAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvPeriod: Text_Normal = view.findViewById(R.id.tvPeriod)
        var tvSub_name: Text_Normal = view.findViewById(R.id.tvSub_name)
        var tvEmp_name: Text_Normal = view.findViewById(R.id.tvEmp_name)
        var tvDuration: Text_Normal = view.findViewById(R.id.tvDuration)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.timetable_view, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.tvPeriod.setText(data.PeriodName)
        holder.tvSub_name.setText(data.SubjectName)
        holder.tvEmp_name.setText(data.EmployeeName)
        holder.tvDuration.setText(data.Duration)


    }
//    fun downloadFile(url: URL, fileName: String) {
//        url.openStream().use { Files.copy(it, Paths.get(fileName)) }
//    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
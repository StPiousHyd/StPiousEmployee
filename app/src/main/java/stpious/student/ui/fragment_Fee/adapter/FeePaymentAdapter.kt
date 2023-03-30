package stpious.student.ui.fragment_Fee.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import stpious.employee.R
import stpious.employee.utills.customFonts.Text_Normal
import stpious.student.repository.model.FeePaymentModel
import stpious.student.repository.model.TimeTableModel
import java.util.*


class FeePaymentAdapter(var context: Context, var dataList: ArrayList<FeePaymentModel>) :
    RecyclerView.Adapter<FeePaymentAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvYear: Text_Normal = view.findViewById(R.id.tvYear)
        var tvSem: Text_Normal = view.findViewById(R.id.tvSem)
        var tvTotalAmt: Text_Normal = view.findViewById(R.id.tvTotalAmt)
        var tvPaidAmt: Text_Normal = view.findViewById(R.id.tvPaidAmt)
        var tvDueAmt: Text_Normal = view.findViewById(R.id.tvDueAmt)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.feepayment_view, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.tvYear.setText("Year : "+data.YearNo.toString())
        holder.tvSem.setText(data.Semester)
        holder.tvTotalAmt.setText("Total : Rs "+data.TotalAssignedFee.toString())
        holder.tvPaidAmt.setText("Paid : Rs "+data.TotalPaidAmount.toString())
        holder.tvDueAmt.setText("Due : Rs "+data.TotalDue.toString())


    }
//    fun downloadFile(url: URL, fileName: String) {
//        url.openStream().use { Files.copy(it, Paths.get(fileName)) }
//    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
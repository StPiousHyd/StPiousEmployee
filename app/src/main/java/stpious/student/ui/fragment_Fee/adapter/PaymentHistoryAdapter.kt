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
import stpious.student.repository.model.FeeHistoryModel
import stpious.student.repository.model.FeePaymentModel
import stpious.student.repository.model.TimeTableModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class PaymentHistoryAdapter(var context: Context, var dataList: ArrayList<FeeHistoryModel>) :
    RecyclerView.Adapter<PaymentHistoryAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvReceipt: Text_Normal = view.findViewById(R.id.tvReceipt)
        var tvHistoryDate: Text_Normal = view.findViewById(R.id.tvHistoryDate)
        var tvPaid: Text_Normal = view.findViewById(R.id.tvPaid)
        var tvMode: Text_Normal = view.findViewById(R.id.tvMode)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.paymenthistory_view, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.tvReceipt.setText("Receipt# : "+data.SystemReceiptNo.toString())
        holder.tvPaid.setText("Paid : Rs "+data.PayingAmount.toString())
        holder.tvMode.setText("Mode : "+data.payMode.toString())

        val from_date = data.ReceiptDate

        val format1 = SimpleDateFormat("yyyy-MM-dd")
        val dt1 = format1.parse(from_date)


        val from_month: DateFormat = SimpleDateFormat("MMM")
        val from_year: DateFormat = SimpleDateFormat("yyyy")
        val from_day: DateFormat = SimpleDateFormat("dd")
        val mMonth: String = from_month.format(dt1)
        val mYear: String = from_year.format(dt1)
        val mDay: String = from_day.format(dt1)
        holder.tvHistoryDate.setText(mMonth + " " + mDay + ", " + mYear)

    }
//    fun downloadFile(url: URL, fileName: String) {
//        url.openStream().use { Files.copy(it, Paths.get(fileName)) }
//    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
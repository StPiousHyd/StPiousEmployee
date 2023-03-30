//package stpious.student.ui.fragment_Fee.adapter
//
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import stpious.employee.R
//import stpious.employee.utills.customFonts.Text_Normal
//import stpious.student.repository.model.FeeHistoryModel
//import stpious.student.repository.model.TimeTableModel
//import java.text.DateFormat
//import java.text.SimpleDateFormat
//
//class HistoryViewAdapter(private val movieList: List<FeeHistoryModel>) : RecyclerView.Adapter<HistoryViewAdapter.RowViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
//        return RowViewHolder(itemView)
//    }
//
//    private fun setHeaderBg(view: View) {
//        view.setBackgroundResource(R.drawable.table_header_cell_bg)
//    }
//
//    private fun setContentBg(view: View) {
//        view.setBackgroundResource(R.drawable.table_content_cell_bg)
//    }
//
//    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
//        val rowPos = holder.adapterPosition
//
//        if (rowPos == 0) {
//            // Header Cells. Main Headings appear here
//            holder.apply {
//                setHeaderBg(tvReceipt)
//                setHeaderBg(tvPaid)
//                setHeaderBg(tvMode)
//                setHeaderBg(tvDate)
//
//                tvReceipt.text = "Receipt#"
//                tvPaid.text = "Paid"
//                tvMode.text = "Mode"
//                tvDate.text = "Date"
//            }
//        } else {
//            val modal = movieList[rowPos - 1]
//
//            holder.apply {
//                setContentBg(tvReceipt)
//                setContentBg(tvPaid)
//                setContentBg(tvMode)
//                setContentBg(tvDate)
//                val from_date = modal.ReceiptDate
//
//                val format1 = SimpleDateFormat("yyyy-MM-dd")
//                val dt1 = format1.parse(from_date)
//
//
//                val from_month: DateFormat = SimpleDateFormat("MMM")
//                val from_year: DateFormat = SimpleDateFormat("yyyy")
//                val from_day: DateFormat = SimpleDateFormat("dd")
//                val mMonth: String = from_month.format(dt1)
//                val mYear: String = from_year.format(dt1)
//                val mDay: String = from_day.format(dt1)
//                tvDate.setText(mMonth + " " + mDay + ", " + mYear)
//
//
//                tvPaid.text = modal.PayingAmount.toString()
//                tvMode.text = modal.payMode
//                tvReceipt.text = modal.SystemReceiptNo.toString()
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return movieList.size + 1 // one more to add header row
//    }
//
//    inner class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val tvReceipt: Text_Normal = itemView.findViewById(R.id.txtRank)
//        val tvPaid: Text_Normal = itemView.findViewById(R.id.txtMovieName)
//        val tvMode: Text_Normal = itemView.findViewById(R.id.txtYear)
//        val tvDate: Text_Normal = itemView.findViewById(R.id.txtCost)
//    }
//}
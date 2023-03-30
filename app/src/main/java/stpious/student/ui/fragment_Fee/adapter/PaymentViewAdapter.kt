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
//import stpious.student.repository.model.FeePaymentModel
//import stpious.student.repository.model.TimeTableModel
//import java.text.DateFormat
//import java.text.SimpleDateFormat
//
//class PaymentViewAdapter(private val movieList: List<FeePaymentModel>) : RecyclerView.Adapter<PaymentViewAdapter.RowViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.payment_item, parent, false)
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
//                setHeaderBg(txtYear)
//                setHeaderBg(txtSemester)
//                setHeaderBg(txtTotal)
//                setHeaderBg(txtPaid)
//                setHeaderBg(txtDue)
//
//                txtYear.text = "Year"
//                txtSemester.text = "Semester"
//                txtTotal.text = "Total"
//                txtPaid.text = "Paid"
//                txtDue.text = "Due"
//            }
//        } else {
//            val modal = movieList[rowPos - 1]
//
//            holder.apply {
//                setContentBg(txtYear)
//                setContentBg(txtSemester)
//                setContentBg(txtTotal)
//                setContentBg(txtPaid)
//                setContentBg(txtDue)
//
//
//                txtYear.text = modal.YearNo.toString()
//                txtSemester.text = modal.Semester.toString()
//                txtTotal.text = modal.TotalAssignedFee.toString()
//                txtPaid.text = modal.TotalPaidAmount.toString()
//                txtDue.text = modal.TotalDue.toString()
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return movieList.size + 1 // one more to add header row
//    }
//
//    inner class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val txtYear: Text_Normal = itemView.findViewById(R.id.txtYear)
//        val txtSemester: Text_Normal = itemView.findViewById(R.id.txtSemester)
//        val txtTotal: Text_Normal = itemView.findViewById(R.id.txtTotal)
//        val txtPaid: Text_Normal = itemView.findViewById(R.id.txtPaid)
//        val txtDue: Text_Normal = itemView.findViewById(R.id.txtDue)
//    }
//}
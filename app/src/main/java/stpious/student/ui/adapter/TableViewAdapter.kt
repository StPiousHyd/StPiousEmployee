package stpious.student.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import stpious.employee.R
import stpious.employee.utills.customFonts.Text_Normal
import stpious.student.repository.model.TimeTableModel

class TableViewAdapter(private val movieList: List<TimeTableModel>) : RecyclerView.Adapter<TableViewAdapter.RowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.timetable_item, parent, false)
        return RowViewHolder(itemView)
    }

    private fun setHeaderBg(view: View) {
        view.setBackgroundResource(R.drawable.table_header_cell_bg)
    }

    private fun setContentBg(view: View) {
        view.setBackgroundResource(R.drawable.table_content_cell_bg)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val rowPos = holder.adapterPosition

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            holder.apply {
                setHeaderBg(txtRank)
                setHeaderBg(txtMovieName)
                setHeaderBg(txtYear)
                setHeaderBg(txtCost)

                txtRank.text = "Period"
                txtMovieName.text = "Subject Name"
                txtYear.text = "Employee Name"
                txtCost.text = "Duration"
            }
        } else {
            val modal = movieList[rowPos - 1]

            holder.apply {
                setContentBg(txtRank)
                setContentBg(txtMovieName)
                setContentBg(txtYear)
                setContentBg(txtCost)

                txtRank.text = modal.PeriodName.toString()
                txtMovieName.text = modal.SubjectName
                txtYear.text = "SUJITH KUmar"
                txtCost.text = modal.Duration.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size + 1 // one more to add header row
    }

    inner class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtRank: Text_Normal = itemView.findViewById(R.id.txtRank)
        val txtMovieName: Text_Normal = itemView.findViewById(R.id.txtMovieName)
        val txtYear: Text_Normal = itemView.findViewById(R.id.txtYear)
        val txtCost: Text_Normal = itemView.findViewById(R.id.txtCost)
    }
}
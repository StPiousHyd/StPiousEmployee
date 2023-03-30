package stpious.student.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.qlx.myviejas.utills.sharedPreferences.UserSession
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import stpious.employee.R
import stpious.employee.utills.customFonts.Text_Bold
import stpious.employee.utills.customFonts.Text_Medium
import stpious.employee.utills.customFonts.Text_Normal
import stpious.student.repository.api.ApiServices
import stpious.student.repository.retrofitservice.ServiceBuilder
import java.text.DecimalFormat

class AttendenceActivity : AppCompatActivity() {

    private var mPresent: Float=0f
    private var mAbsent: Float=0f
    private var mHallticket: String=""
    lateinit var pieChart: PieChart
    private lateinit var ivBack: ImageView
    private lateinit var llback: LinearLayout
    private lateinit var tvHeadertitle: Text_Bold

    private lateinit var tvPresent_value: Text_Medium
    private lateinit var tvAbsent_value: Text_Medium

//    private lateinit var tvSubject: Text_Normal
    private lateinit var tvDayworking: Text_Normal
    private lateinit var tvDaypresent: Text_Normal

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.attendence_layout)

        mHallticket = UserSession(this@AttendenceActivity).getHallticket()


        initialise()

        // on below line we are setting user percent value,
        // setting description as enabled and offset for pie chart
        pieChart.setUsePercentValues(true)
        pieChart.getDescription().setEnabled(false)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

        // on below line we are setting drag for our pie chart
        pieChart.setDragDecelerationFrictionCoef(0.95f)

        // on below line we are setting hole
        // and hole color for pie chart
        pieChart.setDrawHoleEnabled(true)
        pieChart.setHoleColor(Color.WHITE)

        // on below line we are setting circle color and alpha
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)

        // on  below line we are setting hole radius
        pieChart.setHoleRadius(58f)
        pieChart.setTransparentCircleRadius(61f)

        // on below line we are setting center text
        pieChart.setDrawCenterText(true)

        // on below line we are setting
        // rotation for our pie chart
        pieChart.setRotationAngle(0f)

        // enable rotation of the pieChart by touch
        pieChart.setRotationEnabled(true)
        pieChart.setHighlightPerTapEnabled(true)

        // on below line we are setting animation for our pie chart
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        // on below line we are disabling our legend for pie chart
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)


    }

    private fun initialise() {
        llback = findViewById(R.id.llback)
        ivBack = findViewById(R.id.ivBack)
        tvHeadertitle = findViewById(R.id.tvHeadertitle)
        tvAbsent_value = findViewById(R.id.tvAbsent_value)
        tvPresent_value = findViewById(R.id.tvPresent_value)

//        tvSubject = findViewById(R.id.tvSubject)
        tvDaypresent = findViewById(R.id.tvDaypresent)
        tvDayworking = findViewById(R.id.tvDayworking)

        ivBack.visibility = View.VISIBLE
        tvHeadertitle.text = "Attendence"
        pieChart = findViewById(R.id.pieChart)



        llback.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        getAttendence(mHallticket)

    }

    private fun getAttendence(hallticket: String) {

//        pbar.visibility = View.VISIBLE
        val retrofitService: ApiServices =
            ServiceBuilder(this@AttendenceActivity).getRetrofit()!!.create()
        retrofitService.getAttendence(hallticket).enqueue(object : Callback<ResponseBody> {

            override fun onResponse(
                call: Call<ResponseBody>, response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    val string = response.body()!!.string()
                    Log.e("TAG", "hallticket**** : " + string)


                    val jsonObject = JSONObject(string)

//                    tvSubject.setText(jsonObject.getString("SemesterSubjectName"))

                     mPresent=jsonObject.getString("PresentPercentage").toFloat()
                     mAbsent=jsonObject.getString("AbsentPercentage").toFloat()



                    val mTotaldays=jsonObject.getString("Daysworking").toDouble()
                    val mPresentdays=jsonObject.getString("Dayspresent").toDouble()

                    tvDayworking.setText(jsonObject.getString("Daysworking"))
                    tvDaypresent.setText(jsonObject.getString("Dayspresent"))
                    val mPercentage=mPresentdays/mTotaldays*100


                    Log.e("TAG", "Percentage*** : "+String.format("%.0f", mPercentage) )
                    val mFinalPresent=String.format("%.0f", mPercentage).toInt()

                    val FinalAbsent=100-mFinalPresent
                    Log.e("TAG", "absent*** : "+FinalAbsent)

                    mPresent= mFinalPresent.toFloat()
                    mAbsent= FinalAbsent.toFloat()




                    tvAbsent_value.setText("Presence % \n"+mPresent)
                    tvPresent_value.setText("Absence % \n"+mAbsent)

//                    if(jsonObject.getString("AbsentPercentage").equals(0)){
//                        mAbsent=100f
//                    }

                    /*Pie Chart Data*/
                    // on below line we are creating array list and
                    // adding data to it to display in pie chart
                    val entries: ArrayList<PieEntry> = ArrayList()
                    entries.add(PieEntry(mPresent))
                    entries.add(PieEntry(mAbsent))

                    // on below line we are setting pie data set
                    val dataSet = PieDataSet(entries, "Mobile OS")

                    // on below line we are setting icons.
                    dataSet.setDrawIcons(false)

                    // on below line we are setting slice for pie
                    dataSet.sliceSpace = 3f
                    dataSet.iconsOffset = MPPointF(0f, 40f)
                    dataSet.selectionShift = 5f

                    // add a lot of colors to list
                    val colors: ArrayList<Int> = ArrayList()
                    colors.add(resources.getColor(R.color.colorPrimaryDark))
                    colors.add(resources.getColor(R.color.red))

                    // on below line we are setting colors.
                    dataSet.colors = colors

                    // on below line we are setting pie data set
                    val data = PieData(dataSet)
                    data.setValueFormatter(PercentFormatter())
                    data.setValueTextSize(10f)
                    data.setValueTypeface(Typeface.DEFAULT_BOLD)
                    data.setValueTextColor(Color.WHITE)
                    pieChart.setData(data)

                    // undo all highlights
                    pieChart.highlightValues(null)

                    // loading chart
                    pieChart.invalidate()

                }


            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@AttendenceActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@AttendenceActivity, HomeActivity::class.java))
        overridePendingTransition(R.anim.move_right_enter, R.anim.move_right_exit)
    }

}
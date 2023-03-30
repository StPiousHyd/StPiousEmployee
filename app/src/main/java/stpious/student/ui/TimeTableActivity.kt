package stpious.student.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qlx.myviejas.utills.sharedPreferences.UserSession
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import stpious.employee.R
import stpious.employee.utills.customFonts.Text_Bold
import stpious.employee.utills.customFonts.Text_Medium
import stpious.student.repository.api.ApiServices
import stpious.student.repository.model.TimeTableModel
import stpious.student.repository.retrofitservice.ServiceBuilder
import stpious.student.ui.adapter.TableViewAdapter
import stpious.student.ui.adapter.TimeTableAdapter

class TimeTableActivity : AppCompatActivity() {

    private var mFeedback: String = ""
    private var mHallticket: String = ""
    private lateinit var ivBack: ImageView
    private lateinit var llback: LinearLayout
    private lateinit var tvHeadertitle: Text_Bold
    private lateinit var tvEmpty: Text_Medium
    private lateinit var rv_timetable: RecyclerView
    private val timetableList: ArrayList<TimeTableModel> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timetable_layout)
        mHallticket=UserSession(this@TimeTableActivity).getHallticket()


        initialise()

        getTimeTable()

    }

    private fun initialise() {

        rv_timetable = findViewById(R.id.rv_timetable)
        llback = findViewById(R.id.llback)
        ivBack = findViewById(R.id.ivBack)
        tvHeadertitle = findViewById(R.id.tvHeadertitle)
        tvEmpty = findViewById(R.id.tvEmpty)

        ivBack.visibility=View.VISIBLE
        tvHeadertitle.text = "Time Table"

        val layoutmanager = LinearLayoutManager(this@TimeTableActivity)
        rv_timetable.layoutManager = layoutmanager
        rv_timetable.isNestedScrollingEnabled = false


        llback.setOnClickListener(View.OnClickListener {
           onBackPressed()
        })

    }



    private fun getTimeTable() {

//        pbar.visibility = View.VISIBLE
        val retrofitService: ApiServices =
            ServiceBuilder(this@TimeTableActivity).getRetrofit()!!.create()
        retrofitService.getTimetable(mHallticket).enqueue(object : Callback<ResponseBody> {

            override fun onResponse(
                call: Call<ResponseBody>, response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    val string = response.body()!!.string()
                    if(!string.equals("[]")){

                    Log.e("TAG", "hallticket**** : " + string)


                    val jsonarray = JSONArray(string)
                    for (i in 0 until jsonarray.length() ){
                        val jsonobj = jsonarray.getJSONObject(i)
                        val PeriodName=jsonobj.getString("PeriodName")
                        val Duration=jsonobj.getString("Duration")
                        val EmployeeName=jsonobj.getString("EmployeeName")
                        val SubjectName=jsonobj.getString("SubjectName")

                        timetableList.add(TimeTableModel(PeriodName,Duration,EmployeeName,SubjectName))

//                        Log.e("TAG", "pay_hisptory**** : " + jsonobj.getString("SystemReceiptNo"))

                    }
                    val adapter = TimeTableAdapter( this@TimeTableActivity,timetableList)
                    rv_timetable.adapter = adapter
//                    val value = jsonObject.getString("StudentName")

//                    val propic=jsonObject.getString("ProfilePicPath")



                    }else{
                        tvEmpty.visibility=View.VISIBLE
                        rv_timetable.visibility=View.GONE
                    }
                } else {

                }



            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@TimeTableActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
        overridePendingTransition(R.anim.move_right_enter, R.anim.move_right_exit)
    }
}

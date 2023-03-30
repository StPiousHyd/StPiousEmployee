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
import stpious.employee.utills.customFonts.Text_Normal
import stpious.student.repository.api.ApiServices
import stpious.student.repository.model.NoticeModel
import stpious.student.repository.retrofitservice.ServiceBuilder
import stpious.student.ui.adapter.NoticeAdapter

class NoticesActivity : AppCompatActivity() {

    private var mFeedback: String = ""
    private var mHallticket: String = ""
    private lateinit var ivBack: ImageView
    private lateinit var llback: LinearLayout
    private lateinit var tvHeadertitle: Text_Bold
    private lateinit var tvEmpty: Text_Normal
    private lateinit var rv_notice: RecyclerView
    private val noticeList: ArrayList<NoticeModel> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notice_layout)
        mHallticket=UserSession(this@NoticesActivity).getHallticket()


        initialise()

        getNotice()

    }

    private fun initialise() {

        rv_notice = findViewById(R.id.rv_notice)
        llback = findViewById(R.id.llback)
        ivBack = findViewById(R.id.ivBack)
        tvHeadertitle = findViewById(R.id.tvHeadertitle)
        tvEmpty = findViewById(R.id.tvEmpty)

        ivBack.visibility=View.VISIBLE
        tvHeadertitle.text = "Notice"

        val layoutmanager = LinearLayoutManager(this@NoticesActivity)
        rv_notice.layoutManager = layoutmanager
        rv_notice.isNestedScrollingEnabled = false
//        rv_history.addItemDecoration(Divdivider.xmliderItemDecoration(rv_history.getContext(), DividerItemDecoration.VERTICAL))
//        val dividerItemDecoration: RecyclerView.ItemDecoration =
//            DividerItemDecorator(
//                ContextCompat.getDrawable(
//                    rv_notice.context,
//                    R.drawable.divider))
//        rv_notice.addItemDecoration(dividerItemDecoration)


        llback.setOnClickListener(View.OnClickListener {
           onBackPressed()
        })

    }



    private fun getNotice() {

//        pbar.visibility = View.VISIBLE
        val retrofitService: ApiServices =
            ServiceBuilder(this@NoticesActivity).getRetrofit()!!.create()
        retrofitService.getNotices(mHallticket).enqueue(object : Callback<ResponseBody> {

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
                        val subject=jsonobj.getString("CircularSubject")
                        val date=jsonobj.getString("CreatedDate")
                        val file=jsonobj.getString("FilePath")

                        noticeList.add(NoticeModel(subject,file,date))

//                        Log.e("TAG", "pay_hisptory**** : " + jsonobj.getString("SystemReceiptNo"))

                    }
                    val adapter = NoticeAdapter(this@NoticesActivity, noticeList)
                    rv_notice.adapter = adapter
//                    val value = jsonObject.getString("StudentName")

//                    val propic=jsonObject.getString("ProfilePicPath")



                    }else{
                        tvEmpty.visibility=View.VISIBLE
                        rv_notice.visibility=View.GONE
                    }
                } else {

                }



            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@NoticesActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
        overridePendingTransition(R.anim.move_right_enter, R.anim.move_right_exit)
    }
}

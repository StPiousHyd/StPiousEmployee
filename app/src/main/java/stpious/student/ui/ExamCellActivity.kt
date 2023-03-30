package stpious.student.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.qlx.myviejas.utills.sharedPreferences.UserSession
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import stpious.employee.R
import stpious.student.repository.api.ApiServices
import stpious.student.repository.retrofitservice.ServiceBuilder
import stpious.employee.utills.customFonts.Button_Normal
import stpious.employee.utills.customFonts.EditeText_font
import stpious.employee.utills.customFonts.Text_Bold

class ExamCellActivity : AppCompatActivity() {

    private var mHallticket: String = ""
    private lateinit var ivBack: ImageView
    private lateinit var llback: LinearLayout
    private lateinit var tvHeadertitle: Text_Bold

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.examcell_layout)
        mHallticket=UserSession(this@ExamCellActivity).getHallticket()


        initialise()


    }

    private fun initialise() {


        llback = findViewById(R.id.llback)
        ivBack = findViewById(R.id.ivBack)
        tvHeadertitle = findViewById(R.id.tvHeadertitle)

        ivBack.visibility=View.VISIBLE
        tvHeadertitle.text = "Exam Cell"


        llback.setOnClickListener(View.OnClickListener {
           onBackPressed()
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
        overridePendingTransition(R.anim.move_right_enter, R.anim.move_right_exit)
    }
}

package stpious.student.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.qlx.myviejas.utills.sharedPreferences.UserSession
import stpious.employee.R
import stpious.employee.utills.customFonts.Text_Bold

class EventsActivity : AppCompatActivity() {

    private var mHallticket: String = ""
    private lateinit var ivBack: ImageView
    private lateinit var llback: LinearLayout
    private lateinit var tvHeadertitle: Text_Bold

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.eventscell_layout)
        mHallticket=UserSession(this@EventsActivity).getHallticket()


        initialise()


    }

    private fun initialise() {


        llback = findViewById(R.id.llback)
        ivBack = findViewById(R.id.ivBack)
        tvHeadertitle = findViewById(R.id.tvHeadertitle)

        ivBack.visibility=View.VISIBLE
        tvHeadertitle.text = "Events"


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

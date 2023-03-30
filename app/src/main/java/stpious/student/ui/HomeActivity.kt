package stpious.student.ui


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.qlx.myviejas.utills.sharedPreferences.UserSession
import org.json.JSONObject
import prajna.app.ui.HomeFragment
import stpious.employee.R
import stpious.student.ui.fragment.FeeFragment
import stpious.student.ui.fragment.NotifyFragment

class HomeActivity : AppCompatActivity() {

    private var mUsername: String=""
    private var mId: String=""
    private var mScreen: String=""
    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homescreen)

        mId=intent.getStringExtra("id").toString()

        mUsername= UserSession(this@HomeActivity).getHallticket()

        val dict_json = JSONObject()
        val jsonParser = JsonParser()
        dict_json.put("ParentMobileNumber", mUsername)
        val gsonObject = jsonParser.parse(dict_json.toString()) as JsonObject
        Log.e("TAG", "exper_json: " + gsonObject)



        if(mId.equals("fee")){
            loadFragment(FeeFragment())
        }else if(mId.equals("notify")){
            loadFragment(NotifyFragment())
        }else{
            loadFragment(HomeFragment())
        }
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.message -> {
                    loadFragment(NotifyFragment())
                    true
                }
                R.id.settings -> {
                    loadFragment(FeeFragment())
                    true
                }
                else -> throw AssertionError()

            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
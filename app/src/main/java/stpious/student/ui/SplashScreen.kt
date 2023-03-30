package stpious.student.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.qlx.myviejas.utills.sharedPreferences.UserSession
import stpious.employee.R
import stpious.student.login.LoginActivity

class SplashScreen : AppCompatActivity() {

     var mHallticket:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)
        mHallticket=UserSession(this@SplashScreen).getHallticket()

        // This is used to hide the status bar and make 
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )




//  mToken=UserSession(this@SplashScreen).gettokenData()
//        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({

            if(mHallticket.equals("")){
                startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
                overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
                finish()
            }else{
                startActivity(Intent(this@SplashScreen, HomeActivity::class.java))
                overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
                finish()
            }


        }, 2000) // 3000 is the delayed time in milliseconds.
    }
}
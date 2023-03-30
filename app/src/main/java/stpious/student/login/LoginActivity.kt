package stpious.student.login

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.qlx.myviejas.utills.sharedPreferences.UserSession
import okhttp3.ResponseBody
import org.json.JSONObject
import stpious.employee.utills.customFonts.EditeText_font
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import stpious.employee.R
import stpious.student.repository.api.ApiServices
import stpious.student.repository.retrofitservice.ServiceBuilder
import stpious.student.ui.HomeActivity


class LoginActivity : AppCompatActivity(), OnClickListener {
    private lateinit var etUsername: EditeText_font
    private lateinit var etPassword: EditeText_font
    private lateinit var show_pass_btn: ImageView

    var mUsername: String = ""
    var mPassword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        initView()
    }

    private fun initView() {
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        show_pass_btn = findViewById(R.id.show_pass_btn)


    }

    override fun onClick(v: View) {
        val i = v.id
        if (i == R.id.btLogin) {
            mUsername = etUsername.text.toString().trim()
            mPassword = etPassword.text.toString().trim()
            if (validate()) {
                val dict_json = JSONObject()
                val jsonParser = JsonParser()
                dict_json.put("AdmissionNumber", mUsername)
                dict_json.put("Password", mPassword)
                val gsonObject = jsonParser.parse(dict_json.toString()) as JsonObject
//                Log.e("TAG", "exper_json: " + gsonObject)
                getLogin(gsonObject)
            }

        } else if (i == R.id.show_pass_btn) {
            if (etPassword.getTransformationMethod()
                    .equals(PasswordTransformationMethod.getInstance())
            ) {
                (v as ImageView).setImageResource(R.drawable.ic_hide)
                //Show Password
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
            } else {
                (v as ImageView).setImageResource(R.drawable.ic_show)
                //Hide Password
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
            }

        }

    }


    private fun validate(): Boolean {
        var valid = true

        if (mUsername.isEmpty()) {
//            etUsername.error = "Please enter Username"
            Toast.makeText(this@LoginActivity, "Please enter Hallticket", Toast.LENGTH_SHORT).show()
            valid = false
        } else if (mPassword.isEmpty()) {
//            etPassword.error = "Please enter Password"
            Toast.makeText(this@LoginActivity, "Please enter Password", Toast.LENGTH_SHORT).show()
            valid = false
        }
        return valid
    }

    private fun getLogin(gsonObject: JsonObject) {

//        pbar.visibility = View.VISIBLE
        val retrofitService: ApiServices =
            ServiceBuilder(this@LoginActivity).getRetrofit()!!.create()
        retrofitService.getLogin(gsonObject).enqueue(object : Callback<ResponseBody> {

            override fun onResponse(
                call: Call<ResponseBody>, response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    val string = response.body()!!.string()
                    Log.e("TAG", "hallticket**** : " + string)
                    UserSession(this@LoginActivity).setHallticket(string)
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT)
                        .show()

                }

//                    val string = response.body()!!.string()
//                Log.e("TAG", "onResponse: " + string)
//
//                if(string.equals("1")){
//                    UserSession(this@LoginActivity).setLoginSave(mUsername)
//
////                Log.e("TAG", "onResponse() called with: call = [" + call.request().url + "], response = [" + string + "]");
//
//                    startActivity(Intent(this@LoginActivity, Homescreen::class.java))
//                    overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
//                    finish()
//                }else{
//                    Toast.makeText(this@LoginActivity,"Invalid Credentials",Toast.LENGTH_SHORT).show()
//
////                Log.e("TAG", "onResponse() called with: call = [" + call.request().url + "], response = [" + string + "]");
//
//                }


            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}
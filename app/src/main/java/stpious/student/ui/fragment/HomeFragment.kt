package prajna.app.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.qlx.myviejas.utills.sharedPreferences.UserSession
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import stpious.employee.R
import stpious.employee.utills.customFonts.Button_Normal
import stpious.student.login.LoginActivity
import stpious.student.repository.api.ApiServices
import stpious.student.repository.retrofitservice.ServiceBuilder
import stpious.employee.utills.customFonts.Text_Bold
import stpious.employee.utills.customFonts.Text_Medium
import stpious.employee.utills.customFonts.Text_Normal
import stpious.student.ui.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), OnClickListener {

    private var mHallticket: String = ""
    private lateinit var ivProfilepic: ImageView
    private lateinit var tvHeadertitle: Text_Bold
    private lateinit var tvName: Text_Medium
    private lateinit var tvSemester: Text_Normal
    private lateinit var tvAcademic: Text_Normal
    private lateinit var tvPhone: Text_Normal
    private lateinit var tvGroup: Text_Normal
    private lateinit var tvPrograme: Text_Normal

    private lateinit var ivAttendence: ImageView
    private lateinit var ivFeePay: ImageView
    private lateinit var ivExam: ImageView
    private lateinit var ivLearning: ImageView
    private lateinit var btRecentNotity: Button_Normal


    private lateinit var ivFeedback: ImageView
    private lateinit var ivGallery: ImageView
    private lateinit var ivEvents: ImageView
    private lateinit var ivCollageWeb: ImageView
    private lateinit var ivSyllabus: ImageView
    private lateinit var ivTimetable: ImageView
    private lateinit var ivNotice: ImageView

    private lateinit var tvLogout: Text_Bold

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        mHallticket = UserSession(requireActivity()).getHallticket()

        initView(rootView)

        getProfile(mHallticket)


//        textView.setOnClickListener {
//            (requireActivity() as Homescreen).setSelectedItem(2)
//            (requireActivity() as Homescreen).removeBadge(2)
//        }

//        (requireActivity() as Homescreen).setBadge(2)
//        (requireActivity() as Homescreen).setBadge(0)
        return rootView

    }

    fun initView(v: View) {
        ivProfilepic = v.findViewById(R.id.ivProfilepic)
        tvHeadertitle = v.findViewById(R.id.tvHeadertitle)

        tvName = v.findViewById(R.id.tvName)
        tvPrograme = v.findViewById(R.id.tvPrograme)
        tvGroup = v.findViewById(R.id.tvGroup)
        tvSemester = v.findViewById(R.id.tvSemester)
        tvAcademic = v.findViewById(R.id.tvAcademic)
        tvPhone = v.findViewById(R.id.tvPhone)

        ivAttendence = v.findViewById(R.id.ivAttendence)
        ivFeePay = v.findViewById(R.id.ivFeePay)
        ivExam = v.findViewById(R.id.ivExam)
        ivLearning = v.findViewById(R.id.ivLearning)
        btRecentNotity=v.findViewById(R.id.btRecentNotity)
        tvLogout=v.findViewById(R.id.tvLogout)
        tvLogout.visibility=View.VISIBLE

        ivFeedback=v.findViewById(R.id.ivFeedback)
        ivGallery=v.findViewById(R.id.ivGallery)
        ivEvents=v.findViewById(R.id.ivEvents)
        ivCollageWeb=v.findViewById(R.id.ivCollageWeb)
        ivSyllabus=v.findViewById(R.id.ivSyllabus)
        ivTimetable=v.findViewById(R.id.ivTimetable)
        ivNotice=v.findViewById(R.id.ivNotice)


        ivFeedback.setOnClickListener(this)
        ivGallery.setOnClickListener(this)
        ivEvents.setOnClickListener(this)
        ivCollageWeb.setOnClickListener(this)
        ivSyllabus.setOnClickListener(this)
        ivTimetable.setOnClickListener(this)
        ivNotice.setOnClickListener(this)

        ivAttendence.setOnClickListener(this)
        ivFeePay.setOnClickListener(this)
        ivExam.setOnClickListener(this)
        ivLearning.setOnClickListener(this)
        btRecentNotity.setOnClickListener(this)
        tvLogout.setOnClickListener(this)

        tvHeadertitle.setText("Hallticket : " + mHallticket)

//        val layoutmanager = LinearLayoutManager(requireActivity())
//        rv_home.layoutManager = layoutmanager
//        rv_home.layoutManager = GridLayoutManager(requireActivity(), 3)
//        val eventadapter = HomeOptionAdapter(requireActivity())
//        rv_home.adapter = eventadapter

    }

    private fun getProfile(hallticket: String) {

//        pbar.visibility = View.VISIBLE
        val retrofitService: ApiServices =
            ServiceBuilder(requireActivity()).getRetrofit()!!.create()
        retrofitService.getProfile(hallticket).enqueue(object : Callback<ResponseBody> {

            override fun onResponse(
                call: Call<ResponseBody>, response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    val string = response.body()!!.string()
                    Log.e("TAG", "hallticket**** : " + string)


                    val jsonObject = JSONObject(string)
                    val value = jsonObject.getString("StudentName")

                    Log.e("TAG", "hallticket**** : " + value)
//                    val propic=jsonObject.getString("ProfilePicPath")

                    tvName.setText(jsonObject.getString("StudentName"))
                    tvSemester.setText(jsonObject.getString("SemesterName"))
                    tvGroup.setText(jsonObject.getString("GroupName"))
                    tvPrograme.setText(jsonObject.getString("ProgrammeName"))
                    tvAcademic.setText(jsonObject.getString("AcademicYearRegulation"))
                    tvPhone.setText(jsonObject.getString("MobileNo"))


                    Glide.with(requireActivity()).load(jsonObject.getString("ProfilePicPath"))
                        .into(ivProfilepic)


                } else {

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
                Toast.makeText(requireActivity(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onClick(v: View) {
        val i = v.id
        if (i == R.id.ivAttendence) {
            startActivity(Intent(requireActivity(), AttendenceActivity::class.java))
            requireActivity().overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
            requireActivity().finish()
        } else if (i == R.id.ivFeePay) {
            startActivity(Intent(requireActivity(), HomeActivity::class.java).putExtra("id","fee"))


        } else if (i == R.id.ivExam) {
            startActivity(Intent(requireActivity(), ExamCellActivity::class.java))
            requireActivity().overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
            requireActivity().finish()



        } else if (i == R.id.ivLearning) {
            val intent = Intent(requireActivity(), WebviewActivity::class.java)
            intent.putExtra("url", "https://learning.stpiouscollegehyd.in/")
            intent.putExtra("title", "Learning")
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
            requireActivity().finish()



        }else if (i == R.id.tvLogout) {

            UserSession(requireActivity()).removeHallticket()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
            requireActivity().finish()

        }else if (i == R.id.ivNotice) {
            startActivity(Intent(requireActivity(), NoticesActivity::class.java))
            requireActivity().overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
            requireActivity().finish()

        }else if (i == R.id.ivTimetable) {
            startActivity(Intent(requireActivity(), TimeTableActivity::class.java))
            requireActivity().overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
            requireActivity().finish()

        }else if (i == R.id.ivSyllabus) {
            startActivity(Intent(requireActivity(), SyllabusActivity::class.java))
            requireActivity().overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
            requireActivity().finish()

        }else if (i == R.id.ivCollageWeb) {
            val intent = Intent(requireActivity(), WebviewActivity::class.java)
            intent.putExtra("url", "http://www.stannscollegehyd.com/")
            intent.putExtra("title", "College Website")
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
            requireActivity().finish()

        }else if (i == R.id.ivEvents) {
            startActivity(Intent(requireActivity(), EventsActivity::class.java))
            requireActivity().overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
            requireActivity().finish()

        }else if (i == R.id.ivGallery) {

            startActivity(Intent(requireActivity(), GalleryActivity::class.java))
            requireActivity().overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
            requireActivity().finish()
        }else if (i == R.id.ivFeedback) {
            startActivity(Intent(requireActivity(), FeedbackActivity::class.java))
            requireActivity().overridePendingTransition(R.anim.move_left_enter, R.anim.move_left_exit)
            requireActivity().finish()


        }else if (i == R.id.btRecentNotity) {
            startActivity(Intent(requireActivity(), HomeActivity::class.java).putExtra("id","notify"))



        }
    }

}

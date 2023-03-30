package stpious.student.repository.api

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {


    /*Profile Data*/
    @POST("/api/Student/StudentProfileDate")
    fun getProfile(@Body hallticket: String): Call<ResponseBody>


    @POST("/api/Student/GetFeeHistory")
    fun getFeeHistory(@Body hallticket: String): Call<ResponseBody>

    @POST("/api/Student/GetFeeSummary")
    fun getFeePayment(@Body hallticket: String): Call<ResponseBody>

    @POST("/api/Student/GetAttendancePercentage")
    fun getAttendence(@Body hallticket: String): Call<ResponseBody>

    @POST("/api/Student/GetCirculars")
    fun getNotices(@Body hallticket: String): Call<ResponseBody>

    @POST("/api/Student/GetSyllabusDetailsForStudent")
    fun getSyllabus(@Body hallticket: String): Call<ResponseBody>

    @POST("/api/Student/GetTimeTable")
    fun getTimetable(@Body hallticket: String): Call<ResponseBody>

    @POST("/api/Student/GetGallery")
    fun getGallery(@Body hallticket: String): Call<ResponseBody>

    @POST("/api/Student/ValidateLogin")
    fun getLogin(@Body jsonObject: JsonObject): Call<ResponseBody>


    @POST("/api/Student/SaveFeeback")
    fun saveFeedback(@Body jsonObject: JsonObject): Call<ResponseBody>

    @POST("/api/FeeDetails/GetStudentFeeDetails")
    fun getFeeDetails(@Body jsonObject: JsonObject): Call<ResponseBody>


}

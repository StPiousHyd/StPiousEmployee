package stpious.student.ui.fragment_Fee

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.Toast
import androidx.fragment.app.Fragment
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
import stpious.student.repository.api.ApiServices
import stpious.student.repository.model.FeePaymentModel
import stpious.student.repository.retrofitservice.ServiceBuilder
import stpious.student.ui.fragment_Fee.adapter.FeePaymentAdapter

/**
 * A simple [Fragment] subclass.
 */
class FeePayment : Fragment() {
    private lateinit var tbHistory: TableRow
    private lateinit var tbPayment: TableRow
    private var mHallticket: String=""
    private lateinit var rv_history: RecyclerView
    private val paymentList: ArrayList<FeePaymentModel> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.payment_history, container, false)

        mHallticket = UserSession(requireActivity()).getHallticket()

        initView(rootView)

        getFeePayment(mHallticket)

        return  rootView

    }

    private fun initView(v: View) {
        tbHistory=v.findViewById(R.id.tbHistory)
        tbPayment=v.findViewById(R.id.tbPayment)
        rv_history=v.findViewById(R.id.rv_history)

        tbHistory.visibility=View.GONE
        tbPayment.visibility=View.GONE
        val layoutmanager = LinearLayoutManager(requireActivity())
        rv_history.layoutManager = layoutmanager
        rv_history.isNestedScrollingEnabled = false
//        rv_history.addItemDecoration(Divdivider.xmliderItemDecoration(rv_history.getContext(), DividerItemDecoration.VERTICAL))
//        val dividerItemDecoration: RecyclerView.ItemDecoration =
//            DividerItemDecorator(
//                ContextCompat.getDrawable(
//                    rv_history.context,
//                    R.drawable.divider
//                )
//            )
//        rv_history.addItemDecoration(dividerItemDecoration)

    }

    private fun getFeePayment(hallticket: String) {

//        pbar.visibility = View.VISIBLE
        val retrofitService: ApiServices =
            ServiceBuilder(requireActivity()).getRetrofit()!!.create()
        retrofitService.getFeePayment(hallticket).enqueue(object : Callback<ResponseBody> {

            override fun onResponse(
                call: Call<ResponseBody>, response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    val string = response.body()!!.string()
                    Log.e("TAG", "hallticket**** : " + string)


                    val jsonarray = JSONArray(string)
                    for (i in 0 until jsonarray.length() ){
                        val jsonobj = jsonarray.getJSONObject(i)
                        val YearNo=jsonobj.getString("YearNo").toInt()
                        val Semester=jsonobj.getString("Semester")
                        val TotalAssignedFee=jsonobj.getString("TotalAssignedFee").toFloat()
                        val TotalPaidAmount=jsonobj.getString("TotalPaidAmount").toFloat()
                        val TotalDue=jsonobj.getString("TotalDue").toFloat()
                        paymentList.add(FeePaymentModel(YearNo,Semester,TotalAssignedFee,TotalPaidAmount,TotalDue))

//                        Log.e("TAG", "pay_hisptory**** : " + jsonobj.getString("SystemReceiptNo"))

                    }
                    val adapter = FeePaymentAdapter(requireActivity(),paymentList)
                    rv_history.adapter = adapter
//                    val value = jsonObject.getString("StudentName")

//                    val propic=jsonObject.getString("ProfilePicPath")



                } else {

                }



            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(requireActivity(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }


}

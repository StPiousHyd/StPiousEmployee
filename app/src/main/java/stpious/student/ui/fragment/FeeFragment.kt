package stpious.student.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.qlx.myviejas.utills.sharedPreferences.UserSession
import stpious.employee.R
import stpious.employee.utills.customFonts.Text_Bold
import stpious.student.ui.HomeActivity
import stpious.student.ui.fragment.adapter.NetworkViewPagerAdapter


/**
 * A simple [Fragment] subclass.
 */
class FeeFragment : Fragment() {

    private lateinit var mContext: Context
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var adapter: NetworkViewPagerAdapter
    private lateinit var tvHeadertitle: Text_Bold


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_fee, container, false)

        UserSession(requireActivity()).setScreen("3")
//        mHallticket = UserSession(requireActivity()).getHallticket()

        initialise(rootView)



        return rootView
    }

    private fun initialise(view: View) {
        mContext = requireContext()
        tvHeadertitle = view.findViewById(R.id.tvHeadertitle)
        tvHeadertitle.setText("Fee")
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.isSaveEnabled = false
        viewPager.isSaveFromParentEnabled = false
        tabLayout = view.findViewById(R.id.tabs)

        adapter =
            NetworkViewPagerAdapter(requireContext(), childFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }


    //Pressed return button - returns to the results menu
    override fun onResume() {
        super.onResume()
        requireView().isFocusableInTouchMode = true
        requireView()!!.requestFocus()
        requireView()!!.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
//                Toast.makeText(activity, "Back Pressed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(requireActivity(), HomeActivity::class.java).putExtra("id","home"))

                //your code
                true
            } else false
        }
    }

}

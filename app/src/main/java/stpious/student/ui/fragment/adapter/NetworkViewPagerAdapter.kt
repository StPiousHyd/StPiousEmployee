package stpious.student.ui.fragment.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import stpious.student.ui.fragment_Fee.FeePayment
import stpious.student.ui.fragment_Fee.PaymentHistory

class NetworkViewPagerAdapter(
    private val context: Context,
    fm: FragmentManager,
    internal var totaltabs: Int
) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return FeePayment()
            }
            1 -> {
                return PaymentHistory()
            }
            else -> {
                throw IllegalStateException("$position is illegal staement")
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            0 -> title = "fee payment"
            1 -> title = "payment history"
        }
        return title
    }

    fun getItemCount(): Int {
        return 2
    }
}
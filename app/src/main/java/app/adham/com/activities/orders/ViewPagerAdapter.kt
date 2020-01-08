package app.adham.com.activities.orders

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager

import java.util.ArrayList

import app.adham.com.R
import app.adham.com.data.model.booking.BookingsList
import app.adham.com.fragment.order.OrderFragment

class ViewPagerAdapter(private val context: Context, manager: FragmentManager,
                       private val orders: List<List<BookingsList>>)
    : FragmentStatePagerAdapter(manager) {
    private val mFragmentList = ArrayList<OrderFragment>()
    private val mFragmentTitleList = ArrayList<String>()

    init {
        setDefaultTitleandFrgament()
    }

    private fun setDefaultTitleandFrgament() {
        mFragmentTitleList.add(context.resources.getString(R.string.pending))
        mFragmentTitleList.add(context.resources.getString(R.string.approved))
        mFragmentTitleList.add(context.resources.getString(R.string.rejected))

        if (orders.isNotEmpty()) {
            mFragmentList.add(OrderFragment.newInstance(0, orders[0]))
            mFragmentList.add(OrderFragment.newInstance(1, orders[1]))
            mFragmentList.add(OrderFragment.newInstance(2, orders[2]))
        }
    }

    override fun getItem(position: Int): Fragment {
        /*  switch (position) {
            case 0:
                return new OrderFragment();

            case 1:
                return new OrderListFragment();

            case 2:
                return new OrderListFragment();

            case 3:
                return new OrderListFragment(getFilterOrder("303"));
        }
        return new NullFragment();*/
        return mFragmentList[position]
    }

    override fun getItemPosition(`object`: Any): Int {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun search(position: Int, str: String) {
        mFragmentList[position].search(str)
    }

}

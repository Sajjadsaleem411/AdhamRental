package app.adham.com.activities.orders

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import android.widget.Toast
import app.adham.com.AdhamApp
import app.adham.com.R
import app.adham.com.activities.LoginActivity
import app.adham.com.data.model.booking.BookingResponse
import app.adham.com.data.model.booking.BookingsList
import app.adham.com.data.remote.ApiHelper
import app.adham.com.fragment.order.OrderAdpater
import app.adham.com.fragment.order.OrderFragment
import app.adham.com.utils.CommonUtils
import app.adham.com.utils.CommonUtils.orderStatus
import app.adham.com.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_orders.*
import kotlinx.android.synthetic.main.fragment_order.*
import retrofit2.Call
import retrofit2.Response
import android.widget.EditText
import android.widget.ImageView

class OrdersActivity : AppCompatActivity() {

    lateinit var orders: List<List<BookingsList>>
    lateinit var viewPager: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        orders = ArrayList()

        setUI()
    }

    private fun setUI() {
        //  val viewPager = view.findViewById(R.id.pager_orders) as ViewPager
        viewPager = ViewPagerAdapter(this@OrdersActivity, supportFragmentManager, ArrayList() )
        pagerOrders.adapter = viewPager
        callAPI("0")
        /*   val searchEditText = svToolbar.findViewById(android.support.v7.appcompat.R.id.search_src_text) as EditText
           searchEditText.setTextColor(resources.getColor(R.color.white))
           searchEditText.setHintTextColor(resources.getColor(R.color.white))
           val searchMagIcon = svToolbar.findViewById(android.support.v7.appcompat.R.id.search_mag_icon) as ImageView
           searchMagIcon.setImageResource(R.drawable.ic_search_white)*/
        /* val crossIcon = svToolbar.findViewById(android.support.v7.appcompat.R.id.search_close_btn) as ImageView
         crossIcon.setImageResource(R.drawable.ic_)*/

        ivOrderLogout.setOnClickListener {
            CommonUtils.showLoadingDialog(this)
            AdhamApp.mPreference.sessionMethod(AdhamApp.mPreference.isLogin, false)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        ivToolbarSearch.setOnClickListener {
            ivToolbarSearch.visibility = View.GONE
            tvToolbarTitle.visibility = View.GONE
            svToolbar.visibility = View.VISIBLE
            svToolbar.isIconified = false
        }
        svToolbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                /*  if(homeFragment!=null){
                      homeFragment.search(newText)
                  }*/
                if (viewPager != null) {
                    viewPager.search(pagerOrders.currentItem, newText)
                }
                return false
            }
        })
        svToolbar.setOnCloseListener {
            svToolbar.visibility = View.GONE
            ivToolbarSearch.visibility = View.VISIBLE
            tvToolbarTitle.visibility = View.VISIBLE
            false
        }
        tablayoutOrders.setupWithViewPager(pagerOrders)
    }

    fun callAPI(bookingID: String) {

        if (NetworkUtils.isNetworkConnected(this)) {
            val dialog = CommonUtils.showLoadingDialog(this)

            val apiService =
                    AdhamApp.retrofit.create(ApiHelper::class.java)
            val call = apiService.getBookingOrder(bookingID)
            call.enqueue(object : retrofit2.Callback<BookingResponse> {
                override fun onResponse(call: Call<BookingResponse>, response: Response<BookingResponse>) {
                    var data = response.body()
                    if (data?.status == 1) {
                        var orders = giveOrderList(data.bookingsList)
                        //    rvOrder.adapter= OrderAdpater(activity, data.bookingsList, arguments!!.getInt(OrderFragment.STATUS))
                        //    rvEconomyCar.adapter = EconomyCarAdpater(activity!!,data.carsList,brandID)

                        viewPager = ViewPagerAdapter(this@OrdersActivity, supportFragmentManager, orders)
                        pagerOrders.adapter = viewPager
                  //      Toast.makeText(this@OrdersActivity, resources.getString(R.string.orderStatusChange), Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this@OrdersActivity, data!!.description, Toast.LENGTH_SHORT).show()

                    }
                    dialog.dismiss()
                    // ShowDirecton(data!!)
                }

                override fun onFailure(call: Call<BookingResponse>, t: Throwable) {
                    Toast.makeText(this@OrdersActivity, resources.getString(R.string.internetError), Toast.LENGTH_SHORT).show()

                    dialog.dismiss()
                    // Log error here since request failed
                    Log.e("data", t.toString())
                }
            })
        }
    }

    fun giveOrderList(bookingList: List<BookingsList>): List<List<BookingsList>> {
        var orders: List<List<BookingsList>>
        orders = ArrayList()
        var pendingOrders = ArrayList<BookingsList>()
        var acceptOrders = ArrayList<BookingsList>()
        var cancelOrders = ArrayList<BookingsList>()

        for (order in bookingList) {
            //Pending
            if (order.status.equals(orderStatus[0])) {
                pendingOrders.add(order)
            }
            //accept
            else if (order.status.equals(orderStatus[1])) {
                acceptOrders.add(order)
            }
            //cancel
            else if (order.status.equals(orderStatus[2])) {
                cancelOrders.add(order)
            }
        }

        orders.add(pendingOrders)
        orders.add(acceptOrders)
        orders.add(cancelOrders)

        return orders
    }

    public fun refresh() {
        callAPI("0")
    }
}

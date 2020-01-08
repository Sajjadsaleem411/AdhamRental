package app.adham.com.fragment.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import app.adham.com.AdhamApp
import app.adham.com.R
import app.adham.com.activities.orders.OrdersActivity
import app.adham.com.data.model.booking.BookingResponse
import app.adham.com.data.model.booking.BookingsList
import app.adham.com.data.model.car.CarsList
import app.adham.com.data.remote.ApiHelper
import app.adham.com.fragment.home.EconomyCarAdpater
import app.adham.com.utils.CommonUtils
import app.adham.com.utils.NetworkUtils
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_order.*
import retrofit2.Call
import retrofit2.Response
import java.io.Serializable


class OrderFragment : Fragment(), View.OnClickListener, OrderAdpater.OrderClickListener {

    override fun onItemClick() {
        var orderActivity = activity as OrdersActivity
        orderActivity.refresh()
    }

    companion object {
        private val ORDERS = "ORDERs"
        private const val STATUS = "my_int"

        fun newInstance(anInt: Int, orders: List<BookingsList>) = OrderFragment().apply {
            arguments = Bundle(1).apply {
                putInt(STATUS, anInt)
                putSerializable(ORDERS, orders as Serializable)
            }
        }
    }

    private var orders: List<BookingsList>? = null
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)

        setUI(view)

        return view
    }

    private fun setUI(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.rvOrder)
        orders = arguments!!.getSerializable(ORDERS) as List<BookingsList>
        //    var adpater = OrderAdpater(activity, ArrayList(), arguments!!.getInt(STATUS))
        val verticalLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = verticalLayoutManager
        //    recyclerView.adapter = adpater
        recyclerView.adapter = OrderAdpater(activity, orders, arguments!!.getInt(STATUS), this,fragmentManager)

        //  callAPI("0")
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {

        }
    }

    private fun demoData(): List<String> {
        var data: List<String>
        data = ArrayList()
        data.add("All")
        data.add("Mercedes")
        data.add("Ferrari")
        data.add("Maserati")
        data.add("BMW")
        data.add("Etc")
        return data
    }

    fun search(str: String) {
        var subList: List<BookingsList> = ArrayList<BookingsList>()
        subList = if (str.isEmpty()) {
            this.orders!!
        } else {
            searchList(str)
        }
        recyclerView.adapter = OrderAdpater(activity, subList, arguments!!.getInt(STATUS),
                this,fragmentManager)
    }

    fun searchList(str: String): List<BookingsList> {
        var subList = ArrayList<BookingsList>()
        orders!!.let {
            for (item in it) {
                if (item.vehicleTitle.toLowerCase().startsWith(str)) {
                    subList.add(item)
                }
            }
        }

        return subList

    }
}

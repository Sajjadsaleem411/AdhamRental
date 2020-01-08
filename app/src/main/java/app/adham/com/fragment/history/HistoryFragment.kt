package app.adham.com.fragment.history

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
import app.adham.com.AdhamApp.mPreference
import app.adham.com.R
import app.adham.com.activities.orders.ViewPagerAdapter
import app.adham.com.data.model.booking.BookingResponse
import app.adham.com.data.remote.ApiHelper
import app.adham.com.fragment.home.EconomyCarAdpater
import app.adham.com.utils.CommonUtils
import app.adham.com.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_orders.*
import retrofit2.Call
import retrofit2.Response


class HistoryFragment : Fragment(), View.OnClickListener {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        setUI(view)

        return view
    }

    private fun setUI(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.rvHistory)
        //   var EconomyCarAdpater = HistoryAdpater(activity, demoData())
        val verticalLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = verticalLayoutManager
        callAPI(mPreference.getValues(mPreference.emaiId))
    }

    override fun onClick(view: View) {
        /*  val id = view.id
          when (id) {

          }*/
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

    fun callAPI(email: String) {

        if (NetworkUtils.isNetworkConnected(activity)) {
            val dialog = CommonUtils.showLoadingDialog(activity)

            val apiService =
                    AdhamApp.retrofit.create(ApiHelper::class.java)
            val call = apiService.getHistory(email)
            call.enqueue(object : retrofit2.Callback<BookingResponse> {
                override fun onResponse(call: Call<BookingResponse>, response: Response<BookingResponse>) {
                    var data = response.body()
                    if (data?.status == 1) {
                        recyclerView.adapter = HistoryAdpater(context, data.bookingsList,fragmentManager)


                    } else {
                        Toast.makeText(activity, data!!.description, Toast.LENGTH_SHORT).show()

                    }
                    dialog.dismiss()
                    // ShowDirecton(data!!)
                }

                override fun onFailure(call: Call<BookingResponse>, t: Throwable) {
                    Toast.makeText(activity, resources.getString(R.string.internetError), Toast.LENGTH_SHORT).show()

                    dialog.dismiss()
                    // Log error here since request failed
                    Log.e("data", t.toString())
                }
            })
        }
    }


}

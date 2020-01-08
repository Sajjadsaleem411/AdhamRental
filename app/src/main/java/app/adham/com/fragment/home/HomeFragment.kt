package app.adham.com.fragment.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.adham.com.R
import kotlinx.android.synthetic.main.fragment_home.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import app.adham.com.AdhamApp
import app.adham.com.callback.fragmentCallback
import app.adham.com.data.model.car.CarResponse
import app.adham.com.data.model.car.CarsList
import app.adham.com.data.remote.ApiHelper
import app.adham.com.utils.CommonUtils
import app.adham.com.utils.NetworkUtils
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.schedule


class HomeFragment : Fragment(), View.OnClickListener {

    lateinit var mData: List<CarsList>
    var brandID = ""
    var adViews = listOf("Welcome to ADHAM Co", "Economy car Offer")
   // val adContent = listOf("We are a Luxury cars rental Company serving VIP customers", "This Economy offer only valid for some time")
    var bannerIndex = -1
    var run = true

    companion object {
        fun newInstance(brandID: String): HomeFragment {
            val fragment = HomeFragment()
            fragment.brandID = brandID
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        adViews= listOf(resources.getString(R.string.slide1), resources.getString(R.string.slide2))

        setUI(view)
        if (brandID.isEmpty()) {
            brandID = "2"
        }
        callAPI("0", brandID)
        return view
    }

    private fun setUI(view: View) {

        var rvEconomyCar = view.findViewById<RecyclerView>(R.id.rvEconomyCar)
        var EconomyCarAdpater = EconomyCarAdpater(activity!!, ArrayList(), brandID)
        val verticalLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvEconomyCar.layoutManager = verticalLayoutManager
        rvEconomyCar.adapter = EconomyCarAdpater

        fixedRateTimer("timer", false, 0, 4000) {
            activity?.runOnUiThread {
           //     if (run) {
                    if (bannerIndex < adViews.size - 1) {
                        bannerIndex++
                    } else {
                        bannerIndex = 0
                    }
                    view.tvHomeBannerTitle.text = adViews[bannerIndex]
               //     view.tvHomeBannerContent.text = adContent[bannerIndex]
               /* } else {
                    this.cancel()
                }*/
            }
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

    override fun onClick(view: View) {
        val id = view.id

    }

    fun callAPI(carID: String, brandID: String) {

        if (NetworkUtils.isNetworkConnected(context)) {
            val dialog = CommonUtils.showLoadingDialog(context!!)

            val apiService =
                    AdhamApp.retrofit.create(ApiHelper::class.java)
            val call = apiService.getCars(carID, brandID)
            call.enqueue(object : retrofit2.Callback<CarResponse> {
                override fun onResponse(call: Call<CarResponse>, response: Response<CarResponse>) {
                    var data = response.body()
                    if (data?.status == 1) {
                        if (rvEconomyCar != null) {
                            mData = data.carsList
                            rvEconomyCar.adapter = EconomyCarAdpater(activity!!, data.carsList, brandID)
                        }
                    }
                    dialog.dismiss()
                    // ShowDirecton(data!!)
                }

                override fun onFailure(call: Call<CarResponse>, t: Throwable) {
                    Toast.makeText(context, context!!.resources.getString(R.string.internetError), Toast.LENGTH_SHORT).show()

                    dialog.dismiss()
                    // Log error here since request failed
                    Log.e("data", t.toString())
                }
            })
        }
    }

    override fun onPause() {
        super.onPause()
        run = false
    }

    fun search(str: String) {
        var subList: List<CarsList> = ArrayList<CarsList>()
        subList = if (str.isEmpty()) {
            mData
        } else {
            searchList(str)
        }
        rvEconomyCar.adapter = EconomyCarAdpater(activity!!, subList, brandID)
    }

    fun searchList(str: String): List<CarsList> {
        var subList = ArrayList<CarsList>()
        if (mData != null)
            for (item in mData) {
                if (item.vehiclesTitle.toLowerCase().startsWith(str)) {
                    subList.add(item)
                }
            }

        return subList

    }

}
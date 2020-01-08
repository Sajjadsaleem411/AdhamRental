package app.adham.com.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import app.adham.com.AdhamApp
import app.adham.com.AdhamApp.mPreference
import app.adham.com.R
import app.adham.com.data.model.ApiResponse
import app.adham.com.data.model.booking.Booking
import app.adham.com.data.remote.ApiHelper
import app.adham.com.fragment.ReceiptDialog
import app.adham.com.fragment.ReceiptDialog.booking
import app.adham.com.utils.CommonUtils
import app.adham.com.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_maps.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class MapsActivity : AppCompatActivity() {

    var airportNames = arrayOf("Paris Orly airport", "Paris Charles De Gaulle", "Nice Airport")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        rdb1.text = airportNames[0]
        rdb2.text = airportNames[1]
        rdb3.text = airportNames[2]
        rlShareLocation.setOnClickListener {
            val rootObject = JSONObject()
            rootObject.put("customerName", booking.customerName)
            rootObject.put("customerNumber", booking.customerNumber)
            rootObject.put("brandId", booking.brandId)
            rootObject.put("totalPrice", booking.totalPrice)
            rootObject.put("carPrice", booking.price)
            rootObject.put("carImage", booking.carImage)
            rootObject.put("address", etOtherLocation.text)
            rootObject.put("airport", getAirport())
            rootObject.put("isDriver", booking.isDriver)
            rootObject.put("isBodyguard", booking.isBodyGuard)
            booking.message = rootObject.toString()
            callAPI(ReceiptDialog.booking)
        }

        tvMapAirport.setOnClickListener {

            tvMapAirport.setTextColor(ContextCompat.getColor(this, R.color.white))
            tvMapCurrent.setTextColor(ContextCompat.getColor(this, R.color.lineBlue))

            tvMapAirport.setBackgroundResource(R.drawable.bg_blue_left_corner)
            tvMapCurrent.setBackgroundResource(R.drawable.border_background_blue_right)
            rlOtherLocation.visibility = View.GONE
            rlAirport.visibility = View.VISIBLE
        }

        tvMapCurrent.setOnClickListener {

            tvMapCurrent.setTextColor(ContextCompat.getColor(this, R.color.white))
            tvMapAirport.setTextColor(ContextCompat.getColor(this, R.color.lineBlue))

            tvMapCurrent.setBackgroundResource(R.drawable.bg_blue_right_corner)
            tvMapAirport.setBackgroundResource(R.drawable.border_background_blue_left)
            rlAirport.visibility = View.GONE
            rlOtherLocation.visibility = View.VISIBLE
        }
    }


    fun callAPI(info: Booking) {
        if (NetworkUtils.isNetworkConnected(this)) {
            val dialog = CommonUtils.showLoadingDialog(this)
            val apiService =
                    AdhamApp.retrofit.create(ApiHelper::class.java)
            val call = apiService.booking(info)
            call.enqueue(object : retrofit2.Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    var data = response.body()
                    if (data?.status == 1) {
                        openDialog(this@MapsActivity, resources.getString(R.string.orderPost))
                       /* val intent = Intent(this@MapsActivity, MainActivity::class.java)
                        intent.putExtra("BrandID", getIntent().getStringExtra("BrandID"))
                        startActivity(intent)
                        finish()*/
                    }
                    dialog.dismiss()
                    // ShowDirecton(data!!)
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(this@MapsActivity, resources.getString(R.string.internetError), Toast.LENGTH_SHORT).show()

                    dialog.dismiss()
                    // Log error here since request failed
                }
            })
        }
    }
  /*  private fun changeTab(isCurrent:Boolean,isArbic:Boolean){
        if()
    }*/
    fun getAirport(): String {
        if (rdb1.isChecked) {
            return airportNames[0]
        } else if (rdb2.isChecked) {
            return airportNames[1]
        } else if (rdb3.isChecked) {
            return airportNames[2]
        }
        return ""
    }

    fun openDialog(context: Context?,msg:String) {
        val dialog = Dialog(context!!) // Context, this, etc.
        dialog.setContentView(R.layout.dialog)
        val textView = dialog.findViewById(R.id.tv_dialog_msg) as TextView
        textView.text = msg
        //dialog.setTitle(msg);
        val button = dialog.findViewById(R.id.btn_ok) as Button
        button.setOnClickListener {
            val intent = Intent(this@MapsActivity, MainActivity::class.java)
            intent.putExtra("BrandID", getIntent().getStringExtra("BrandID"))
            startActivity(intent)
            finish()
        }
        dialog.setCancelable(false)
        dialog.show()
    }
}

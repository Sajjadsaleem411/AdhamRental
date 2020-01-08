package app.adham.com.activities.carDetail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import app.adham.com.AdhamApp
import app.adham.com.AdhamApp.mPreference
import app.adham.com.R
import app.adham.com.activities.MainActivity
import app.adham.com.data.model.booking.Booking
import app.adham.com.data.model.car.CarResponse
import app.adham.com.data.model.car.CarsList
import app.adham.com.data.remote.ApiHelper
import app.adham.com.utils.CommonUtils
import app.adham.com.fragment.ReceiptDialog
import app.adham.com.utils.AppConstants.currency
import app.adham.com.utils.NetworkUtils
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_car_detail.*
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CarDetailActivity : AppCompatActivity(), SwitchDateTimeDialogFragment.OnButtonWithNeutralClickListener {
    private val TAG = "Sample"
    private var booking: Booking? = null

    private val TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT"

    private val STATE_TEXTVIEW = "STATE_TEXTVIEW"
    private var textView: TextView? = null
    private var dateDialogIndex = 0
    private var carsList:CarsList? = null
    private var toDate:String=""
    private var fromDate:String=""

    private var dateTimeFragment: SwitchDateTimeDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)
        setUI()
    }

    fun setUI() {
        toDate=CommonUtils.getPostDate()
        fromDate=CommonUtils.getPostDate()
        booking= Booking()
        var brandID = intent.getStringExtra("BrandID")
        var carID = intent.getStringExtra("CarID")
        if (brandID != null && carID != null)
            callAPI( intent.getStringExtra("CarID"),intent.getStringExtra("BrandID"))

        rlBtnReceipt.setOnClickListener {
           // if(toDate.equals(CommonUtils.getCurrentDate())) {
                booking?.let {
                    it.toDate = toDate
                    it.fromDate = fromDate
                    /*if(mPreference.getIntValues(mPreference.language)!=1) {
                        it.toDate = toDate
                        it.fromDate = fromDate
                    }
                    else{
                        it.toDate = fromDate
                        it.fromDate = toDate

                    }*/
                    it.postingDate = CommonUtils.getPostDate()
                    it.isDriver = spinnerDriver.selectedItem.toString()
                    it.isBodyGuard = spinnerBodyguard.selectedItem.toString()
                    it.price = carsList?.pricePerDay
                    it.userEmail = AdhamApp.mPreference.getValues(AdhamApp.mPreference.emaiId)
                    it.status = "0"
                    it.bookingID = 0
                    it.carImage=carsList!!.carImages[0].imagePath!!
                    it.carName=carsList!!.vehiclesTitle
                    it.vehicleID = carID
                    it.customerName=AdhamApp.mPreference.getValues(AdhamApp.mPreference.fullName)
                    it.customerNumber=AdhamApp.mPreference.getValues(AdhamApp.mPreference.contactNo)

                }

                ReceiptDialog.newInstance(carsList, intent.getStringExtra("BrandID"), booking)
                        .show(supportFragmentManager)
          /*  }else{
                Toast.makeText(this@CarDetailActivity, this@CarDetailActivity!!.
                        resources.getString(R.string.dataFieldsEmpty), Toast.LENGTH_SHORT).show()

            }*/

            //    startActivity(Intent(this@CarDetailActivity, ReceiptActivity::class.java))
        }
        getDateForUI()
        // Initialize
        val dateTimeDialogFragment = SwitchDateTimeDialogFragment.newInstance(
                resources.getString(R.string.selectBookingDate),
                resources.getString(R.string.ok),
                resources.getString(R.string.cancel)
        )


        val dateTimeDialogFragment2 = SwitchDateTimeDialogFragment.newInstance(
                resources.getString(R.string.selectBookingDate),
                resources.getString(R.string.ok),
                resources.getString(R.string.cancel)
        )
        // Assign values
        dateTimeDialogFragment.startAtCalendarView()
        dateTimeDialogFragment2.startAtCalendarView()
        // dateTimeDialogFragment.set24HoursMode(true)
        var currentDate = CommonUtils.getCurrentDate()
        var date = currentDate.split("-")
     /*   val year = date[0].toIntOrNull() as Int
        val month = date[1].toIntOrNull() as Int
        val day = date[2].toIntOrNull() as Int
        val hours = date[3].toIntOrNull() as Int
        val minutes = date[4].toIntOrNull() as Int*/

        dateTimeDialogFragment.minimumDateTime = GregorianCalendar(2017, Calendar.DECEMBER, 31).time
        dateTimeDialogFragment.maximumDateTime = GregorianCalendar(2025, Calendar.DECEMBER, 31).time

// Define new day and month format
        try {
            dateTimeDialogFragment.simpleDateMonthAndDayFormat = SimpleDateFormat("d MMM yyyy HH:mm", Locale.getDefault())
        } catch (e: SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException) {
            Log.e("Error", e.message)
        }
        //   val myDateFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())

        // Set listener for date
        // Or use dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
        dateTimeDialogFragment?.setOnButtonClickListener(this)
        dateTimeDialogFragment2?.setOnButtonClickListener(this)

        llDateFrom.setOnClickListener {
            dateDialogIndex = 1
            dateTimeDialogFragment?.let {
                it.startAtCalendarView()
                //       it.setDefaultDateTime(GregorianCalendar(year, month, day, hours, minutes).time)
                it.show(supportFragmentManager, TAG_DATETIME_FRAGMENT)
            }


        }

        llDateTo.setOnClickListener {
            dateDialogIndex = 2
            dateTimeDialogFragment2?.let {
                it.startAtCalendarView()
                //      it.setDefaultDateTime(GregorianCalendar(year, month, day, hours, minutes).time)
                it.show(supportFragmentManager, TAG_DATETIME_FRAGMENT)
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

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
      /*  val intent= Intent(this, MainActivity::class.java)
        intent.putExtra("BrandID",intent.getStringExtra("BrandID"))
       startActivity(intent)
       finish()*/
    }

    override fun onPositiveButtonClick(p0: Date?) {
        /*   Toast.makeText(this@CarDetailActivity,
                   myDateFormat.format(date), Toast.LENGTH_SHORT).show()*/
        var currentDate = CommonUtils.getDate(p0)
        var date = currentDate.split("-")
        val month = date[1]
        val dayName = date[2]
        val day = date[3].toIntOrNull() as Int
        val time = date[4]

        if (dateDialogIndex == 1) {
            fromDate=CommonUtils.changePostDate(p0)
            tvDateFromDay.text = "$day"
            tvDateFromDayMonth.text = "$dayName $month"
            tvDateFromTime.text = "${resources.getString(R.string.time)}: $time"
        } else if (dateDialogIndex == 2) {
            toDate=CommonUtils.changePostDate(p0)
            tvDateToDay.text = "$day"
            tvDateToDayMonth.text = "$dayName $month"
            tvDateToTime.text = "${resources.getString(R.string.time)}: $time"
        }
    }

    override fun onNeutralButtonClick(p0: Date?) {
    }

    override fun onNegativeButtonClick(p0: Date?) {
    }

    fun getDateForUI() {
        var currentDate = CommonUtils.getDate()
        var date = currentDate.split("-")
        val month = date[1]
        val dayName = date[2]
        val day = date[3].toIntOrNull() as Int
        val time = date[4]

        tvDateFromDay.text = "$day"
        tvDateFromDayMonth.text = "$dayName $month"
        tvDateFromTime.text = "${resources.getString(R.string.time)}: $time"
        tvDateToDay.text = "$day"
        tvDateToDayMonth.text = "$dayName $month"
        tvDateToTime.text = "${resources.getString(R.string.time)}: $time"

    }

    fun callAPI(carID: String, brandID: String) {

        if (NetworkUtils.isNetworkConnected(this)) {
            val dialog = CommonUtils.showLoadingDialog(this)

            val apiService =
                    AdhamApp.retrofit.create(ApiHelper::class.java)
            val call = apiService.getCars(carID, brandID)
            call.enqueue(object : retrofit2.Callback<CarResponse> {
                override fun onResponse(call: Call<CarResponse>, response: Response<CarResponse>) {
                    var data = response.body()
                    if (data?.status == 1) {
                        if (data.carsList.size > 0) {
                            carsList = data.carsList[0]
                            carsList?.let {
                                tvCarDetailName.text = it.vehiclesTitle
                                tvCarDetailDesc.text = it.vehicleOverview
                                tvCarDetailPrice.text = currency+"${it.pricePerDay} " + resources.getString(R.string.perDay)
                                var adapter = ImageAdpater(this@CarDetailActivity, carsList?.carImages)
                                val horizontalLayoutManager1 = LinearLayoutManager(this@CarDetailActivity, LinearLayoutManager.HORIZONTAL, false)
                                rvImage.layoutManager = horizontalLayoutManager1
                                rvImage.adapter = adapter
                                Picasso.get().load(carsList?.carImages?.get(0)?.imagePath)
                                        .into(toolbarImage)

                            }
                        }
                    }
                    dialog.dismiss()
                    // ShowDirecton(data!!)
                }

                override fun onFailure(call: Call<CarResponse>, t: Throwable) {
                    Toast.makeText(this@CarDetailActivity, this@CarDetailActivity.resources.getString(R.string.internetError), Toast.LENGTH_SHORT).show()

                    dialog.dismiss()
                    // Log error here since request failed
                    Log.e("data", t.toString())
                }
            })
        }
    }

}

package app.adham.com.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.Toast
import app.adham.com.AdhamApp
import app.adham.com.AdhamApp.mPreference
import app.adham.com.R
import app.adham.com.data.model.brand.BrandResponse
import app.adham.com.data.model.brand.BrandsList
import app.adham.com.data.remote.ApiHelper
import app.adham.com.utils.CommonUtils
import app.adham.com.utils.NetworkUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_car_type.*
import kotlinx.android.synthetic.main.item_cartype.view.*
import retrofit2.Call
import retrofit2.Response

class CarTypeActivity : AppCompatActivity(), View.OnClickListener {

    var adapter: CarTypeAdapter? = null
    lateinit var data: ArrayList<BrandsList>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_type)
        setUI()
    }

    private fun setUI() {
        callAPI()
        ivCarTypeToolbarArrow.setOnClickListener {
            if(mPreference.getBoolean(mPreference.isLogin)){
                this.finishAffinity()
            }
            else{
                LoginActivity.newInstance(this)
            }
        }

    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.ivCarTypeToolbarArrow -> {
                finish()
            }
        }
    }

    class CarTypeAdapter : BaseAdapter {
        var data = ArrayList<BrandsList>()
        var context: Activity? = null
        internal var height: Int = 0
        internal var width: Int = 0

        constructor(context: Activity, foodsBrandsList: ArrayList<BrandsList>) : super() {
            this.context = context
            this.data = foodsBrandsList
            var displayMetrics: DisplayMetrics


            displayMetrics = context.resources.displayMetrics
            height = displayMetrics.heightPixels
            width = displayMetrics.widthPixels
        }

        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(position: Int): Any {
            return data[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val data = this.data[position]
            val cellWidth = width * 0.46
            val cellHeight = height * 0.2
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var view = inflator.inflate(R.layout.item_cartype, null)
            view.layoutParams = LinearLayout.LayoutParams(cellWidth.toInt(), cellHeight.toInt())
            /*   foodView.imgFood.setImageResource(food.image!!)
               foodView.tvName.text = food.name!!*/
            view.tvCarTypeName.text = data.brandName!!
            Picasso.get().load(data.image).into(view.ivCarTypeLogo)
            view.setOnClickListener {
              /*  var bundle=Bundle()
                bundle.putString("BrandID",data.id.toString())*/
                val intent=Intent(context, MainActivity::class.java)
                intent.putExtra("BrandID",data.id.toString())
                context!!.startActivity(intent)
                context!!.finish()
            }

            return view
        }
    }

    fun callAPI() {

        if (NetworkUtils.isNetworkConnected(this)) {
            val dialog = CommonUtils.showLoadingDialog(this)

            val apiService =
                    AdhamApp.retrofit.create(ApiHelper::class.java)
            val call = apiService.allBrands
            call.enqueue(object : retrofit2.Callback<BrandResponse> {
                override fun onResponse(call: Call<BrandResponse>, response: Response<BrandResponse>) {
                    var result = response.body()
                    data = result?.list!!

                    adapter = CarTypeAdapter(this@CarTypeActivity, data)

                    gvCarType.adapter = adapter
                    dialog.dismiss()
                    // ShowDirecton(data!!)
                }

                override fun onFailure(call: Call<BrandResponse>, t: Throwable) {
                    Toast.makeText(this@CarTypeActivity,
                            this@CarTypeActivity.resources.getString(R.string.internetError), Toast.LENGTH_SHORT).show()

                    dialog.dismiss()
                    // Log error here since request failed
                    Log.e("data", t.toString())
                }
            })
        }
    }

   /* override fun onBackPressed() {
        super.onBackPressed()
        if(!AdhamApp.mPreference.getBoolean(AdhamApp.mPreference.isLogin)){
            LoginActivity.newInstance(this)
        }
    }*/
}

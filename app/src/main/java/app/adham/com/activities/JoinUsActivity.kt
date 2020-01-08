package app.adham.com.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import app.adham.com.AdhamApp

import app.adham.com.R
import app.adham.com.activities.orders.OrdersActivity
import app.adham.com.data.model.ApiResponse
import app.adham.com.data.model.JoinInfo
import app.adham.com.data.model.login.LoginInfo
import app.adham.com.data.remote.ApiHelper
import app.adham.com.utils.CommonUtils
import app.adham.com.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_join_us.*
import kotlinx.android.synthetic.main.activity_orders.*
import kotlinx.android.synthetic.main.fragment_signup.view.*
import kotlinx.android.synthetic.main.toolbar_main.view.*
import retrofit2.Call
import retrofit2.Response

class JoinUsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_us)
        setUI()
    }

    private fun setUI() {
        toolbarJoinUs.tvToolbarTitle.text = resources.getString(R.string.joinUs)
        toolbarJoinUs.ivToolbarArrow.setOnClickListener(this)
        rlBtnJoin.setOnClickListener(this)
        ivToolbarSearch.visibility=View.GONE
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.ivToolbarArrow -> {
                finish()
            }
            R.id.rlBtnJoin -> {
                val email = etJoinEmail.text.toString()
                val name = etJoinName.text.toString()
                val number = etJoinContactNo.text.toString()
                val license = etJoinLicenseNo.text.toString()
                val join = spinnerJoinUs.selectedItem.toString()
                if (!email.isEmpty() && !license.isEmpty()
                        && !number.isEmpty() && !name.isEmpty()) {
                    if (email.isValidEmail()) {

                        var info = JoinInfo()
                        info.fullName = name
                        info.emaiID = email
                        info.phoneNo = number
                        info.licenseNo = license
                        info.joinAs = join
                        callAPI(info)

                    } else {
                        Toast.makeText(this, this.resources.getString(R.string.emailInvalid), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, this.resources.getString(R.string.dataFieldsEmpty), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun callAPI(info: JoinInfo) {
        if (NetworkUtils.isNetworkConnected(this)) {
            val dialog = CommonUtils.showLoadingDialog(this)
            val apiService =
                    AdhamApp.retrofit.create(ApiHelper::class.java)
            val call = apiService.JoinNow(info)
            call.enqueue(object : retrofit2.Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    var data = response.body()
                    if (data?.status == 1)
                        startActivity(Intent(this@JoinUsActivity, LoginActivity::class.java))
                    else{
                        Toast.makeText(this@JoinUsActivity, this@JoinUsActivity.resources.getString(R.string.invalidData), Toast.LENGTH_SHORT).show()

                    }
                    dialog.dismiss()
                    // ShowDirecton(data!!)
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(this@JoinUsActivity, this@JoinUsActivity.resources.getString(R.string.internetError), Toast.LENGTH_SHORT).show()

                    dialog.dismiss()
                    // Log error here since request failed
                    Log.e("data", t.toString())
                }
            })
        }
    }

    fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

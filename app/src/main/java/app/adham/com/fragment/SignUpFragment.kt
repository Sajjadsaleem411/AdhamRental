package app.adham.com.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import app.adham.com.AdhamApp
import app.adham.com.R
import app.adham.com.activities.CarTypeActivity
import app.adham.com.activities.LoginActivity
import app.adham.com.data.model.ApiResponse
import app.adham.com.data.model.login.LoginInfo
import app.adham.com.data.remote.ApiHelper
import app.adham.com.utils.CommonUtils
import app.adham.com.utils.NetworkUtils
import kotlinx.android.synthetic.main.fragment_signin.view.*
import kotlinx.android.synthetic.main.fragment_signup.view.*
import retrofit2.Call
import retrofit2.Response


class SignUpFragment : Fragment(), View.OnClickListener {

    internal lateinit var view: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        setUI(view)

        return view
    }

    private fun setUI(view: View) {
        this.view = view
        view.findViewById<RelativeLayout>(R.id.rlBtnSignUp).setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val email = this.view.etSignUpEmail.text.toString()
        val password = this.view.etSignUpPassword.text.toString()
        val name = this.view.etSignUpName.text.toString()
        val number = this.view.etSignUpNumber.text.toString()
        val id = view.id
        when (id) {
            R.id.rlBtnSignUp -> {
                if (!email.isEmpty() && !password.isEmpty()
                        && !number.isEmpty() && !name.isEmpty()) {
                    if (email.isValidEmail()) {
                        if (password.length >= 6) {
                            var info = LoginInfo()
                            info.fullName = name
                            info.emaiId = email
                            info.password = password
                            info.contactNo = number
                            callAPI(info)
                        } else {
                            Toast.makeText(context, context!!.resources.getString(R.string.passwordInvalid), Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, context!!.resources.getString(R.string.emailInvalid), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, context!!.resources.getString(R.string.dataFieldsEmpty), Toast.LENGTH_SHORT).show()
                }
              //  startActivity(Intent(activity, CarTypeActivity::class.java))
            }
        }
    }

    fun callAPI(info: LoginInfo) {
     //   if (NetworkUtils.isNetworkConnected(context)) {
            val dialog = CommonUtils.showLoadingDialog(context!!)
            val apiService =
                    AdhamApp.retrofit.create(ApiHelper::class.java)
            val call = apiService.SignUp(info)
            call.enqueue(object : retrofit2.Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    var data = response.body()
                    if (data?.status == 1)
                        startActivity(Intent(activity, LoginActivity::class.java))
                    else {
                        Toast.makeText(context, context!!.resources.getString(R.string.invalidData), Toast.LENGTH_SHORT).show()

                    }
                    dialog.dismiss()
                    // ShowDirecton(data!!)
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()

            //        Toast.makeText(context, context!!.resources.getString(R.string.internetError), Toast.LENGTH_SHORT).show()

                    dialog.dismiss()
                    // Log error here since request failed
                    Log.e("data", t.toString())
                }
            })
      //  }
    }

    fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

package app.adham.com.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import app.adham.com.AdhamApp
import app.adham.com.AdhamApp.mPreference
import app.adham.com.R
import app.adham.com.activities.CarTypeActivity
import app.adham.com.activities.JoinUsActivity
import app.adham.com.activities.LoginActivity
import app.adham.com.activities.MainActivity
import app.adham.com.activities.orders.OrdersActivity
import app.adham.com.data.model.login.LoginInfo
import app.adham.com.data.model.login.LoginResponse
import app.adham.com.data.remote.ApiHelper
import app.adham.com.utils.CommonUtils
import app.adham.com.utils.NetworkUtils
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_signin.view.*
import retrofit2.Call
import retrofit2.Response
import java.util.*


class SignInFragment : Fragment(), View.OnClickListener {

    internal lateinit var view: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signin, container, false)

        setUI(view)
        this.view = view
        return view
    }

    private fun setUI(view: View) {
        /*  if (Locale.getDefault().displayLanguage == "English")
              setLocale("ar")*/
        view.tvSigInSkip.setOnClickListener {
            startActivity(Intent(activity, CarTypeActivity::class.java))
        }
        view.findViewById<RelativeLayout>(R.id.rlBtnSignIn).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tvSignInJoinUs).setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val id = view.id
        val email = this.view.etSignInEmail.text.toString()
        val password = this.view.etSignInPassword.text.toString()
        when (id) {
            R.id.rlBtnSignIn -> {

                if (email.isValidEmail()) {
                    if (!password.isEmpty()) {
                        callAPI(email, password)
                    } else {
                        Toast.makeText(context, context!!.resources.getString(R.string.passwordInvalid), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, context!!.resources.getString(R.string.emailInvalid), Toast.LENGTH_SHORT).show()
                }
            }
            R.id.tvSignInJoinUs -> {
                startActivity(Intent(activity, JoinUsActivity::class.java))
            }
        }
    }

    fun callAPI(email: String, password: String) {

        //   if (NetworkUtils.isNetworkConnected(context)) {
        val dialog = CommonUtils.showLoadingDialog(context!!)

        val apiService =
                AdhamApp.retrofit.create(ApiHelper::class.java)
        val call = apiService.Login(email, password)
        call.enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                var data = response.body()
                if (data?.status == 1) {
                    var loginInfo = data.loginInfo
                    mPreference.sessionMethod(mPreference.isLogin, true)
                    mPreference.sessionMethod(mPreference.userID, loginInfo.userID)
                    mPreference.sessionMethod(mPreference.fullName, loginInfo.fullName)
                    mPreference.sessionMethod(mPreference.emaiId, loginInfo.emaiId)
                    mPreference.sessionMethod(mPreference.contactNo, loginInfo.contactNo)
                    mPreference.sessionMethod(mPreference.isAdmin, loginInfo.isAdmin)
                    if (data.loginInfo.isAdmin) {
                        var intent = Intent(activity, OrdersActivity::class.java)

                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        activity?.startActivity(intent)

                    } else
                        startActivity(Intent(activity, CarTypeActivity::class.java))
                    activity?.finish()
                } else {
                    Toast.makeText(context, context!!.resources.getString(R.string.passwordOrEmailInvalid), Toast.LENGTH_SHORT).show()
                    //  Toast.makeText(context, context!!.resources.getString(R.string.invalidData), Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
                // ShowDirecton(data!!)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()

//                    Toast.makeText(context, context!!.resources.getString(R.string.internetError), Toast.LENGTH_SHORT).show()

                dialog.dismiss()
                // Log error here since request failed
                Log.e("data", t.toString())
            }
        })
        //  }
    }

    fun setLocale(lang: String) {
        var myLocale = Locale(lang)
        var res = getResources();
        var dm = res.getDisplayMetrics();
        var conf = res.getConfiguration();
        conf.locale = myLocale;
        conf.setLocale(Locale(lang.toLowerCase())) // API 17+ only.
        res.updateConfiguration(conf, dm)
        startActivity(Intent(activity, LoginActivity::class.java))
        activity!!.finish()
    }

    fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()

}

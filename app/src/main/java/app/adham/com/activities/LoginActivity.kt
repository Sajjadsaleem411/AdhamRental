package app.adham.com.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import app.adham.com.AdhamApp.mPreference

import app.adham.com.R
import app.adham.com.activities.orders.OrdersActivity
import app.adham.com.fragment.SignInFragment
import app.adham.com.fragment.SignUpFragment
import app.adham.com.utils.CommonUtils
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import java.util.*
import java.util.Arrays.asList


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    var initialDisplay = true

    companion object {

        fun newInstance(activity: Activity) {
            var intent = Intent(activity, LoginActivity::class.java)
            //     intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("Splash", false)
            activity.startActivity(intent)

            activity.finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* if (intent.extras==null) {
             Thread.sleep(2000)
         }*/
        Thread.sleep(2000)
        setTheme(R.style.AppTheme)
        if (mPreference.getBoolean(mPreference.isLogin)) {
            if (mPreference.getBoolean(mPreference.isAdmin))
                startActivity(Intent(this, OrdersActivity::class.java))
            else
                startActivity(Intent(this, CarTypeActivity::class.java))

            finish()

        }
        /*   val index=mPreference.getIntValues(mPreference.language)
           val baths = resources.getStringArray(R.array.languageArray)
           spLanguage.setSelection(Arrays.asList(baths).indexOf(index))*/
        setContentView(R.layout.activity_login)
        val fragment = SignInFragment()
        initializeFragment(fragment, R.id.login_container)
        setUI()
        //     var dialog = CommonUtils.showLoadingDialog(this!!)

    }

    private fun setUI() {
        rlSignIn.setOnClickListener(this)
        rlSignUp.setOnClickListener(this)

        val index = mPreference.getIntValues(mPreference.language)// your code here
        // your code here
        when (index) {
            0 -> ivLoginLanguage.setImageResource(R.drawable.ic_english)
            1 -> ivLoginLanguage.setImageResource(R.drawable.ic_arabic)
            2 -> ivLoginLanguage.setImageResource(R.drawable.ic_french)
        }

        spLanguage.setSelection(index)
        spLanguage.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                // your code here
                if (!initialDisplay)
                    setLocale(CommonUtils.getLangaugeName(position))
                else
                    initialDisplay = false
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        }

    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.rlSignIn -> {
                tvSignIn.setTextColor(ContextCompat.getColor(this, R.color.textColor))
                tvSignUp.setTextColor(ContextCompat.getColor(this, R.color.lightTextColor))
                vSignIn.visibility = View.VISIBLE
                vSignUp.visibility = View.GONE
                val fragment = SignInFragment()
                initializeFragment(fragment, R.id.login_container)
            }
            R.id.rlSignUp -> {
                tvSignIn.setTextColor(ContextCompat.getColor(this, R.color.lightTextColor))
                tvSignUp.setTextColor(ContextCompat.getColor(this, R.color.textColor))
                vSignIn.visibility = View.GONE
                vSignUp.visibility = View.VISIBLE
                val fragment = SignUpFragment()
                initializeFragment(fragment, R.id.login_container)
            }
        }
    }

    private fun initializeFragment(fragment: Fragment, id: Int) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(id, fragment, "HELLO")
        fragmentTransaction.commit()
    }

    fun setLocale(lang: String) {
        var myLocale = Locale(lang)
        var res = getResources();
        var dm = res.getDisplayMetrics();
        var conf = res.getConfiguration();
        conf.locale = myLocale;
        conf.setLocale(Locale(lang.toLowerCase())) // API 17+ only.
        res.updateConfiguration(conf, dm)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
}

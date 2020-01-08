package app.adham.com.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.adham.com.R
import kotlinx.android.synthetic.main.fragment_more.view.*
import android.content.Intent
import app.adham.com.AdhamApp
import app.adham.com.AdhamApp.mPreference
import app.adham.com.activities.LoginActivity
import app.adham.com.utils.CommonUtils
import java.util.*
import android.app.AlertDialog
import android.app.Dialog
import android.view.Window
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import app.adham.com.activities.AboutActivity
import app.adham.com.activities.ContactUsActivity
import app.adham.com.activities.SupportActivity


class MoreFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_more, container, false)

        setUI(view)

        return view
    }

    private fun setUI(view: View) {
        view.tvMoreLanguage.text = Locale.getDefault().displayLanguage
        if (AdhamApp.mPreference.getBoolean(mPreference.isLogin)) {
            view.tvMoreName.text = AdhamApp.mPreference.getValues(mPreference.fullName)
            view.tvMoreEmail.text = AdhamApp.mPreference.getValues(mPreference.emaiId)
        } else {
            view.rl_top.visibility = View.GONE
        }
        view.rlLanguage.setOnClickListener {
            showDialog()
            /*val b = AlertDialog.Builder(context)
            b.setTitle(resources.getString(R.string.language))

           var array: Array<String>? = null

            array = resources.getStringArray(R.array.languageArray)
            val types = arrayOf(array[0], array[1],array[2])
            b.setItems(types) { dialog, which ->
                dialog.dismiss()
               setLocale(CommonUtils.getLangaugeName(which))
            }

            b.show()*/
          /*  val languageIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(languageIntent)*/
        }
        view.btnLogout.setOnClickListener {
            CommonUtils.showLoadingDialog(context!!)
            AdhamApp.mPreference.sessionMethod(AdhamApp.mPreference.isLogin, false)

            /* var intent=Intent(activity,LoginActivity::class.java)
             intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
             startActivity(intent)

             startActivity(Intent(activity,LoginActivity::class.java))
             activity?.finish()*/
            LoginActivity.newInstance(activity!!)
        }
        view.llMoreContactUs.setOnClickListener {
            startActivity(Intent(activity,ContactUsActivity::class.java))
        }
        view.llMoreAboutUs.setOnClickListener {
            startActivity(Intent(activity,AboutActivity::class.java))
        }

        view.llMoreSupport.setOnClickListener {
            startActivity(Intent(activity,SupportActivity::class.java))
        }
    }

    override fun onClick(view: View) {
        /*  val id = view.id
          when (id) {

          }*/
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

    private fun showDialog() {
        var dialogs = Dialog(activity)
        dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogs.setCancelable(true)
        dialogs.setContentView(R.layout.dailog_language)
        dialogs.findViewById<RelativeLayout>(R.id.rlEnglish).setOnClickListener {
            setLocale(CommonUtils.getLangaugeName(0))
        }
        dialogs.findViewById<RelativeLayout>(R.id.rlArabic).setOnClickListener {
            setLocale(CommonUtils.getLangaugeName(1))
        }
        dialogs.findViewById<RelativeLayout>(R.id.rlFrench).setOnClickListener {
            setLocale(CommonUtils.getLangaugeName(2))
        }
       /* val body = dialogs.findViewById(R.id.body) as TextView
        body.text = title
        val yesBtn = dialogs.findViewById(R.id.yesBtn) as Button
        val noBtn = dialogs.findViewById(R.id.noBtn) as TextView
        yesBtn.setOnClickListener {
            dialogs.dismiss()
        }
        noBtn.setOnClickListener { dialogs.dismiss() }*/
        dialogs.show()

    }


}

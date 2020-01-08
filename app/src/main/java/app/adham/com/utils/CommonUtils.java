package app.adham.com.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import app.adham.com.R;

import static app.adham.com.AdhamApp.mPreference;

public class CommonUtils {

    public static final String POST_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MMMM-EEE-dd-hh:mm a";
    public static final String CURRENT_DATE_FORMAT = "yyyy-MM-dd-hh-mm";
    //0=pending,1=accept,2=cancel
    public static final String[] orderStatus={"0","1","2"};
    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(CURRENT_DATE_FORMAT, Locale.ENGLISH);
        String strDate = sdf.format(c.getTime());
        Log.d("", "" + strDate);
        return strDate;
    }
    public static String getDate(Date date) {
        String output1="";
        DateFormat outputFormatter1 = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        output1 = outputFormatter1.format(date); //
        return output1;
    }
    public static String getDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        String strDate = sdf.format(c.getTime());
    //    Log.d("", "" + strDate);
        return strDate;
    }
    public static String getPostDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(POST_DATE_FORMAT, Locale.ENGLISH);
        String strDate = sdf.format(c.getTime());
        //    Log.d("", "" + strDate);
        return strDate;
    }
    public static String changePostDate(Date date) {
        String output1="";
        DateFormat outputFormatter1 = new SimpleDateFormat(POST_DATE_FORMAT, Locale.ENGLISH);
        output1 = outputFormatter1.format(date); //
        return output1;
    }

    public static ProgressDialog showLoadingDialog(Context context)  {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        Sprite doubleBounce = new DoubleBounce();
        progressDialog.setIndeterminateDrawable(doubleBounce);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
    public static String getLangaugeName(int code){
        switch (code){
            case 0:
                mPreference.sessionMethod(mPreference.language,0);
                return "en";

            case 1:
                mPreference.sessionMethod(mPreference.language,1);
                return "ar";

            case 2:
                mPreference.sessionMethod(mPreference.language,2);
                return "fr";
        }
        return "";
    }
}

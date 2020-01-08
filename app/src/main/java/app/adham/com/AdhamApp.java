package app.adham.com;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import java.util.Locale;

import app.adham.com.data.remote.ApiClient;
import app.adham.com.utils.CommonUtils;
import app.adham.com.utils.SaveInSharedPreference;
import io.fabric.sdk.android.Fabric;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdhamApp extends Application {
    String TAG="Debug";
    public static SaveInSharedPreference mPreference;
    public static Retrofit retrofit;
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        retrofit= ApiClient.getClient();
        mPreference=new SaveInSharedPreference(this);
        mPreference.setDefault(mPreference);
        int value=mPreference.getIntValues(mPreference.language);
        setLocale(CommonUtils.getLangaugeName(value));
    /*    Fabric.with(this, new Crashlytics());
        FirebaseApp.initializeApp(this);
        FirebaseInstanceId.getInstance().getToken();
        FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
        mPreference.sessionMethod(mPreference.deviceId,FirebaseInstanceId.getInstance().getToken());
        Log.d(TAG, "Firebase reg id: " + mPreference.getValues(mPreference.deviceId));
        //       displayFirebaseRegId();*/
    }
 /*   private void displayFirebaseRegId() {


        SharedPreferences pref = getApplicationContext()
                .getSharedPreferences(Config.SHARED_PREF,  0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

       *//* if (!TextUtils.isEmpty(regId))

            txtRegId.setText("Firebase Reg Id: " + regId);
        else
            txtRegId.setText("Firebase Reg Id is not received yet!");*//*
    }*/

    void setLocale(String lang) {
        if(!lang.isEmpty()) {
            Locale myLocale = new Locale(lang);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            conf.setLocale(new Locale(lang.toLowerCase())); // API 17+ only.
            res.updateConfiguration(conf, dm);
        }
      /*  startActivity(Intent(activity, LoginActivity::class.java))
        activity!!.finish()*/
    }
}

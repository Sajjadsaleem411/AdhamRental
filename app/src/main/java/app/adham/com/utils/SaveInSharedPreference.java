package app.adham.com.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveInSharedPreference {

	static Context context;

	public final String fullName= "FullName";
	public final String emaiId= "EmaiId";
	public final String contactNo= "ContactNo";
	public final String isAdmin= "IsAdmin";
	public final String userID= "UserID";
	public final String isLogin= "isLogin";
	public final String language= "Language";
	public final String alreadyInstall = "alreadyInstall";
	
	public SaveInSharedPreference(Context context){
		this.context = context;
	}
	
	
	//making session method start
	public void sessionMethod(String key, boolean data){
		    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
	 		SharedPreferences.Editor editor = preferences.edit();
	 		editor.putBoolean(key, data);		 		
	 		editor.commit();	 			 		
	}
	//making session method end
	
			//get session method start
			public	 boolean getBoolean(String key){
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
				boolean value = prefs.getBoolean(key, false);			
				return value;
			}
			//get session method end
	
	
		//making session method start
		public void sessionMethod(String key, String data){
			    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		 		SharedPreferences.Editor editor = preferences.edit();
		 		editor.putString(key, data);		 		
		 		editor.commit();	 			 		
		}

	//making session method start
	public void sessionMethod(String key, int data){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(key, data);
		editor.commit();
	}
		//making session method end

		
		//get session method start
		public String getValues(String key){
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
			String value = prefs.getString(key, "");
			return value;
		}

	//get session method start
	public int getIntValues(String key){
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		int value = prefs.getInt(key, 0);
		return value;
	}
		//get session method end

	//get clear key method start
	public static void clearKey(String key){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.remove(key);
		editor.commit();
	}
	public void setDefault(SaveInSharedPreference mPreference) {
		if (!mPreference.getBoolean(alreadyInstall)) {


			mPreference.sessionMethod(alreadyInstall, true);
			mPreference.sessionMethod(language, 0);

		}
	}
	//get clear key method end
}

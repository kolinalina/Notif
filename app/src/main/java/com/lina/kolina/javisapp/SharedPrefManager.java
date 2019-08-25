package com.lina.kolina.javisapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "volleyregisterlogin";
    private static final String KEY_NAMAPERUSAHAAN = "keynamaperusahaan";
    private static final String KEY_ALAMAT = "keyalamat";
    private static final String KEY_NAMACLIENT = "keynamaclient";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_NOHP = "keynohp";
    private static final String KEY_IDCLIENT = "keyidclient";
    private static SharedPrefManager mInstance;
    private static Context ctx;

    private SharedPrefManager(Context context) {
        ctx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_IDCLIENT, user.getId_client());
        editor.putString(KEY_NAMAPERUSAHAAN, user.getNama_perusahaan());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_ALAMAT, user.getAlamat());
        editor.putString(KEY_NAMACLIENT, user.getNama_client());
        editor.putString(KEY_NOHP, user.getNo_hp());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_IDCLIENT, -1),
                sharedPreferences.getString(KEY_NAMAPERUSAHAAN, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_ALAMAT, null),
                sharedPreferences.getString(KEY_NAMACLIENT, null),
                sharedPreferences.getString(KEY_NOHP, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LoginActivity.class));
    }
}
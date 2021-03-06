package com.hngkyt.vr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.google.gson.Gson;
import com.hngkyt.vr.R;
import com.hngkyt.vr.VRApplication;
import com.hngkyt.vr.net.RequestService;
import com.hngkyt.vr.model.User;
import com.hzgktyt.vr.baselibrary.utils.SPUtils;

/**
 * Created by wrf on 2016/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity {


    public static final int REQUEST_CODE_DEFAULT = 1;
    public SPUtils mSPUtils;
    public FragmentManager mFragmentManager;
    public VRApplication mVRApplication;
    public RequestService mRequestService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(intLayoutResId());
        mSPUtils = new SPUtils(this, SPUtils.class.getCanonicalName());
        mVRApplication = (VRApplication) getApplication();
        mRequestService = mVRApplication.mRetrofit.create(RequestService.class);
        mFragmentManager = getSupportFragmentManager();

        initView();
    }


    abstract protected int intLayoutResId();

    abstract protected void initView();

    protected void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (tag == null) {
            transaction.replace(R.id.framelayout_fragment, fragment);
        } else {
            transaction.replace(R.id.framelayout_fragment, fragment, tag);
        }
        transaction.commit();
    }

    public void startActivityOriginal(Context context, Class<?> cls) {
        startActivity(new Intent(context, cls));
    }


    public String getEditTextContent(EditText editText) {
        return editText.getText().toString().trim();
    }


    public User getUserInfo() {
        String userJson = mSPUtils.getString(User.class.getCanonicalName(), "");
        if (TextUtils.isEmpty(userJson)) {
            return null;
        }
        return new Gson().fromJson(userJson, User.class);
    }


    public void saveUserInfo(User user) {
        if (user == null) {
            mSPUtils.putString(User.class.getCanonicalName(), "");
            return;
        }
        String userJson = new Gson().toJson(user);
        mSPUtils.putString(User.class.getCanonicalName(), userJson);
    }

}

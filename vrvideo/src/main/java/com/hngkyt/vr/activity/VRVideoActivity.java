package com.hngkyt.vr.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;

import com.hngkyt.vr.R;
import com.hngkyt.vr.fragment.VRVideoFragment;
import com.hngkyt.vr.model.Video;
import com.orhanobut.logger.Logger;

/**
 * Created by wrf on 2016/11/16.
 */

public class VRVideoActivity extends BaseActivity {
    private PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        cpuAwake();

    }

    @Override
    protected int intLayoutResId() {
        return R.layout.include_framelayout_fragment;
    }

    private void cpuAwake() {
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                getLocalClassName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        wakeLock.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wakeLock.release();
    }

    @Override
    protected void initView() {
        Video video = getIntent().getParcelableExtra(Video.class.getCanonicalName());

        Logger.e("video = " + video);


        //当Activity被系统销毁的时候，回复状态要判断Fragment会不会为空，不然Fragment中onActivityCreated会执行两次
        VRVideoFragment vrVideoFragment = (VRVideoFragment) mFragmentManager.findFragmentByTag(VRVideoFragment.class.getCanonicalName());
        if (vrVideoFragment != null) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            //            Logger.e("添加前 =" + mFragmentManager.getFragments().size());
            //attach、show、replace不清楚有什么区别效果都一样，都能达到恢复状态的效果
            //使用add会报错说已经存在这个fragment了，这是符合逻辑的本来就已经有了
            fragmentTransaction.show(vrVideoFragment);
            //fragmentTransaction.add(vrVideoFragment,VRVideoFragment.class.getCanonicalName());
            //ragmentTransaction.attach(vrVideoFragment);
            //fragmentTransaction.replace(R.id.framelayout_fragment,vrVideoFragment,VRVideoFragment.class.getCanonicalName());
            //           Logger.e("添加后 =" + mFragmentManager.getFragments().size());

            fragmentTransaction.commit();
        } else {
            replaceFragment(VRVideoFragment.newInstance(video), VRVideoFragment.class.getCanonicalName());
        }

        showStatsBar();


    }


    public void hideStatsBar() {
        View decorView = getWindow().getDecorView();

        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void showStatsBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }


}

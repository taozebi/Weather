package com.taoze.weather.ui.common;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.taoze.weather.global.WApplication;

import butterknife.ButterKnife;

/**
 * CommonActivity
 *
 * @version 1.0.0
 * @date 2016-3-2
 */
public abstract class CommonActivity extends BaseActivity {

    /**
     * 总布局.
     */
    protected RelativeLayout baseLayout = null;

    /**
     * 主内容布局.
     */
    protected RelativeLayout contentLayout = null;

    protected LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WApplication) getApplication()).addActivity(this);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);

//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			// 透明状态栏
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			// 透明导航栏
////			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//		}
        mInflater = LayoutInflater.from(this);

        baseLayout = new RelativeLayout(this);

        contentLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams baseParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        setContentView(baseLayout, baseParams);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((WApplication) getApplication()).removeActivity(this);
    }

    /**
     * 返回键按下时回调，如返回false，则会自动调用finish方法。
     *
     * @return
     */
    protected boolean onBack() {
        Log.d(WApplication.TAG, "CommonActivity --> onBack()");
        return false;
    }

    /**
     * 描述：用指定的View填充主界面.
     *
     * @param contentView 指定的View
     */
    @Override
    public void setContentView(View contentView) {
        contentLayout.removeAllViews();
        contentLayout.addView(contentView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //注解控件
        ButterKnife.bind(this);
        onInitData();
    }

    /**
     * 描述：用指定资源ID表示的View填充主界面.
     *
     * @param resId 指定的View的资源ID
     */
    @Override
    public void setContentView(int resId) {
        setContentView(mInflater.inflate(resId, null));
    }

    /**
     * 初始化数据
     */
    public abstract void onInitData();
}


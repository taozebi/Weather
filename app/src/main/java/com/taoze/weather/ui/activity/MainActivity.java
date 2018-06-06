package com.taoze.weather.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.taoze.weather.R;
import com.taoze.weather.global.WApplication;
import com.taoze.weather.model.dao.CityDao;
import com.taoze.weather.model.dao.CountyDao;
import com.taoze.weather.model.dao.ProvinceDao;
import com.taoze.weather.model.entity.City;
import com.taoze.weather.model.entity.County;
import com.taoze.weather.model.entity.JuheWeather;
import com.taoze.weather.model.entity.Province;
import com.taoze.weather.model.entity.Weather;
import com.taoze.weather.presenter.IWeatherPresenter;
import com.taoze.weather.presenter.impl.WeatherPresenterImpl;
import com.taoze.weather.ui.adapter.WeatherRecyclerAdapter;
import com.taoze.weather.ui.common.BlurredView;
import com.taoze.weather.ui.common.CommonActivity;
import com.taoze.weather.ui.view.IWeatherView;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends CommonActivity implements IWeatherView{

    /*========== 控件相关 ===========*/
    @BindView(R.id.bv_weather)
    public BlurredView weatherBView;           //背景模糊图

    @BindView(R.id.rv_weather)
    public RecyclerView weatherRView;          //滑动列表

    public TextView tempText;                  //当前温度
    public TextView weatherText;               //天气
    public TextView windText;                  //风向
    public TextView windPowerText;             //风力
    public TextView humPowerText;              //湿度
    public TextView flPowerText;               //紫外线指数

    public TextView weekTv;                     //星期
    public TextView dateTv;                     //日期
    public TextView temperatureTv;             //温度
    public TextView dryingIndexTv;             //干燥指数
    public TextView washIndexTv;               //洗车指数
    public TextView travelIndexTv;             //旅行指数
    public TextView exerciseIndexTv;           //锻炼指数
    public TextView dressIndexTv;              //穿衣指数
    public TextView dressAdviceTv;             //穿衣建议
    public TextView comfortIndexTv;            //舒适度

    /*========== 数据相关 ===========*/


    /*========== 其他 ===========*/
    private int mScrollerY;                     //滚动距离
    private int mAlpha;                         //透明值

    private WeatherRecyclerAdapter mAdapter;

    private IWeatherPresenter weatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void bindViewListener() {
        super.bindViewListener();
        weatherRView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollerY += dy;                       //滚动距离
                if (Math.abs(mScrollerY) > 1000) {      //根据滚动距离控制模糊程度 滚动距离是模糊程度的十倍
                    mAlpha = 100;
                } else {
                    mAlpha = Math.abs(mScrollerY) / 10;
                }
                weatherBView.setBlurredLevel(mAlpha);    //设置透明度等级
            }
        });
    }

    @Override
    public void onInitData() {
        loadAssetsFile();

        //透明状态栏 导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        weatherRView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new WeatherRecyclerAdapter(this);
        weatherRView.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int firstVisibleItemPosition = ((LinearLayoutManager)weatherRView.getLayoutManager()).findFirstVisibleItemPosition();
                View basicView = weatherRView.getLayoutManager().findViewByPosition(firstVisibleItemPosition);
                tempText = (TextView) basicView.findViewById(R.id.tv_basic_temp);
                weatherText = (TextView) basicView.findViewById(R.id.tv_basic_weather);
                windText = (TextView) basicView.findViewById(R.id.tv_basic_wind);
                windPowerText = (TextView) basicView.findViewById(R.id.tv_basic_wind_power);
                humPowerText = (TextView) basicView.findViewById(R.id.tv_basic_hum_power);
                flPowerText = (TextView) basicView.findViewById(R.id.tv_basic_fl_power);

                weekTv = (TextView) basicView.findViewById(R.id.tv_basic_week);
                dateTv = (TextView) basicView.findViewById(R.id.tv_basic_date);
                temperatureTv = (TextView) basicView.findViewById(R.id.tv_basic_temperature);
                dryingIndexTv = (TextView) basicView.findViewById(R.id.tv_basic_drying_index);
                washIndexTv = (TextView) basicView.findViewById(R.id.tv_basic_wash_index);
                travelIndexTv = (TextView) basicView.findViewById(R.id.tv_basic_travel_index);
                exerciseIndexTv = (TextView) basicView.findViewById(R.id.tv_basic_exercise_index);
                dressIndexTv = (TextView) basicView.findViewById(R.id.tv_basic_dressing_index);
                dressAdviceTv = (TextView) basicView.findViewById(R.id.tv_basic_dressing_advice);
                comfortIndexTv = (TextView) basicView.findViewById(R.id.tv_basic_comfort_index);

                weatherPresenter = new WeatherPresenterImpl(MainActivity.this);
                String cityNO = "武汉";
                weatherPresenter.getWeather(cityNO);
            }
        },500);
    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void setWeather(Weather weather) {
        if(weather == null || tempText == null)return;
        tempText.setText(weather.getWeatherinfo().getTemp());
        weatherText.setText(weather.getWeatherinfo().getCity()+" | "+weather.getWeatherinfo().getWeather());
        windPowerText.setText(weather.getWeatherinfo().getWS());
        humPowerText.setText(weather.getWeatherinfo().getSD());
    }

    @Override
    public void setWeather(JuheWeather juheWeather) {
        if(juheWeather == null || tempText == null)return;
        tempText.setText(juheWeather.getSk().getTemp()+"℃");
        weatherText.setText(juheWeather.getToday().getCity()+"  |  "+juheWeather.getToday().getWeather());
        windText.setText(juheWeather.getSk().getWind_direction());
        windPowerText.setText(juheWeather.getSk().getWind_strength());
        humPowerText.setText(juheWeather.getSk().getHumidity());
        flPowerText.setText(juheWeather.getToday().getUv_index());

        weekTv.setText(juheWeather.getToday().getWeek());
        dateTv.setText(juheWeather.getToday().getDate_y());
        temperatureTv.setText(juheWeather.getToday().getTemperature());
        comfortIndexTv.setText(TextUtils.isEmpty(juheWeather.getToday().getComfort_index())?"无数据":juheWeather.getToday().getComfort_index());
        dryingIndexTv.setText(TextUtils.isEmpty(juheWeather.getToday().getDrying_index())?"无数据":juheWeather.getToday().getDrying_index());
        washIndexTv.setText(TextUtils.isEmpty(juheWeather.getToday().getWash_index())?"无数据":juheWeather.getToday().getWash_index());
        travelIndexTv.setText(TextUtils.isEmpty(juheWeather.getToday().getTravel_index())?"无数据":juheWeather.getToday().getTravel_index());
        exerciseIndexTv.setText(TextUtils.isEmpty(juheWeather.getToday().getExercise_index())?"无数据":juheWeather.getToday().getExercise_index());
        dressIndexTv.setText(TextUtils.isEmpty(juheWeather.getToday().getDressing_index())?"无数据":juheWeather.getToday().getDressing_index());
        dressAdviceTv.setText(TextUtils.isEmpty(juheWeather.getToday().getDressing_advice())?"":juheWeather.getToday().getDressing_advice());

        List<JuheWeather.Future> futures = juheWeather.getFutures();
    }

    private void loadAssetsFile(){
        try {
            ProvinceDao dao = new ProvinceDao(this);
            List<Province> list = dao.query();
            if(list != null && list.size()> 0){
                return;
            }
            final InputStream is = this.getResources().getAssets().open("city.xml");
            new  Thread(new Runnable() {
                @Override
                public void run() {
                    parseXML(is);
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseXML(InputStream inStream) {
        Log.d(WApplication.TAG, "解析天气查询城市代码xml数据");
        XmlPullParser parser = Xml.newPullParser();
        ProvinceDao provinceDao = new ProvinceDao(this);
        CityDao cityDao = new CityDao(this);
        CountyDao countyDao = new CountyDao(this);

        List<City> citys = new ArrayList<>();
        List<County> counties = new ArrayList<>();
        List<Province> provinces = new ArrayList<>();
        Province province = null;
        City city = null;
        County county = null;
        try {
            parser.setInput(inStream, "UTF-8");// 设置数据源编码
            int eventType = parser.getEventType();// 获取事件类型
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                        // 实例化集合类
//                        province = new Province();
//                        city = new City();
//                        county = new County();
                        break;
                    case XmlPullParser.START_TAG://开始读取某个标签
                        //通过getName判断读到哪个标签，然后通过nextText()获取文本节点值，或通过getAttributeValue(i)获取属性节点值
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("province")) {
                            province = new Province();
                            province.id = parser.getAttributeValue("","id");
                            province.name = parser.getAttributeValue("","name");
//                            userInfo = new User();
                        }
                        if (name.equalsIgnoreCase("city")) {
                            city = new City();
                            city.id = parser.getAttributeValue("","id");
                            city.name = parser.getAttributeValue("","name");
                            city.pid = province.id;
                        }

                        if (name.equalsIgnoreCase("county")) {
                            county = new County();
                            county.id = parser.getAttributeValue("","id");
                            county.name = parser.getAttributeValue("","name");
                            county.pid = province.id;
                            county.cid = city.id;
                            county.code = parser.getAttributeValue("","weatherCode");
                        }
                        break;
                    case XmlPullParser.END_TAG:// 结束元素事件
                        //读完一个实体，可以将其添加到集合类中
                        String tag = parser.getName();
                        if (tag.equalsIgnoreCase("county")) {
                            counties.add(county);
                        }else if(tag.equalsIgnoreCase("city")){
                            citys.add(city);
                        }else if(tag.equalsIgnoreCase("province")){
                            provinces.add(province);
                        }
                        break;
                }
                eventType = parser.next();
            }
            inStream.close();
            cityDao.save(citys);
            countyDao.save(counties);
            provinceDao.save(provinces);
            Log.e(WApplication.TAG,"parseXml finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

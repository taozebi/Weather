package com.taoze.weather.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.util.Xml;

import com.ewide.core.orm.DBConfig;
import com.taoze.weather.R;
import com.taoze.weather.global.WApplication;
import com.taoze.weather.model.dao.CityDao;
import com.taoze.weather.model.dao.CountyDao;
import com.taoze.weather.model.dao.ProvinceDao;
import com.taoze.weather.model.entity.City;
import com.taoze.weather.model.entity.County;
import com.taoze.weather.model.entity.Province;
import com.taoze.weather.ui.common.CommonActivity;
import com.taoze.weather.util.Utils;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onInitData() {
        initializeDB();
        loadAssetsFile();
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

    private void initializeDB() {
        Log.d(WApplication.TAG, "init DB");
        String path = Utils.getAppDir(this, getResources().getString(R.string.app_name)) + "/config/" + "db";
        Log.d(WApplication.TAG, "Database Path --> " + path);
        DBConfig.DB_PATH = path;
        DBConfig.DB_VERSION = 3;
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

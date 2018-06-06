package com.taoze.weather.model.entity;

/**
 * Created by Taoze on 2018/6/6.
 */

public class JuheResult {
    private String resultcode;
    private String reason;
    private JuheWeather result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public JuheWeather getResult() {
        return result;
    }

    public void setResult(JuheWeather result) {
        this.result = result;
    }
}

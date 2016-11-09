package com.cyj.chanyouji.bean;

/**
 * Created by Biu丶 on 2016/11/5.
 */
public class ToolCountry {
    private String temp_min;//1,
    private String temp_max;//21,
    private String current_time;//private String 17:59，周六private String ,
    private String urls_category_0;//true,
    private String urls_category_1;//false,
    private String language_code;//private String jpprivate String ,
    private String currency_code;//private String JPYprivate String ,
    private String currency_display;//private String 日元private String ,
    private String country_name;//private String japanprivate String

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }

    public String getUrls_category_0() {
        return urls_category_0;
    }

    public void setUrls_category_0(String urls_category_0) {
        this.urls_category_0 = urls_category_0;
    }

    public String getUrls_category_1() {
        return urls_category_1;
    }

    public void setUrls_category_1(String urls_category_1) {
        this.urls_category_1 = urls_category_1;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getCurrency_display() {
        return currency_display;
    }

    public void setCurrency_display(String currency_display) {
        this.currency_display = currency_display;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}

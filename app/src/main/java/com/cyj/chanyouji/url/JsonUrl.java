package com.cyj.chanyouji.url;

/**
 * Created by Biu丶 on 2016/10/31.
 */
public class JsonUrl {
    //banner
    final public static String BANNER = "https://chanyouji.com/api/adverts.json?name=app_featured_banner_android";
    //banner--二级
    final public static String BANNER_TWO_START =  "https://chanyouji.com/api/articles/";
    final public static String BANNER_TWO_END = ".json";
    //游记
    final public static String YOUJI = "https://chanyouji.com/api/trips/featured.json?page=";
    //游记--二级
    final public static String YOUJI_TWO_START = "https://chanyouji.com/api/trips/";
    final public static String YOUJI_TWO_END = ".json";
    //攻略--一级
    final public static String GONGLUE = "http://chanyouji.com/api/destinations.json";
    //攻略--二级
    final public static String GONGLUE_TWO_START = "https://chanyouji.com/api/destinations/";
    final public static String GONGLUE_TWO_END = ".json";
    //攻略--二级--可能是下载,后面加id，如165
    final public static String GONGLUE_TWO_DOWNLOAD = "https://chanyouji.com/api/destinations/";
    //攻略--二级--攻略,后面加id，如165
    final public static String SUB_GL_START = "https://chanyouji.com/api/wiki/destinations/";
    final public static String SUB_GL_END = ".json";
    //攻略--二级--行程
    final public static String SUB_XC_START = "https://chanyouji.com/api/destinations/plans/";
    final public static String SUB_XC_END = ".json";
    //攻略--二级--旅行地
    final public static String SUB_LXD_START = "https://chanyouji.com/api/destinations/attractions/";
    final public static String SUB_LXD_END = ".json?page=";
    //攻略--二级--专题
    final public static String SUB_ZT_START = "https://chanyouji.com/api/articles.json?destination_id=";
    final public static String SUB_ZT_END = "&page=";
    //攻略--二级--攻略--搜索//& //q= 和&之间为搜索内容，utf-8
    final public static String SUB_GL_SEARCH_START = "http://chanyouji.com/api/wiki/destinations/search/";
    final public static String SUB_GL_SEARCH_END = ".json?q=";
    //攻略--二级--行程--二级
    final public static String SUB_XC_TWO_START = "https://chanyouji.com/api/plans/";
    final public static String SUB_XC_TWO_END = ".json";
    //攻略--二级--专题--二级
    final public static String SUB_ZT_TWO_START ="https://chanyouji.com/api/articles/" ;//76
    final public static String SUB_ZT_TWO_END = ".json"; // .json

    //攻略--二级--旅行-二级
    final public static String SUB_LX_TWO_START ="http://chanyouji.com/api/attractions/" ;//35443
    final public static String SUB_LX_TWO_END = ".json"; // .json


    final public static String SUB_LX_THREE_PHOTO_START = "http://chanyouji.com/api/attractions/photos/"; // JIA ID ;
    final public static String SUB_LX_THREE_PHOTO_END =".json?page=" ;

    //搜索列表
    final public static String SEARCH = "https://chanyouji.com/api/destinations/list.json";
    final public static String SEARCH_TWO = "https://chanyouji.com/api/destinations/trips/";

    public static String SEARCH_MONTH = "https://chanyouji.com/api/trips/month/";

    //搜索
    public static String TRAVEL = "https://chanyouji.com/api/search/trips.json?";
    public static String DESTINATION= "https://chanyouji.com/api/search/attractions.json?";
    public static String USER= "https://chanyouji.com/api/search/users.json?";

    public static String DESTINATION_TWO = "https://chanyouji.com/api/attractions/35443.json";
    public static String USER_TRAVEL = "https://chanyouji.com/api/users/";
    public static String USER_LIKE = "https://chanyouji.com/api/users/likes/";

    public static String TOOL = "http://chanyouji.com/api/wiki/destinations.json";
    public static String TEMP = "http://chanyouji.com/api/wiki/destinations/infos/";

    public static String MAP_START = "http://chanyouji.com/api/destinations/attractions/";
    public static String MAP_END = ".json?all=true";

    public static final String APP_ID = "20161107000031420";
    public static final String SECURITY_KEY = "uI7bqTdkD8r9AqlwZB1d";
}

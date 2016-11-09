package com.cyj.chanyouji.bean;

import java.util.List;

/**
 * Created by maocalm on 2016/11/2.
 */
public class GlDsGl {

    String category_type;

    List<Pages> pages;

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

    public List<Pages> getPages() {
        return pages;
    }

    public void setPages(List<Pages> pages) {
        this.pages = pages;
    }

    public class Pages {

        String id; //3,
        String title; //"游玩指南",
        List<Childern> children; //[]

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Childern> getChildren() {
            return children;
        }

        public void setChildren(List<Childern> children) {
            this.children = children;
        }
    }

    public class Childern {

        String id; //4,
        String title; //"日本游玩概览",
        List<Sections> sections; //[]

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Sections> getSections() {
            return sections;
        }

        public void setSections(List<Sections> sections) {
            this.sections = sections;
        }
    }

    public class Sections {
        String id; //100,
        String title; //"概览与地图",
        Description_user_ids description_user_ids; //{ },
        String ctrip_attraction_ids; //null,
        String description; //"日本旅游目的地众多，以东京为中心的关东地区和以京都、大阪、神户为中心的关西地区是最热门的旅游地，遍布全境的樱花和天然温泉是其显著的特色。 相近的文字，完善友好的公共设施使得自由行非常容易。国内飞涨的物价已让日本的优质商品有了性价比，到了打折季节更会让人欲罢不能，加上与国人近似的饮食口味和高质量的天然食材，这里无疑还是吃货向往的天堂。",
        String travel_date; //null,
        List<Attractions> attractions; //: [ ],
        List<Hotels> hotels; // : [ ],
        List<PagesGL> pages;  // : [ ],
        List<Photos> photos; //: [],
        List<Items> items; // : [ ]

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Description_user_ids getDescription_user_ids() {
            return description_user_ids;
        }

        public void setDescription_user_ids(Description_user_ids description_user_ids) {
            this.description_user_ids = description_user_ids;
        }

        public String getCtrip_attraction_ids() {
            return ctrip_attraction_ids;
        }

        public void setCtrip_attraction_ids(String ctrip_attraction_ids) {
            this.ctrip_attraction_ids = ctrip_attraction_ids;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTravel_date() {
            return travel_date;
        }

        public void setTravel_date(String travel_date) {
            this.travel_date = travel_date;
        }

        public List<Attractions> getAttractions() {
            return attractions;
        }

        public void setAttractions(List<Attractions> attractions) {
            this.attractions = attractions;
        }

        public List<Hotels> getHotels() {
            return hotels;
        }

        public void setHotels(List<Hotels> hotels) {
            this.hotels = hotels;
        }

        public List<PagesGL> getPages() {
            return pages;
        }

        public void setPages(List<PagesGL> pages) {
            this.pages = pages;
        }

        public List<Photos> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Photos> photos) {
            this.photos = photos;
        }

        public List<Items> getItems() {
            return items;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }
    }
    public class Description_user_ids {

    }

    public class Attractions {

    }

    public class Hotels {

    }

    public class PagesGL {

    }

    public class Photos {

        String image_url; //"http://w.chanyouji.cn/1405591989250p18t1svavvq3kafl1tfkc4665r1.jpg",
        String image_width; //1100,
        String image_height; //1216,
        String description; //"日本概览地图",
        String trip_id; //null,
        String note_id; //null,
        String user_name; //null

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getImage_width() {
            return image_width;
        }

        public void setImage_width(String image_width) {
            this.image_width = image_width;
        }

        public String getImage_height() {
            return image_height;
        }

        public void setImage_height(String image_height) {
            this.image_height = image_height;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTrip_id() {
            return trip_id;
        }

        public void setTrip_id(String trip_id) {
            this.trip_id = trip_id;
        }

        public String getNote_id() {
            return note_id;
        }

        public void setNote_id(String note_id) {
            this.note_id = note_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }


    public class Items {

    }
}

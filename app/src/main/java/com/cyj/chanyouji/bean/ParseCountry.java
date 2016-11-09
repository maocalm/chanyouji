package com.cyj.chanyouji.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by mandy on 2016/11/1.
 */
public class ParseCountry {
    public static List<Country> parseData(String json){
        return new Gson().fromJson(json,new TypeToken<List<Country>>(){}.getType());
    }
    public class Country{
                private String id; //595220,
                private String name; //private String 山阴山阳夏未央private String ,
                private String photos_count; //128,
                private String start_date; //private String 2016-08-10private String ,
                private String end_date; //private String 2016-08-15private String ,
                private String days; //6,
                private String level; //3,
                private String views_count; //1058,
                private String comments_count; //0,
                private String likes_count; //15,
                private String source; //private String webprivate String ,
                private String front_cover_photo_url; //private String http://p.chanyouji.cn/595220/1474297974951p1at1fv8sn1hvu1e0k12kn1u8nhdm.jpgprivate String ,
                private String featured; //false,
                private User user;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhotos_count() {
            return photos_count;
        }

        public void setPhotos_count(String photos_count) {
            this.photos_count = photos_count;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getViews_count() {
            return views_count;
        }

        public void setViews_count(String views_count) {
            this.views_count = views_count;
        }

        public String getComments_count() {
            return comments_count;
        }

        public void setComments_count(String comments_count) {
            this.comments_count = comments_count;
        }

        public String getLikes_count() {
            return likes_count;
        }

        public void setLikes_count(String likes_count) {
            this.likes_count = likes_count;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getFront_cover_photo_url() {
            return front_cover_photo_url;
        }

        public void setFront_cover_photo_url(String front_cover_photo_url) {
            this.front_cover_photo_url = front_cover_photo_url;
        }

        public String getFeatured() {
            return featured;
        }

        public void setFeatured(String featured) {
            this.featured = featured;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
    public class User{
        private String id; //129571,
        private String name; //private String 腐喵要脱非入欧private String ,
        private String image; //private String http://tva2.sinaimg.cn/crop.0.0.1080.1080.50/53f149ffjw8ety1tqhcgrj20u00u00w7.jpgprivate String

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}

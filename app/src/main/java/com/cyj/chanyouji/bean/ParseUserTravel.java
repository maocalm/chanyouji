package com.cyj.chanyouji.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by mandy on 2016/11/2.
 */
public class ParseUserTravel {
    private String id;//305842,
    private String name;//private String zeyroprivate String ,
    private String gender;//0,
    private String image;//private String https://img3.doubanio.com/icon/ul52601438-16.jpgprivate String ,
    private String favorites_count;//37,
    private String likes_count;//94,
    private String attraction_favorites_count;//1,
    private String poi_favorites_count;//1,
    private String friends_count;//12,
    private String fans_count;//37,
    private String trips_count;//4,
    private String plans_count;//0,
    private String current_user_friend;//false,

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFavorites_count() {
        return favorites_count;
    }

    public void setFavorites_count(String favorites_count) {
        this.favorites_count = favorites_count;
    }

    public String getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(String likes_count) {
        this.likes_count = likes_count;
    }

    public String getAttraction_favorites_count() {
        return attraction_favorites_count;
    }

    public void setAttraction_favorites_count(String attraction_favorites_count) {
        this.attraction_favorites_count = attraction_favorites_count;
    }

    public String getPoi_favorites_count() {
        return poi_favorites_count;
    }

    public void setPoi_favorites_count(String poi_favorites_count) {
        this.poi_favorites_count = poi_favorites_count;
    }

    public String getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(String friends_count) {
        this.friends_count = friends_count;
    }

    public String getFans_count() {
        return fans_count;
    }

    public void setFans_count(String fans_count) {
        this.fans_count = fans_count;
    }

    public String getTrips_count() {
        return trips_count;
    }

    public void setTrips_count(String trips_count) {
        this.trips_count = trips_count;
    }

    public String getPlans_count() {
        return plans_count;
    }

    public void setPlans_count(String plans_count) {
        this.plans_count = plans_count;
    }

    public String getCurrent_user_friend() {
        return current_user_friend;
    }

    public void setCurrent_user_friend(String current_user_friend) {
        this.current_user_friend = current_user_friend;
    }

    private List<Trips> trips;

    public List<Trips> getTrips() {
        return trips;
    }

    public void setTrips(List<Trips> trips) {
        this.trips = trips;
    }

    public static List<Trips> parseData(String json){
        return new Gson().fromJson(json,ParseUserTravel.class).getTrips();
    }
    public class Trips{
                private String id;//165592,
                private String name;//private String 夏天冲绳潜海去-离岛篇private String ,
                private String photos_count;//95,
                private String start_date;//private String 2014-09-01private String ,
                private String end_date;//private String 2014-09-06private String ,
                private String days;//6,
                private String level;//2,
                private String privacy;//false,
                private String views_count;//692,
                private String comments_count;//0,
                private String likes_count;//2,
                private String source;//private String webprivate String ,
                private String password;//null,
                private String front_cover_photo_url;//private String http://p.chanyouji.cn/165592/1411472209048p192h4jubu1o9q1u6v1atb1cgere24.jpgprivate String

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

        public String getPrivacy() {
            return privacy;
        }

        public void setPrivacy(String privacy) {
            this.privacy = privacy;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFront_cover_photo_url() {
            return front_cover_photo_url;
        }

        public void setFront_cover_photo_url(String front_cover_photo_url) {
            this.front_cover_photo_url = front_cover_photo_url;
        }
    }
}

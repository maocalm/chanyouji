package com.cyj.chanyouji.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by mandy on 2016/11/2.
 */
public class ParseUserLike {
    public static List<UserLike> parseData(String json){
        return new Gson().fromJson(json,new TypeToken<List<UserLike>>(){}.getType());
    }
    public class UserLike{
        private String id;
        private String description;
        private String width;
        private String height;
        private String photo_url;
        private String trip_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }

        public String getTrip_id() {
            return trip_id;
        }

        public void setTrip_id(String trip_id) {
            this.trip_id = trip_id;
        }
    }
}

package com.cyj.chanyouji.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by mandy on 2016/11/2.
 */
public class ParseUser {
    public static List<User> parseData(String json){
       return new Gson().fromJson(json,new TypeToken<List<User>>(){}.getType());
    }
    public class User{
        private String id;
        private String name;
        private String latest_publish_trip_id;
        private String publish_trips_count;
        private String latest_publish_trip_name;
        private String image;

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

        public String getLatest_publish_trip_id() {
            return latest_publish_trip_id;
        }

        public void setLatest_publish_trip_id(String latest_publish_trip_id) {
            this.latest_publish_trip_id = latest_publish_trip_id;
        }

        public String getPublish_trips_count() {
            return publish_trips_count;
        }

        public void setPublish_trips_count(String publish_trips_count) {
            this.publish_trips_count = publish_trips_count;
        }

        public String getLatest_publish_trip_name() {
            return latest_publish_trip_name;
        }

        public void setLatest_publish_trip_name(String latest_publish_trip_name) {
            this.latest_publish_trip_name = latest_publish_trip_name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}

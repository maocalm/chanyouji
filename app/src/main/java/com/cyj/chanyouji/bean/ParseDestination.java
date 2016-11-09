package com.cyj.chanyouji.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by mandy on 2016/11/2.
 */
public class ParseDestination {
    public static List<Destination> parseData(String json){
        return new Gson().fromJson(json,new TypeToken<List<Destination>>(){}.getType());
    }
    public class Destination{
        private String id;
        private String name;
        private String user_score;
        private String attraction_trips_count;
        private String description;
        private String image_url;

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

        public String getUser_score() {
            return user_score;
        }

        public void setUser_score(String user_score) {
            this.user_score = user_score;
        }

        public String getAttraction_trips_count() {
            return attraction_trips_count;
        }

        public void setAttraction_trips_count(String attraction_trips_count) {
            this.attraction_trips_count = attraction_trips_count;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}

package com.cyj.chanyouji.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mandy on 2016/11/1.
 */
public class ParseSearchCountry {

    private List<AbroadCountry> other_destinations;
    private List<HomeCountry> china_destinations;

    public List<AbroadCountry> getOther_destinations() {
        return other_destinations;
    }

    public void setOther_destinations(List<AbroadCountry> other_destinations) {
        this.other_destinations = other_destinations;
    }

    public List<HomeCountry> getChina_destinations() {
        return china_destinations;
    }

    public void setChina_destinations(List<HomeCountry> china_destinations) {
        this.china_destinations = china_destinations;
    }

    public static List<AbroadCountry> parseAbroadData(String json){
        return new Gson().fromJson(json,ParseSearchCountry.class).getOther_destinations();
    }
    public static List<HomeCountry> parseHomeData(String json){
        return new Gson().fromJson(json,ParseSearchCountry.class).getChina_destinations();
    }
    public class AbroadCountry implements Serializable{
        private String id;
        private String name;
        private String destination_trips_count;

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

        public String getDestination_trips_count() {
            return destination_trips_count;
        }

        public void setDestination_trips_count(String destination_trips_count) {
            this.destination_trips_count = destination_trips_count;
        }
    }
    public class HomeCountry{
        private String id;
        private String name;
        private String destination_trips_count;

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

        public String getDestination_trips_count() {
            return destination_trips_count;
        }

        public void setDestination_trips_count(String destination_trips_count) {
            this.destination_trips_count = destination_trips_count;
        }
    }


}

package com.cyj.chanyouji.bean;

import java.util.List;

/**
 * Created by Biu丶 on 2016/11/1.
 */
public class Country {

    private String category;//private String 1private String ,
    private List<Destination> destinations;//Array[14]

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public class Destination{

        private String id;//55,
        private String name_zh_cn;//private String 日本private String ,
        private String name_en;//private String Japanprivate String ,
        private String poi_count;//1010,
        private String lat;//36.2048,
        private String lng;//138.253,
        private String image_url;//private String http://m.chanyouji.cn/destinations/55-portrait.jpgprivate String ,
        private String updated_at;//1457039212

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName_zh_cn() {
            return name_zh_cn;
        }

        public void setName_zh_cn(String name_zh_cn) {
            this.name_zh_cn = name_zh_cn;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getPoi_count() {
            return poi_count;
        }

        public void setPoi_count(String poi_count) {
            this.poi_count = poi_count;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}

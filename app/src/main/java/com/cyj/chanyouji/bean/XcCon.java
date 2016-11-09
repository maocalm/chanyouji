package com.cyj.chanyouji.bean;

import java.util.List;

/**
 * Created by maocalm on 2016/11/2.
 */
public class XcCon {

    String id; //1693,
    String name; //"日本全景7日游",
    String description; //"京都的优雅，东京大阪的时尚，富士山的秀美，北海道的浪漫一次体验，7天最全面的日本游线路。",
    String budget; //0,
    String budget_description; //"",
    String tips; //"",
    String start_date; //null,
    String current_user_favorite; //false,
    String updated_at; //1410504676,
    String image_url; //"http://m.chanyouji.cn/plans/1693.jpg",
    List<Plan_days> plan_days; // : [],
    Destination destination; // : {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getBudget_description() {
        return budget_description;
    }

    public void setBudget_description(String budget_description) {
        this.budget_description = budget_description;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getCurrent_user_favorite() {
        return current_user_favorite;
    }

    public void setCurrent_user_favorite(String current_user_favorite) {
        this.current_user_favorite = current_user_favorite;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<Plan_days> getPlan_days() {
        return plan_days;
    }

    public void setPlan_days(List<Plan_days> plan_days) {
        this.plan_days = plan_days;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public class Plan_days {
        String id; //: 5513,
        String memo; //: "大阪本身是一个历史悠久的百川之城，沿穿城而过的水路漫游，既是大阪人的生活常态，也是游人感受水城气质的最好体验。更重要的是，每一条行走过的街道，每一个迎面而来的微笑，都勾勒出大阪生活之美，这样有人情味的城市让人感到无比的温暖和留恋。 #交通指南# 建议购买大阪周游卡1日券，可随便乘坐大阪市内的地铁和巴士，还可免费入场28处旅游设施，2000日元/张，性价比非常高。 ",
        List<Plan_nodes> plan_nodes; // : []

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public List<Plan_nodes> getPlan_nodes() {
            return plan_nodes;
        }

        public void setPlan_nodes(List<Plan_nodes> plan_nodes) {
            this.plan_nodes = plan_nodes;
        }
    }

    public class Plan_nodes {

        String id;  //23829,
        String entry_id;  //35945,
        String position;  //2,
        String candidate;  //false,
        String tips;  //"#游玩指南# 午餐过后前往大阪城公园，这是以丰臣秀吉建造的大阪城天守阁为中心的城市公园，是游客和大阪市民观光、休闲的场所。可与天守阁一同游玩，约2个小时。 #交通指南# 地铁堺筋线到堺筋本町站，换乘地铁中央线，到森之宫站下车。",
        String lat;  //"34.685612",
        String lng;  //"135.528591",
        String distance;  //3.03286311768366,
        String image_url;  //"http://m.chanyouji.cn/attractions/35945.jpg",
        String entry_name;  //"大阪城公园",
        String entry_type;  //"Attraction",
        String attraction_type;  //null,
        String user_entry;  //false,
        Destination destination;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEntry_id() {
            return entry_id;
        }

        public void setEntry_id(String entry_id) {
            this.entry_id = entry_id;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getCandidate() {
            return candidate;
        }

        public void setCandidate(String candidate) {
            this.candidate = candidate;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getEntry_name() {
            return entry_name;
        }

        public void setEntry_name(String entry_name) {
            this.entry_name = entry_name;
        }

        public String getEntry_type() {
            return entry_type;
        }

        public void setEntry_type(String entry_type) {
            this.entry_type = entry_type;
        }

        public String getAttraction_type() {
            return attraction_type;
        }

        public void setAttraction_type(String attraction_type) {
            this.attraction_type = attraction_type;
        }

        public String getUser_entry() {
            return user_entry;
        }

        public void setUser_entry(String user_entry) {
            this.user_entry = user_entry;
        }

        public Destination getDestination() {
            return destination;
        }

        public void setDestination(Destination destination) {
            this.destination = destination;
        }
    }

    public class Destination {

        String id; // : 167,
        String name_zh_cn; //: "关西"

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
    }
}

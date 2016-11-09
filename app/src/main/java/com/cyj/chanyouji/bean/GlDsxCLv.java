package com.cyj.chanyouji.bean;

/**
 * Created by maocalm on 2016/11/2.
 */
public class GlDsxCLv {


    String id;  //1693,
    String name;  //String 日本全景7日游String ,
    String budget;  //0,
    String start_date;  //null,
    String description;  //String 京都的优雅，东京大阪的时尚，富士山的秀美，北海道的浪漫一次体验，7天最全面的日本游线路。String ,
    String plan_days_count;  //7,
    String plan_nodes_count;  //38,
    String user_name;  //String String ,
    String image_url;  //String http://m.chanyouji.cn/plans/1693.jpgString ,
    String updated_at;  //1410504676

    @Override
    public String toString() {
        return "GlDsxCLv{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

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

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlan_days_count() {
        return plan_days_count;
    }

    public void setPlan_days_count(String plan_days_count) {
        this.plan_days_count = plan_days_count;
    }

    public String getPlan_nodes_count() {
        return plan_nodes_count;
    }

    public void setPlan_nodes_count(String plan_nodes_count) {
        this.plan_nodes_count = plan_nodes_count;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

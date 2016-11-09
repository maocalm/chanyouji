package com.cyj.chanyouji.bean;

import java.util.List;

/**
 * Created by maocalm on 2016/11/1.
 */
public class Destination {

    String id; // 55,
    String name_zh_cn; // String 日本String ,
    String name_en; // String JapanString ,
    String poi_count; // 1010,
    String plans_count; // 6,
    String articles_count; // 6,
    String contents_count; // 2438,
    String destination_trips_count; // 4308,
    String locked; // false,
    String wiki_app_publish; // true,
    String updated_at; // 1457039212,
    String image_url; // String http:   ; //m.chanyouji.cn/destinations/55-landscape.jpgString ,
    String guides_count; // 7,

    List<DestContents> mDestination_contentsList;
    DestIntro  intro ;

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

    public String getPlans_count() {
        return plans_count;
    }

    public void setPlans_count(String plans_count) {
        this.plans_count = plans_count;
    }

    public String getArticles_count() {
        return articles_count;
    }

    public void setArticles_count(String articles_count) {
        this.articles_count = articles_count;
    }

    public String getContents_count() {
        return contents_count;
    }

    public void setContents_count(String contents_count) {
        this.contents_count = contents_count;
    }

    public String getDestination_trips_count() {
        return destination_trips_count;
    }

    public void setDestination_trips_count(String destination_trips_count) {
        this.destination_trips_count = destination_trips_count;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getWiki_app_publish() {
        return wiki_app_publish;
    }

    public void setWiki_app_publish(String wiki_app_publish) {
        this.wiki_app_publish = wiki_app_publish;
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

    public String getGuides_count() {
        return guides_count;
    }

    public void setGuides_count(String guides_count) {
        this.guides_count = guides_count;
    }

    public List<DestContents> getDestination_contentsList() {
        return mDestination_contentsList;
    }

    public void setDestination_contentsList(List<DestContents> destination_contentsList) {
        mDestination_contentsList = destination_contentsList;
    }

    public DestIntro getIntro() {
        return intro;
    }

    public void setIntro(DestIntro intro) {
        this.intro = intro;
    }


    ///////////////////
    public class DestContents {
        String name;
        String description_html ;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription_html() {
            return description_html;
        }

        public void setDescription_html(String description_html) {
            this.description_html = description_html;
        }
    }
    ////////////
    public  class DestIntro{

        List<DesNotes> mNotes ;

        public List<DesNotes> getNotes() {
            return mNotes;
        }

        public void setNotes(List<DesNotes> notes) {
            mNotes = notes;
        }
    }
/////////////////////
    public class  DesNotes {

        String  description ;
        String photo_url ;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
}

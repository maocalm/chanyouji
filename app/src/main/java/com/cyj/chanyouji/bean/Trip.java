package com.cyj.chanyouji.bean;

/**
 * Created by Biu丶 on 2016/10/31.
 */
public class Trip {
    private String id;//567027,
    private String name;//private String 带着儿子骑行在台湾垦丁的海边公路上private String ,
    private String photos_count;//126,
    private String start_date;//private String 2016-04-12private String ,
    private String end_date;//private String 2016-04-15private String ,
    private String days;//4,
    private String level;//4,
    private String views_count;//13187,
    private String comments_count;//15,
    private String likes_count;//144,
    private String source;//private String webprivate String ,
    private String front_cover_photo_url;//private String http://p.chanyouji.cn/567027/1468041585314p1an718tsb1j3v2a11u1g3cp1sg513.jpgprivate String ,
    private String featured;//false,
    private User user;//Object{...}

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

    public class User{
        private String id;//108306,
        private String name;//private String 封面private String ,
        private String image;//private String http://q.qlogo.cn/qqapp/100277927/18A154099949BB8E30865D6FEA35ED04/100private String

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

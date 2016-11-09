package com.cyj.chanyouji.bean;

import java.util.List;

/**
 * Created by Biu丶 on 2016/10/31.
 */
public class Article {

    private String id;//625,
    private String name;//private String 氢气球旅行Appprivate String ,
    private String image_url;//private String http://m.chanyouji.cn/articles/625/ca9e50f74e273041e3a399bf5528f7b5.jpgprivate String ,
    private String title;//private String 新·蝉游记正式发布private String ,
    private String comments_count;//0,
    private String destination_id;//null,
    private String commentable;//false,
    private String current_user_favorite;//false,
    private String updated_at;//1450783658,
    private List<Section> article_sections;//Array[10]
    public class Section {

        private String title;//private String private String ,
        private String image_url;//private String private String ,
        private String image_width;//0,
        private String image_height;//0,
        private String description;//private String 2015年末，蝉游记原班人马发布创新之作：氢气球旅行App。在蝉小队心目中，它是旅行产品的进化形态。 App下载地址：：http://t.cn/RUF88uaprivate String

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getImage_width() {
            return image_width;
        }

        public void setImage_width(String image_width) {
            this.image_width = image_width;
        }

        public String getImage_height() {
            return image_height;
        }

        public void setImage_height(String image_height) {
            this.image_height = image_height;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments_count() {
        return comments_count;
    }

    public void setComments_count(String comments_count) {
        this.comments_count = comments_count;
    }

    public String getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(String destination_id) {
        this.destination_id = destination_id;
    }

    public String getCommentable() {
        return commentable;
    }

    public void setCommentable(String commentable) {
        this.commentable = commentable;
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

    public List<Section> getArticle_sections() {
        return article_sections;
    }

    public void setArticle_sections(List<Section> article_sections) {
        this.article_sections = article_sections;
    }

}

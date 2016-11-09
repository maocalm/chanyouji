package com.cyj.chanyouji.bean;

import java.util.List;

/**
 * Created by maocalm on 2016/11/3.
 */
public class ZtCon {

    private String id;//76,
    private String name;//private String 醉｜日本private String ,
    private String image_url;//private String http://p.chanyouji.cn/34062/1367929106035p17pverbioeog1pu2i1mirb11fd2.jpgprivate String ,
    private String title;//private String 日式小清新体验之旅private String ,
    private String comments_count;//1,
    private String destination_id;//55,
    private String commentable;//false,
    private String current_user_favorite;//false,
    private String updated_at;//1428458258,
    private List<Article_section> article_sections;//Array[29]

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

    public List<Article_section> getArticle_sections() {
        return article_sections;
    }

    public void setArticle_sections(List<Article_section> article_sections) {
        this.article_sections = article_sections;
    }

    public class Article_section{
        private String title;//private String private String ,
        private String image_url;//private String private String ,
        private String image_width;//0,
        private String image_height;//0,
        private String description;//
        private Attraction attraction;//Object{...}
        private Note note;

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

        public Attraction getAttraction() {
            return attraction;
        }

        public void setAttraction(Attraction attraction) {
            this.attraction = attraction;
        }

        public Note getNote() {
            return note;
        }

        public void setNote(Note note) {
            this.note = note;
        }

        public class Attraction{
            private String id;//47050,
            private String name;//private String 东京都private String ,
            private String user_score;//private String 4.6private String ,
            private String attraction_trips_count;//329,
            private String image_url;//private String http://m.chanyouji.cn/attractions/47050.jpgprivate String

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

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }
        }
        public class Note{
            private String id;//2789220,
            private String trip_id;//66896,
            private String trip_name;//private String 冲绳—琉球国的巡礼private String ,
            private String user_name;//private String 聪聪匆匆溜走private String

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTrip_id() {
                return trip_id;
            }

            public void setTrip_id(String trip_id) {
                this.trip_id = trip_id;
            }

            public String getTrip_name() {
                return trip_name;
            }

            public void setTrip_name(String trip_name) {
                this.trip_name = trip_name;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }
        }
    }

}

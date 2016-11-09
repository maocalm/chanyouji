package com.cyj.chanyouji.bean;

import java.util.List;

/**
 * Created by Biu丶 on 2016/11/1.
 */
public class TripDetail {

    private String id;//290122,
    private String name;//private String 【泰国的小清新玩法】清迈·拜县行摄记private String ,
    private String photos_count;//74,
    private String start_date;//private String 2015-02-20private String ,
    private String end_date;//private String 2015-02-26private String ,
    private String level;//4,
    private String privacy;//false,
    private String front_cover_photo_id;//9542926,
    private String views_count;//24260,
    private String comments_count;//131,
    private String likes_count;//596,
    private String favorites_count;//553,
    private String state;//private String publishprivate String ,
    private String source;//private String webprivate String ,
    private String serial_id;//null,
    private String serial_position;//null,
    private String serial_next_id;//0,
    private String updated_at;//1477895830,
    private List<Trip_Day> trip_days;//Array[7],
    private Tip tip;//Object{...},
    private User user;//Object{...},
    private String upload_token;//null,
    private String current_user_favorite;//false,
    private String password;//null,
    private String front_cover_photo_url;//private String http://p.chanyouji.cn/290122/1431669703433p19lb2k0npknj1ied21ku4ngla2.jpgprivate String ,
    private List<Notes_Likes_Comment> notes_likes_comments;//Array[80]

    public class Trip_Day{
        private String id;//818204,
        private String trip_date;//private String 2015-02-20private String ,
        private String day;//1,
        private String updated_at;//1431720430,
        private Destination destination;//:Object{...},
        private List<Node> nodes;//Array[3]
    
        public class Destination{
            private String idprivate;// String :115,
            private String name_zh_cnprivate;// String :private String 清迈与泰国北部private String

            public String getIdprivate() {
                return idprivate;
            }

            public void setIdprivate(String idprivate) {
                this.idprivate = idprivate;
            }

            public String getName_zh_cnprivate() {
                return name_zh_cnprivate;
            }

            public void setName_zh_cnprivate(String name_zh_cnprivate) {
                this.name_zh_cnprivate = name_zh_cnprivate;
            }
        }
        public class Node{
            private String id;//1852400,
            private String row_order;//0,
            private String score;//0,
            private String comment;//null,
            private String tips;//null,
            private Memo memo;//Object{...},
            private String entry_id;//null,
            private String lat;//0,
            private String lng;//0,
            private String entry_type;//null,
            private String user_entry;//false,
            private String entry_name;//null,
            private String attraction_type;//null,
            private String updated_at;//1431658253,
            private List<Note> notes;//Array[1]

            public class Memo{
                private String price_amount;//private String 130private String ,
                private String price_currency;//private String CNYprivate String

                public String getPrice_amount() {
                    return price_amount;
                }

                public void setPrice_amount(String price_amount) {
                    this.price_amount = price_amount;
                }

                public String getPrice_currency() {
                    return price_currency;
                }

                public void setPrice_currency(String price_currency) {
                    this.price_currency = price_currency;
                }
            }
            public class Note{
                private String id;//10351715,
                private String row_order;//0,
                private String layout;//private String fullprivate String ,
                private String col;//0,
                private String description;//private String 很多人对泰国的印象，或许都是人妖、海滩之类的，其实泰国还是小清新的天堂，喜欢小清新的你一定不能错过！ 喜欢旅游or摄影的朋友，可以加我微信交流哦： waterlily137 （注明蝉游记）或微博：伊莲137private String ,
                private String updated_at;//1431658840
                private Photo photo;//rivate;// String :Object{...}
                
                public class Photo{
                    private String id;//9540467,
                    private String image_width;//960,
                    private String image_height;//641,
                    private String image_file_size;//169170,
                    private String exif_lat;//private String 0.0private String ,
                    private String exif_lng;//private String 0.0private String ,
                    private String exif_date_time_original;//0,
                    private String url;//private String http://p.chanyouji.cn/290122/1431658198085p19lankt254vo1atvslmgrsnm2.jpgprivate String

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
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

                    public String getImage_file_size() {
                        return image_file_size;
                    }

                    public void setImage_file_size(String image_file_size) {
                        this.image_file_size = image_file_size;
                    }

                    public String getExif_lat() {
                        return exif_lat;
                    }

                    public void setExif_lat(String exif_lat) {
                        this.exif_lat = exif_lat;
                    }

                    public String getExif_lng() {
                        return exif_lng;
                    }

                    public void setExif_lng(String exif_lng) {
                        this.exif_lng = exif_lng;
                    }

                    public String getExif_date_time_original() {
                        return exif_date_time_original;
                    }

                    public void setExif_date_time_original(String exif_date_time_original) {
                        this.exif_date_time_original = exif_date_time_original;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getRow_order() {
                    return row_order;
                }

                public void setRow_order(String row_order) {
                    this.row_order = row_order;
                }

                public String getLayout() {
                    return layout;
                }

                public void setLayout(String layout) {
                    this.layout = layout;
                }

                public String getCol() {
                    return col;
                }

                public void setCol(String col) {
                    this.col = col;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public Photo getPhoto() {
                    return photo;
                }

                public void setPhoto(Photo photo) {
                    this.photo = photo;
                }
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRow_order() {
                return row_order;
            }

            public void setRow_order(String row_order) {
                this.row_order = row_order;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getTips() {
                return tips;
            }

            public void setTips(String tips) {
                this.tips = tips;
            }

            public Memo getMemo() {
                return memo;
            }

            public void setMemo(Memo memo) {
                this.memo = memo;
            }

            public String getEntry_id() {
                return entry_id;
            }

            public void setEntry_id(String entry_id) {
                this.entry_id = entry_id;
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

            public String getEntry_type() {
                return entry_type;
            }

            public void setEntry_type(String entry_type) {
                this.entry_type = entry_type;
            }

            public String getUser_entry() {
                return user_entry;
            }

            public void setUser_entry(String user_entry) {
                this.user_entry = user_entry;
            }

            public String getEntry_name() {
                return entry_name;
            }

            public void setEntry_name(String entry_name) {
                this.entry_name = entry_name;
            }

            public String getAttraction_type() {
                return attraction_type;
            }

            public void setAttraction_type(String attraction_type) {
                this.attraction_type = attraction_type;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public List<Note> getNotes() {
                return notes;
            }

            public void setNotes(List<Note> notes) {
                this.notes = notes;
            }
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTrip_date() {
            return trip_date;
        }

        public void setTrip_date(String trip_date) {
            this.trip_date = trip_date;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public Destination getDestination() {
            return destination;
        }

        public void setDestination(Destination destination) {
            this.destination = destination;
        }

        public List<Node> getNodes() {
            return nodes;
        }

        public void setNodes(List<Node> nodes) {
            this.nodes = nodes;
        }
    }
    public class Tip{
        private String id;//15793,
        private List<String> texts;//Array[5]

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getTexts() {
            return texts;
        }

        public void setTexts(List<String> texts) {
            this.texts = texts;
        }
    }
    public class User{
        private String id;//517088,
        private String name;//private String 伊莲137private String ,
        private String image;//private String http://a.chanyouji.cn/517088/1446373503.jpgprivate String

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
    public class Notes_Likes_Comment{
        private String id;//10351715,
        private String comments_count;//3,
        private String likes_count;//17,
        private String current_user_like;//false,
        private String current_user_comment;//false

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getCurrent_user_like() {
            return current_user_like;
        }

        public void setCurrent_user_like(String current_user_like) {
            this.current_user_like = current_user_like;
        }

        public String getCurrent_user_comment() {
            return current_user_comment;
        }

        public void setCurrent_user_comment(String current_user_comment) {
            this.current_user_comment = current_user_comment;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getFront_cover_photo_id() {
        return front_cover_photo_id;
    }

    public void setFront_cover_photo_id(String front_cover_photo_id) {
        this.front_cover_photo_id = front_cover_photo_id;
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

    public String getFavorites_count() {
        return favorites_count;
    }

    public void setFavorites_count(String favorites_count) {
        this.favorites_count = favorites_count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(String serial_id) {
        this.serial_id = serial_id;
    }

    public String getSerial_position() {
        return serial_position;
    }

    public void setSerial_position(String serial_position) {
        this.serial_position = serial_position;
    }

    public String getSerial_next_id() {
        return serial_next_id;
    }

    public void setSerial_next_id(String serial_next_id) {
        this.serial_next_id = serial_next_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<Trip_Day> getTrip_days() {
        return trip_days;
    }

    public void setTrip_days(List<Trip_Day> trip_days) {
        this.trip_days = trip_days;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUpload_token() {
        return upload_token;
    }

    public void setUpload_token(String upload_token) {
        this.upload_token = upload_token;
    }

    public String getCurrent_user_favorite() {
        return current_user_favorite;
    }

    public void setCurrent_user_favorite(String current_user_favorite) {
        this.current_user_favorite = current_user_favorite;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFront_cover_photo_url() {
        return front_cover_photo_url;
    }

    public void setFront_cover_photo_url(String front_cover_photo_url) {
        this.front_cover_photo_url = front_cover_photo_url;
    }

    public List<Notes_Likes_Comment> getNotes_likes_comments() {
        return notes_likes_comments;
    }

    public void setNotes_likes_comments(List<Notes_Likes_Comment> notes_likes_comments) {
        this.notes_likes_comments = notes_likes_comments;
    }
}

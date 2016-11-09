package com.cyj.chanyouji.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by mandy on 2016/11/3.
 */
public class ToolDestination {

    public static List<Destination> parseData(String json){
        return new Gson().fromJson(json,new TypeToken<List<Destination>>(){}.getType());
    }
    public class Destination{
        private String category;
        private List<Destinations> destinations;

        @Override
        public String toString() {
            return "Destination{" +
                    "category='" + category + '\'' +
                    ", destinations=" + destinations +
                    '}';
        }

        public class Destinations {
            private String id;
            private String name_zh_cn;
            private String name_en;
            private String image_url;
            private List<Children> children;

            @Override
            public String toString() {
                return "Destinations{" +
                        "id='" + id + '\'' +
                        ", name_zh_cn='" + name_zh_cn + '\'' +
                        ", name_en='" + name_en + '\'' +
                        ", image_url='" + image_url + '\'' +
                        ", children=" + children +
                        '}';
            }

            public class Children {
                private String id;
                private String name_zh_cn;
                private String name_en;
                private String destination_trips_count;
                private String image_url;
                private String items_count;

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


                public String getDestination_trips_count() {
                    return destination_trips_count;
                }

                public void setDestination_trips_count(String destination_trips_count) {
                    this.destination_trips_count = destination_trips_count;
                }

                public String getImage_url() {
                    return image_url;
                }

                public void setImage_url(String image_url) {
                    this.image_url = image_url;
                }

                public String getItems_count() {
                    return items_count;
                }

                public void setItems_count(String items_count) {
                    this.items_count = items_count;
                }
            }
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

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public List<Children> getChildren() {
                return children;
            }

            public void setChildren(List<Children> children) {
                this.children = children;
            }
        }


        public String getCategory() {

            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public List<Destinations> getDestinations() {
            return destinations;
        }

        public void setDestinations(List<Destinations> destinations) {
            this.destinations = destinations;
        }
    }

}

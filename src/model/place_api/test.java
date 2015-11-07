/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.place_api;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trungtran.vn
 */
public class test {

    public static void main(String[] args) {
//        String s;
//        s = "{\"lat_lng\":21.3333}";
//        Gson gson = new Gson();
//        Location lo = gson.fromJson(s, Location.class);
//        System.out.println(lo.toString());

        List<String> l = new ArrayList<>();
        l.add("hello");
        l.add("world");
        System.out.println(l);
    }

    class Location {

        private double lat_lng;
        private double lng;
        private List<Photo> photos;

        public Location() {
        }

        public Location(double lat, double lng, List<Photo> photos) {
            this.lat_lng = lat;
            this.lng = lng;
            this.photos = photos;
        }

        public List<Photo> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Photo> photos) {
            this.photos = photos;
        }

        public double getLat() {
            return lat_lng;
        }

        public void setLat(double lat) {
            this.lat_lng = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            String s = "lat:" + lat_lng + "\nlng:" + lng + "\nphotos:";

            return s;
        }

    }

    class Photo {

        private String link;

        public Photo() {
        }

        public Photo(String link) {
            this.link = link;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        @Override
        public String toString() {
            return "link: " + link;
        }
    }

}

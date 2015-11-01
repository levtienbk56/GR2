/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.place_api;

import java.util.List;

/**
 *
 * @author trungtran.vn
 */
public class Result {
    private Geometry geometry;
    private String icon;
    private String id;
    private String name;
    private List<Photo> photos;
    private String place_id;
    private String scope;
    private int price_level;
    private double rating;
    private String reference;
    private List<String> types;
    private String vicinity;

    public Result() {
    }

    public Result(Geometry geometry, String icon, String id, String name, List<Photo> photos, String place_id, String scope, int price_level, double rating, String reference, List<String> types, String vicinity) {
        this.geometry = geometry;
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.photos = photos;
        this.place_id = place_id;
        this.scope = scope;
        this.price_level = price_level;
        this.rating = rating;
        this.reference = reference;
        this.types = types;
        this.vicinity = vicinity;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getPrice_level() {
        return price_level;
    }

    public void setPrice_level(int price_level) {
        this.price_level = price_level;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
    
    
}

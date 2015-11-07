/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.place_api.NearBySearchResult;

/**
 *
 * @author trungtran.vn
 */
public class GooglePlaceModel {
    private Coordinate coordinate;
    private NearBySearchResult nearPlace;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public NearBySearchResult getNearPlace() {
        return nearPlace;
    }

    public void setNearPlace(NearBySearchResult nearPlace) {
        this.nearPlace = nearPlace;
    }
    
    
}

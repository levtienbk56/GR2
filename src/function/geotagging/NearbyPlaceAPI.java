/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.geotagging;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import model.Coordinate;
import model.GPSPoint;
import model.place_api.NearBySearchResult;
import model.place_api.Result;
import utils.http.ClientRequest;

/**
 *
 * @author trungtran.vn
 */
public class NearbyPlaceAPI {
    private static final String API_KEY="AIzaSyAxT_lAlVf9oKHq1aCw47Qt0SJXnRWJbbs";
    public static void main(String[] args) {

    }

    /**
     * request nearby place base on coordinate and radius
     *
     * @param point: gps coordinate point
     * @param radius: limit ranger
     * @return
     */
    public static List<Result> requestNearbyPlace(Coordinate point, int radius) {
        List<Result> results = new ArrayList<>();
        List<Result> r = new ArrayList<>();
        try {
            String url;
            Gson gson = new Gson();
            ClientRequest client = new ClientRequest();
            url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + point.getLat() + "," + point.getLng() + "&radius=" + radius + "&key="+API_KEY;
            String json = client.request(url);
            NearBySearchResult obj = gson.fromJson(json, NearBySearchResult.class);
            results = obj.getResults();
            for (Result x : results) {
                if (Result.listTypes.contains(x.getTypes().get(0))) {
                    r.add(x);
                }
            }
        } catch (NullPointerException e) {

        }
        try {
            System.out.println("requestNearbyPlace: " + point.getLat() + "," + point.getLng() + ":" + r.size());
            for (String x : r.get(0).getTypes()) {
                System.out.print("- " + x);
            }
            System.out.println("");
        } catch (Exception ex) {

        }
        return r;
    }

}

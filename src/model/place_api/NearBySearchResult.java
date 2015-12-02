/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.place_api;

import com.google.gson.Gson;
import java.util.List;
import utils.http.ClientRequest;

/**
 *
 * @author trungtran.vn
 */
public class NearBySearchResult {

    private List<String> htmlAttribution;
    private String nextPageToken;
    private List<Result> results;
    private String status;

    public NearBySearchResult() {
    }

    public NearBySearchResult(List<String> htmlAttribution, String nextPageToken, List<Result> results, String status) {
        this.htmlAttribution = htmlAttribution;
        this.nextPageToken = nextPageToken;
        this.results = results;
        this.status = status;
    }

    public List<String> getHtmlAttribution() {
        return htmlAttribution;
    }

    public void setHtmlAttribution(List<String> htmlAttribution) {
        this.htmlAttribution = htmlAttribution;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private static final String URL_TEST = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=21.011557,105.849261&radius=50&key=AIzaSyAxT_lAlVf9oKHq1aCw47Qt0SJXnRWJbbs";

    public static void main(String[] args) {
        ClientRequest client = new ClientRequest();
        String json = client.request(URL_TEST);

        Gson gson = new Gson();
        NearBySearchResult obj = gson.fromJson(json, NearBySearchResult.class);
        for (Result result : obj.getResults()) {

            System.out.print(result.getId() + ", ");

            System.out.println("");
        }
    }
    
}

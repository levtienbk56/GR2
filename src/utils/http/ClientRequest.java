/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientRequest {

    private final String USER_AGENT = "Mozilla/5.0";

    private static final String URL_TEST = "http://www.google.com/search?q=mkyong";

    public static void main(String[] args) {
        ClientRequest client = new ClientRequest();
        String result = client.request(URL_TEST);
    }

    /**
     * get Json result from Geocoder service
     *
     * @param link
     * @return an String of json result
     */
    public String request(String link) {
        String results = "";
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // optional default is GET
            conn.setRequestMethod("GET");

            //add request header
            conn.setRequestProperty("User-Agent", USER_AGENT);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder out = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                out.append(line);
            }

            results = out.toString();
            conn.disconnect();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return results;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.geotagging;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import model.GPSPoint;
import model.place_api.NearBySearchResult;
import model.place_api.Result;
import utils.GPSPointExtractor;
import utils.file.MyFile;
import utils.http.ClientRequest;

/**
 *
 * @author trungtran.vn
 */
public class PresenterImpl implements Presenter {

    private MyView view;
    InteractorImpl interactor;

    public PresenterImpl(MyView view) {
        this.view = view;
    }

    /**
     * search nearby Place of points
     *
     * @param pathFile file content list of point
     */
    @Override
    public void doRequest(String pathFile) {
        Gson gson = new Gson();
        String url;
        String pathFileOut;
        int i;
        GPSPoint point;

        try {
            // extract point from file
            ArrayList<GPSPoint> pointArray = GPSPointExtractor.extractFromFile(pathFile);
            view.appendOutputProcess(pointArray.size() + " point extracted");
//            String fileName = MyFile.getFileName(pathFile);
//            System.out.println(fileName);
            String fileName = "test";

            // get request for each point
            for (i = 0; i < pointArray.size(); i++) {
                point = pointArray.get(i);
                view.appendOutputProcess("begin send request...");
                ClientRequest client = new ClientRequest();
                url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + point.getLat() + "," + point.getLng() + "&radius=50&key=AIzaSyAxT_lAlVf9oKHq1aCw47Qt0SJXnRWJbbs";
                String json = client.request(url);
                NearBySearchResult obj = gson.fromJson(json, NearBySearchResult.class);

                //write to file
                pathFileOut = "output_nearbyplace/" + fileName + "/" + i + ".txt";
                view.appendOutputProcess("write to file " + pathFileOut);

                for (Result result : obj.getResults()) {
                    MyFile.writeToFile(pathFileOut,
                            result.getName() + "," + result.getTypes() + "\n");
                }
            }
            view.appendOutputProcess("get result success!");

        } catch (IOException | NumberFormatException ex) {
            view.appendOutputProcess("error reading file ");
            ex.printStackTrace();
        }

    }

}

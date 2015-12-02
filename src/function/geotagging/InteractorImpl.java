/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.geotagging;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.GPSPoint;
import model.StayPoint;
import model.place_api.Result;
import utils.GPSPointExtractor;
import utils.file.MyFile;

/**
 *
 * @author trungtran.vn
 */
public class InteractorImpl implements Interactor {

    @Override
    public StayPoint extractPointsFromFile(String path) throws Exception {
        ArrayList<GPSPoint> pointArray = GPSPointExtractor.extractFromFile(path);
        StayPoint sp = new StayPoint();
        sp.setArr(pointArray);
        sp.computeAvgCoordinate();
        return sp;
    }

    /**
     * vói mỗi point, function sẽ tìm kiếm tập điểm lân cận cho điểm đó. sau đó
     * lấy giao các tập điểm để tìm điểm chung. nếu tập giao là rỗng, thì tăng
     * vùng tìm kiếm, và tính toán lại. (vòng lặp while)
     *
     * @param pointArray
     * @return tập điểm giao nhau như là kết quả của nearbySearch
     */
    @Override
    public ArrayList<Result> findNearbyPlace(StayPoint sp) {
        int i;
        int radius = 0;    // default radius (m)
        Set<Result> intersection = new HashSet<>();

        // nếu ko tìm thấy giao điểm, thử mở rộng phạm vi
        while (true) {
            radius += 10;
            System.out.println("----findNearbyPlace radius = " + radius + "-------");
            if (radius > 100) {
                break;
            }
            intersection = new HashSet<>();
            List<Result> results = NearbyPlaceAPI.requestNearbyPlace(sp.getAvgCoordinate(), radius);
            intersection = new HashSet(results);
            // với mỗi điểm, tìm tập địa điểm lân cận, rồi lấy giao 
            for (i = 0; i < sp.getArr().size(); i++) {
                GPSPoint point = sp.getArr().get(i);
                results = NearbyPlaceAPI.requestNearbyPlace(point, radius);
                intersection.retainAll(results);  // lấy giao 2 tập hợp
                if (intersection.isEmpty()) {
                    break;   // thử lại với phạm vi lớn hơn
                }
            }
            System.out.println("--in while --");
            for (Result r : intersection) {
                System.out.println(r.getName());
            }
            if (!intersection.isEmpty()) {
                break;
            }

        }
        return new ArrayList<Result>(intersection);
    }

    public static void main(String[] args) {
        try {
            InteractorImpl inter = new InteractorImpl();

            String path = "D:\\NetbeansProjects\\GR2\\output\\366_02_30_600\\lists\\0.txt";
            StayPoint sp = inter.extractPointsFromFile(path);
            ArrayList<Result> results = inter.findNearbyPlace(sp);
            System.out.println("-----------------------------------------------------\n" + results.size());
            inter.writeOutFile(results, path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void writeOutFile(ArrayList<Result> rs, String inputPathFile) throws Exception {
        String fileName = MyFile.getFileName(inputPathFile) + "_nearbyPlaces.txt";

        // write to centroid file
        // file[id,name,types]
        for (Result r : rs) {
            String content = "";
            for (String s : r.getTypes()) {
                content = content + "," + s;
            }
            content = r.getId() + "," + r.getName() + content + "\n";
            MyFile.writeToFile(fileName, content);
        }
    }

}

package function.staypoint;

import java.util.ArrayList;
import java.util.List;
import model.Coordinate;
import model.GPSPoint;
import model.StayPoint;
import model.dao.GPSPointDAO;
import utils.GPSPointExtractor;

public class StayPointCalculator2 {

    private double distThresh = 20;     // met
    private long timeThresh = 300;      // second

    public StayPointCalculator2(double dis, long time) {
        this.distThresh = dis;
        this.timeThresh = time;
    }

    /**
     *
     * @param arrLog list of GPSPoint objects
     * @return list of StayPoint
     */
    public ArrayList<StayPoint> extractStayPoints(List<GPSPoint> arrLog) {
        int i = 0, k, j, token, pointNum;
        double dist;
        long deltaTime;
        GPSPoint pi, pj, pk;
        pointNum = arrLog.size();
        // list of stay point
        ArrayList<StayPoint> arrStayPoints = new ArrayList<>();

        // duyệt tất cả point
        while (i < pointNum - 1) {
            j = i + 1;
            token = 0;
            pi = arrLog.get(i);
            while (j < pointNum) {
                pj = arrLog.get(j);
                dist = Coordinate.distance(pi, pj);
                // dừng và lưu SP nếu vượt khoảng cách hoặc duyệt hết list
                if ((dist > distThresh) || (j == pointNum - 1)) {
                    k = j - 1;
                    pk = arrLog.get(k);
                    deltaTime = GPSPoint.interval(pi.getTime(), pk.getTime());
                    // dừng và lưu SP nếu vượt thời gian hoặc duyệt hết list
                    if ((deltaTime > timeThresh) || (j == pointNum - 1)) {
                        StayPoint sp = new StayPoint();
                        for (k = i; k <= j - 1; k++) {
                            // chỉ add thêm mới, nhằm giảm số request khi geotaging
                            // @see GPSPoint equal()
                            if (!sp.getArr().contains(arrLog.get(k))) {
                                sp.getArr().add(arrLog.get(k));
                            }
                        }
                        sp.setStartTime(pi.getTime());
                        sp.setEndTime(pk.getTime());

                        // tính trọng tâm của SP
                        sp.computeAvgCoordinate();
                        // thêm SP này vào list kết quả
                        arrStayPoints.add(sp);
                        i = j;
                        token = 1;
                    }
                    break;
                }
                j++;
            }
            if (token != 1) {
                i++;
            }
        }
        return arrStayPoints;
    }

    public double getDistThresh() {
        return distThresh;
    }

    public void setDistThresh(double distThresh) {
        this.distThresh = distThresh;
    }

    public long getTimeThresh() {
        return timeThresh;
    }

    public void setTimeThresh(long timeThresh) {
        this.timeThresh = timeThresh;
    }

    public static void main(String[] args) throws Exception {
        List<GPSPoint> points = GPSPointExtractor.extractFromFile("D:\\GR\\Geolife Trajectories 1.3\\Geolife Trajectories 1.3\\Data\\000\\Trajectory\\20081112091400.plt");
        
//        GPSPointDAO dao = new GPSPointDAO();
//        List<GPSPoint> points = dao.selectByDate("2008-11-11");
        
        StayPointCalculator2 sp = new StayPointCalculator2(30, 600);
        List<StayPoint> staypoints = sp.extractStayPoints(points);
        for (StayPoint s : staypoints) {
            System.out.println(s.toString());
            for (GPSPoint p : s.getArr()) {
                System.out.println("\t" + p.toString());
            }
        }
    }
}

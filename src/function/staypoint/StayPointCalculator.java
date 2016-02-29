package function.staypoint;

import java.util.ArrayList;
import java.util.List;
import model.Coordinate;
import model.GPSPoint;
import model.StayPoint;
import utils.GPSPointExtractor;

public class StayPointCalculator {

    private double distThresh = 30;     // met
    private long timeThresh = 600;       // second

    public StayPointCalculator(double dis, long time) {
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
        GPSPoint pi, pj;
        pointNum = arrLog.size();
        // list of stay point
        ArrayList<StayPoint> arrStayPoints = new ArrayList<>();

        // duyệt tất cả point
        while (i < pointNum) {
            pi = arrLog.get(i);

            j = i + 1;
            token = 0;      // flag (0: not sp, 1: is sp)

            //khởi tạo SP
            StayPoint sp = new StayPoint();
            k = j;  // điểm cuối của SP

            // chọn điểm đầu tiên là i, lần lượt tìm các điểm j tiếp theo của SP
            while (j < pointNum) {
                pj = arrLog.get(j);
                dist = Coordinate.distance(pi, pj);

                // check condition of distance 
                if (dist < distThresh) {
                    token = 1;
                    k = j;
                } // kết thúc duyệt j
                else {
                    break;
                }
                j++;
            }

            // kiểm tra điều kiện thời gian
            if (token == 1) {
                // lấy điểm cuối của SP
                pj = arrLog.get(k);
                // tính khoảng thời gian của SP
                deltaTime = GPSPoint.interval(pi.getTime(), pj.getTime());

                if (deltaTime > timeThresh) {
                    int l;
                    for (l = i; l <= k; l++) {
                        if (!sp.getArr().contains(arrLog.get(l))) {
                            sp.getArr().add(arrLog.get(l));
                        }
                    }
                    sp.setStartTime(pi.getTime());
                    sp.setEndTime(pj.getTime());

                    // tính trọng tâm của SP
                    sp.computeAvgCoordinate();
                    // thêm SP này vào list kết quả
                    arrStayPoints.add(sp);
                }
                i = k;
            }
            i++;
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
        List<GPSPoint> points = GPSPointExtractor.extractFromFile("D:\\NetbeansProjects\\GR2\\resource\\366_02.txt");
        System.out.println(points.size());
        System.out.println(points.get(1));
        StayPointCalculator sp = new StayPointCalculator(30, 1200);
        List<StayPoint> staypoints = sp.extractStayPoints(points);
        for (StayPoint s : staypoints) {
            System.out.println(s.toString());
            for (GPSPoint p : s.getArr()) {
                System.out.println("\t" + p.toString());
            }
        }
    }
}

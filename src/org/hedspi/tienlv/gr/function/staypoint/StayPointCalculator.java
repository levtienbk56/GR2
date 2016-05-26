package org.hedspi.tienlv.gr.function.staypoint;

import java.util.ArrayList;
import java.util.List;
import org.hedspi.tienlv.gr.model.Coordinate;
import org.hedspi.tienlv.gr.model.GPSPoint;
import org.hedspi.tienlv.gr.model.StayPoint;
import org.hedspi.tienlv.gr.utils.GPSPointExtractor;

/**
 * add one point at last
 * @author Lev Tien
 */
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

        // insert one point at last array
        arrLog.add(new GPSPoint(0.0, 0.0, "2999-01-01 00:00:00"));
        pointNum = arrLog.size();

        // list of stay point
        ArrayList<StayPoint> SP = new ArrayList<>();

        while (i < pointNum - 1) {
            pi = arrLog.get(i);
            j = i + 1;
            token = 0;
            while (j < pointNum) {
                pj = arrLog.get(j);
                dist = Coordinate.distance(pi, pj);

                if (dist > distThresh) {
                    // tính khoảng thời gian của SP
                    deltaTime = GPSPoint.interval(pi.getTime(), pj.getTime());
                    if (deltaTime > timeThresh) {
                        int l;
                        StayPoint S = new StayPoint();
                        for (l = i; l < j; l++) {
                            // @see contains()
                            if (!S.getArr().contains(arrLog.get(l))) {
                                S.getArr().add(arrLog.get(l));
                            }
                        }
                        S.setStartTime(pi.getTime());
                        S.setEndTime(pj.getTime());

                        // tính trọng tâm của SP
                        S.computeAvgCoordinate();
                        // thêm SP này vào list kết quả
                        SP.add(S);
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
        return SP;
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
        List<GPSPoint> points = GPSPointExtractor.extractFromFile("D:\\NetbeansProjects\\GR2\\input\\temp.txt");
        StayPointCalculator sp = new StayPointCalculator(30, 1200);

        List<StayPoint> staypoints = sp.extractStayPoints(points);
        System.out.println("there are " + staypoints.size() + " points");
        for (StayPoint s : staypoints) {
            System.out.println("---");
            System.out.println(s.toString());
            for (GPSPoint p : s.getArr()) {
                System.out.println("\t" + p.toString());
            }
        }
    }
}

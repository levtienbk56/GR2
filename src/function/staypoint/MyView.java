/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.staypoint;

/**
 *
 * @author trungtran.vn
 */
public interface MyView {
    String getInputFilePath();
    void setInputFilePath();
    String getOutputFileName();
    int getDisThresh();
    int getTimeThresh();
    void appendOutputProcess();
    void setOutputProcess();
}

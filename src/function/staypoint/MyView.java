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

    String getInputFilePath() throws Exception;

    void setInputFilePath(String content);

    int getDisThresh() throws Exception;

    int getTimeThresh() throws Exception;

    void appendOutputProcess(String content);

    void setOutputProcess(String content);
}

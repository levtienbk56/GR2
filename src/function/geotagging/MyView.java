/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.geotagging;

import java.util.List;

/**
 *
 * @author trungtran.vn
 */
public interface MyView {
    String getInputFilePath() throws Exception;
    List<String> getInputFolderPath() throws Exception;
    void appendOutputProcess(String log);
    void setOutputProcess(String content);
}

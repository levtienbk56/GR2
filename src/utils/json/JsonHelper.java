/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.json;

/**
 *
 * @author trungtran.vn
 */
public class JsonHelper {

    private JsonParser parser;

    public JsonHelper() {
        parser = new DefaultParser();
    }

    public JsonHelper(JsonParser parser) {
        this.parser = parser;
    }

    public String parse(String s) {
        return parser.parse(s);
    }

    static class DefaultParser implements JsonParser {

        @Override
        public String parse(String s) {

            return null;
        }

    }
}

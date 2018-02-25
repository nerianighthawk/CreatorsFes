package jp.co.unirita.creatorsfes.teamc.model;

import java.util.Map;
import java.util.TreeMap;

public class MasterData {

    private Map<String, String> map;

    public MasterData() {
        map = new TreeMap<>();
    }

    @Override
    public String toString() {
    	return map.toString();
    }

    public String getParam(String key) {
        return map.get(key);
    }

    public void setParam(String key, String value) {
        this.map.put(key, value);
    }

}

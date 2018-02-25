package jp.co.unirita.creatorsfes.teamc.model;

import java.util.HashMap;
import java.util.Map;

public class MasterData {

    private Map<String, String> map;

    public MasterData() {
        map = new HashMap<>();
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

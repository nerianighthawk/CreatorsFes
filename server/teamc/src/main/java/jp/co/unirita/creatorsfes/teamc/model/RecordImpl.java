package jp.co.unirita.creatorsfes.teamc.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RecordImpl implements Record {

    private Map<String, String> map = new TreeMap<>();

    public RecordImpl() {
    }

    @Override
    public String toString() {
    	return map.toString();
    }
    
    public Set<String> keySet() {
    	return map.keySet();
    }

    public Map<String, String> getParams() {
        return map;
    }

    public String getParam(String key) {
        return map.get(key);
    }

    public int getParamAsInt(String key) {
        return Integer.parseInt(getParam(key));
    }

    public void setParam(String key, String value) {
        this.map.put(key, value);
    }

    public boolean hasParam(String key) {
        return map.containsKey(key);
    }
}

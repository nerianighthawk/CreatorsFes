package jp.co.unirita.creatorsfes.teamc.model;

import java.util.Set;

public interface Record {
	public Set<String> keySet();
    public String getParam(String key);
    public int getParamAsInt(String key);

}

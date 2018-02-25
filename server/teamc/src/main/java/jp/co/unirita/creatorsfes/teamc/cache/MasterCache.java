package jp.co.unirita.creatorsfes.teamc.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.unirita.creatorsfes.teamc.model.Master;
import jp.co.unirita.creatorsfes.teamc.util.FileUtil;

public class MasterCache {

    private static Map<String, List<Master>> masterMap = new HashMap<>();

    public static List<Master> getMasterList(String masterName) throws Exception {
    	List<Master> list = masterMap.get(masterName);
        if (list != null) {
            return list;
        }
        list = FileUtil.loadMasterData(masterName);
        masterMap.put(masterName, list);
        return list;
    }
}

package jp.co.unirita.creatorsfes.teamc.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.unirita.creatorsfes.teamc.model.MasterData;
import jp.co.unirita.creatorsfes.teamc.util.FileUtil;

public class MasterCache {

    private static Map<String, List<MasterData>> masterMap = new HashMap<>();

    public static List<MasterData> getMasterList(String masterName) throws Exception {
    	List<MasterData> list = masterMap.get(masterName);
        if (list != null) {
            return list;
        }
        list = FileUtil.loadMasterData(masterName);
        masterMap.put(masterName, list);
        return list;
    }
}

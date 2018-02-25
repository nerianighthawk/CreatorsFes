package jp.co.unirita.creatorsfes.teamc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.unirita.creatorsfes.teamc.cache.MasterCache;
import jp.co.unirita.creatorsfes.teamc.model.Master;
import jp.co.unirita.creatorsfes.teamc.model.MasterData;

@Service
public class MasterService {

    public List<Master> execute(String masterName) throws Exception {
    	List<Master> list = new ArrayList<>();
    	List<MasterData> srcList = MasterCache.getMasterList(masterName);
    	String idKey = masterName + "Id";
    	String nameKey = masterName + "Name";
    	for (MasterData data : srcList) {
    		list.add(new Master(data.getParam(idKey), data.getParam(nameKey)));
    	}
    	return list;
    }
}

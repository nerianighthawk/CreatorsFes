package jp.co.unirita.creatorsfes.teamc.util.axis;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.unirita.creatorsfes.teamc.cache.MasterCache;
import jp.co.unirita.creatorsfes.teamc.model.MasterData;
import jp.co.unirita.creatorsfes.teamc.model.Record;

public class CompanyAxis extends Axis {
    public static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Map<String, String> map = new HashMap<>();
	
	public CompanyAxis() {
        super("companyId");
        try {
        	List<MasterData> list = MasterCache.getMasterList("department");
			for (MasterData data : list) {
				map.put(data.getParam("departmentId"), data.getParam("companyId"));
			}
		} catch (Exception e) {
			logger.error("Error in CompanyAxis()", e);
		}
	}

	@Override
	public void classify(Record record) {
        String depId = record.getParam("departmentId");
        String compId = map.get(depId);
        if (compId == null) {
        	compId = "(not found)";
        }
        record.setParam(getAxisName(), compId);
	}

}

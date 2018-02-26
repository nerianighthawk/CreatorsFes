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

public class DepartmentAxis extends Axis {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Map<String, String> map = new HashMap<>();
	
	public DepartmentAxis() {
        super("departmentId");
        try {
        	List<MasterData> list = MasterCache.getMasterList("user");
			for (MasterData data : list) {
				map.put(data.getParam("userId"), data.getParam("departmentId"));
			}
		} catch (Exception e) {
			logger.error("Error in DepartmentAxis()", e);
		}
	}

	@Override
    public void classify(Record record) {
        String userId = record.getParam("userId");
        String depId = map.get(userId);
        if (depId == null) {
        	depId = "(not found)";
        }
        super.setParam(record, getAxisName(), depId);
	}

}

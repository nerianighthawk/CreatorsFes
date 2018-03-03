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

public class GenderAxis extends Axis {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Map<String, String> map = new HashMap<>();
	
	public GenderAxis() {
        super("gender");
        try {
        	List<MasterData> list = MasterCache.getMasterList("user");
			for (MasterData data : list) {
				map.put(data.getParam("userId"), data.getParam("gender"));
			}
		} catch (Exception e) {
			logger.error("Error in GenderAxis()", e);
		}
	}

	@Override
    public void classify(Record record) {
        String userId = record.getParam("user");
        String gender = map.get(userId);
        if (gender == null) {
        	gender = "(not found)";
        }
        super.setParam(record, getAxisName(), gender);
	}

}

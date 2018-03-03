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

public class AgeAxis extends Axis {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Map<String, String> map = new HashMap<>();
	
	public AgeAxis() {
        super("age");
        try {
        	List<MasterData> list = MasterCache.getMasterList("user");
			for (MasterData data : list) {
				map.put(data.getParam("userId"), data.getParam("birthAge"));
			}
		} catch (Exception e) {
			logger.error("Error in GenderAxis()", e);
		}
	}

	@Override
    public void classify(Record record) {
        String userId = record.getParam("user");
        int age = Integer.parseInt(map.get(userId));
        String str;
        if (age >= 60) {
        	str = "60";
        } else if (age >= 50) {
        	str = "50";
        } else if (age >= 40) {
        	str = "40";
        } else if (age >= 30) {
        	str = "30";
        } else {
        	str = "20";
        }
        super.setParam(record, getAxisName(), str);
	}

}

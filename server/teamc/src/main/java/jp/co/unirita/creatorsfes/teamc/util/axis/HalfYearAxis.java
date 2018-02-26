package jp.co.unirita.creatorsfes.teamc.util.axis;

import jp.co.unirita.creatorsfes.teamc.model.Record;

public class HalfYearAxis extends Axis {

    public HalfYearAxis() {
        super("halfYearId");
    }

    @Override
    public void classify(Record record) {
        int month = record.getParamAsInt("month");
        String halfId = "(not found)";
        if (4 <= month && month <= 9) {
        	halfId = "1";
        } else {
        	halfId = "2";
        }
        super.setParam(record, getAxisName(), halfId);
    }
}

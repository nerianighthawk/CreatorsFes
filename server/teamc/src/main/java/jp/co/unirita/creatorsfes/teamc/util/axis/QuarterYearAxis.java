package jp.co.unirita.creatorsfes.teamc.util.axis;

import jp.co.unirita.creatorsfes.teamc.model.Record;

public class QuarterYearAxis extends Axis {

    public QuarterYearAxis() {
        super("quarter");
    }

    @Override
    public void classify(Record record) {
        int month = record.getParamAsInt("month");
        String quarterId = "(not found)";
        if (4 <= month && month <= 6) {
        	quarterId = "1";
        } else if(7 <= month && month <= 9) {
        	quarterId = "2";
        } else if(10 <= month && month <= 12) {
        	quarterId = "3";
        } else {
        	quarterId = "4";
        }
        super.setParam(record, getAxisName(), quarterId);
    }
}

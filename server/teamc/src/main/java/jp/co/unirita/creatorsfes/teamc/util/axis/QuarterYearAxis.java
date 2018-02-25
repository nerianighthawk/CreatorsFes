package jp.co.unirita.creatorsfes.teamc.util.axis;

import jp.co.unirita.creatorsfes.teamc.model.Record;

public class QuarterYearAxis extends Axis {

    public QuarterYearAxis() {
        super("quarterYearId");
    }

    @Override
    public void classify(Record record) {
        int month = record.getParamAsInt("month");
        if(4 <= month && month <= 6) {
            record.setParam(getAxisName(), "1");
        }else if(7 <= month && month <= 9) {
            record.setParam(getAxisName(), "2");
        }else if(10 <= month && month <= 12) {
            record.setParam(getAxisName(), "3");
        }else {
            record.setParam(getAxisName(), "4");
        }
    }
}

package jp.co.unirita.creatorsfes.teamc.util.axis;

import jp.co.unirita.creatorsfes.teamc.model.Record;

public class MonthAxis extends Axis {

    public MonthAxis() {
        super("month");
    }

    @Override
    public void classify(Record record) {
        String date = record.getParam("date");
        int month = Integer.parseInt(date.substring(4, 6));
        super.setParam(record, getAxisName(), String.valueOf(month));
    }
}

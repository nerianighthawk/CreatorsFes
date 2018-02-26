package jp.co.unirita.creatorsfes.teamc.util.axis;

import jp.co.unirita.creatorsfes.teamc.model.Record;

public class YearAxis extends Axis {

    public YearAxis() {
        super("year");
    }

    @Override
    public void classify(Record record) {
        String date = record.getParam("date");
        int year = Integer.parseInt(date.substring(0, 4));
        super.setParam(record, getAxisName(), String.valueOf(year));
    }
}

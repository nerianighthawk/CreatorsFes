package jp.co.unirita.creatorsfes.teamc.util.axis;

import jp.co.unirita.creatorsfes.teamc.model.Record;
import jp.co.unirita.creatorsfes.teamc.model.RecordImpl;

public class YearAxis extends Axis {

    public YearAxis() {
        super("year");
    }

    @Override
    public void classify(Record _record) {
    	RecordImpl record = (RecordImpl)_record;
        String date = record.getParam("date");
        int year = Integer.parseInt(date.substring(0, 4));
        record.setParam(getAxisName(), String.valueOf(year));
    }
}

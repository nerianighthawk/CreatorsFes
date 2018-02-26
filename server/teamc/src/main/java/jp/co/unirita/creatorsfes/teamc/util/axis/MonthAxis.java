package jp.co.unirita.creatorsfes.teamc.util.axis;

import jp.co.unirita.creatorsfes.teamc.model.Record;
import jp.co.unirita.creatorsfes.teamc.model.RecordImpl;

public class MonthAxis extends Axis {

    public MonthAxis() {
        super("month");
    }

    @Override
    public void classify(Record _record) {
    	RecordImpl record = (RecordImpl)_record;
        String date = record.getParam("date");
        int month = Integer.parseInt(date.substring(4, 6));
        record.setParam(getAxisName(), String.valueOf(month));
    }
}

package jp.co.unirita.creatorsfes.teamc.util.axis;

import jp.co.unirita.creatorsfes.teamc.model.Record;
import jp.co.unirita.creatorsfes.teamc.model.RecordImpl;

public class OverTimeMinutesAxis extends Axis {

    public OverTimeMinutesAxis() {
        super("overtimeMinutes");
    }

    @Override
    public void classify(Record _record) {
    	RecordImpl record = (RecordImpl)_record;
        String[] attend = record.getParam("attendTime").split(":");
        String[] leave = record.getParam("leaveTime").split(":");
        int attendTime = Integer.parseInt(attend[0]) * 60 + Integer.parseInt(attend[1]);
        int leaveTime = Integer.parseInt(leave[0]) * 60 + Integer.parseInt(leave[1]);

        record.setParam(getAxisName(), String.valueOf(Math.max(0, leaveTime - attendTime - 540)));
    }
}

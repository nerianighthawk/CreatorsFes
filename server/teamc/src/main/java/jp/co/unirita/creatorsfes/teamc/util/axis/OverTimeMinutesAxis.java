package jp.co.unirita.creatorsfes.teamc.util.axis;

import jp.co.unirita.creatorsfes.teamc.model.Record;

public class OverTimeMinutesAxis extends Axis {

    public OverTimeMinutesAxis() {
        super("overtimeMinutes");
    }

    @Override
    public void classify(Record record) {
        String[] attend = record.getParam("attendTime").split(":");
        String[] leave = record.getParam("leaveTime").split(":");
        int attendTime = Integer.parseInt(attend[0]) * 60 + Integer.parseInt(attend[1]);
        int leaveTime = Integer.parseInt(leave[0]) * 60 + Integer.parseInt(leave[1]);

        super.setParam(record, getAxisName(), String.valueOf(Math.max(0, leaveTime - attendTime - 540)));
    }
}

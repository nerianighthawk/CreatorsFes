package jp.co.unirita.creatorsfes.teamc.util.axis;

import java.util.List;

import jp.co.unirita.creatorsfes.teamc.model.Record;
import jp.co.unirita.creatorsfes.teamc.model.RecordImpl;
import lombok.Getter;

@Getter
public abstract class Axis {
    private String axisName;

    public Axis(String name) {
        this.axisName = name;
    }

    public abstract void classify(Record record);
    
    public void classify(List<Record> records){
        records.forEach(this::classify);
    }

    protected void setParam(Record _record, String key, String value) {
    	RecordImpl record = (RecordImpl)_record;
    	record.setParam(key, value);
    }

}

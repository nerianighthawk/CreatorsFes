package jp.co.unirita.creatorsfes.teamc.util.axis;


import jp.co.unirita.creatorsfes.teamc.model.record.Record;
import lombok.Getter;

import java.util.List;

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
}

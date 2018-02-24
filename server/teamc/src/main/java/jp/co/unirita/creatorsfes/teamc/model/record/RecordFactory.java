package jp.co.unirita.creatorsfes.teamc.model.record;

import jp.co.unirita.creatorsfes.teamc.util.FileUtil;

import java.util.List;

public class RecordFactory {

    private static List<Record> recordList = null;

    public static List<Record> getRecordList() throws Exception {
        if(recordList != null) {
            return recordList;
        }
        recordList = FileUtil.loadRecordList("data/record.csv");
        return recordList;
    }
}

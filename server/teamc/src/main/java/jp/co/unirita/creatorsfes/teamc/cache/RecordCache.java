package jp.co.unirita.creatorsfes.teamc.cache;

import java.util.List;

import jp.co.unirita.creatorsfes.teamc.model.Record;
import jp.co.unirita.creatorsfes.teamc.util.FileUtil;

public class RecordCache {

    private static List<Record> recordList = null;

    public static List<Record> getRecordList() throws Exception {
        if(recordList != null) {
            return recordList;
        }
        recordList = FileUtil.loadRecordList();
        return recordList;
    }
}

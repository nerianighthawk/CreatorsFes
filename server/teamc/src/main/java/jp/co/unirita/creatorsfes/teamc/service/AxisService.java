package jp.co.unirita.creatorsfes.teamc.service;

import jp.co.unirita.creatorsfes.teamc.cache.RecordCache;
import jp.co.unirita.creatorsfes.teamc.model.RecordImpl;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AxisService {

    public Set<String> execute() throws Exception {
        return ((RecordImpl)RecordCache.getRecordList().get(0)).keySet();
    }
}

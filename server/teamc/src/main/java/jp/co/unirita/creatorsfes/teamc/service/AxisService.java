package jp.co.unirita.creatorsfes.teamc.service;

import jp.co.unirita.creatorsfes.teamc.cache.RecordCache;
import jp.co.unirita.creatorsfes.teamc.model.RecordImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AxisService {

    public Set<String> getAxes() throws Exception {
        return ((RecordImpl)RecordCache.getRecordList().get(0)).keySet();
    }

    public List<String> getValues(String axis) throws Exception {
        Set<String> axes = getAxes();
        if(!axes.contains(axis)) {
            throw new RuntimeException("axis " + axis + " is not found.");
        }
        return RecordCache.getRecordList().stream()
                .map(r -> r.getParam(axis))
                .distinct().sorted().collect(Collectors.toList());
    }
}

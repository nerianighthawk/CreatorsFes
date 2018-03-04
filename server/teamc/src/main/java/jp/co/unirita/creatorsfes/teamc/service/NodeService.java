package jp.co.unirita.creatorsfes.teamc.service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jp.co.unirita.creatorsfes.teamc.cache.RecordCache;
import jp.co.unirita.creatorsfes.teamc.model.NodeData;

@Service
public class NodeService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public NodeData execute(Map<String, String> params, boolean isContainRecord) throws Exception {
        NodeData root = new NodeData("ROOT", null);
        root.setRecords(RecordCache.getRecordList());

        checkKeyRegex(params.keySet());
        int count = 0;
        while(params.size() > 0) {
            if(count > 9){
                throw new RuntimeException("NAZO");
            }
            root.nextAxis();
            List<String> list = new ArrayList<>();
            for(String key: params.keySet()){
                if(key.startsWith("axis" +  count)){
                    list.add(key);
                }
            }
            list.sort(Comparator.naturalOrder());
            logger.info("[execute] axis" + count + " =  " + String.join(". ", list));
            for(String key: list){
                root.addAxis(params.get(key));
            }
            list.forEach(params::remove);
            count++;
        }
        root.close(isContainRecord);
        return root;
    }

    public Map<String, List<Double>> pileUp(int set, Map<String, String> params) throws Exception {
        checkParams(params);
        params.put("axis10", "month");
        NodeData root = execute(params, false);

        Map<String, List<Double>> result = new TreeMap<>();
        Map<String, NodeData> children = root.getChildren();
        for (String key : children.keySet()) {
            if(key.startsWith("month")){
                continue;
            }
            Map<String, NodeData> data = children.get(key).getChildren();
            List<Double> list = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                int month = (3 + i) % 12 + 1;
                double average = data.get(String.format("month:%02d", month)).getResult().getAverage();
                if(i % set > 0){
                    average += list.get(i-1);
                }
                list.add(average);
            }
            result.put(key, list);
        }
        return result;
    }

    private void checkKeyRegex(Set<String> keySet) {
        keySet.forEach(key -> {
            if(!key.matches("^axis[0-9]{2}$")){
                throw new RuntimeException("invalid key regex. key = " + key);
            }
        });
    }

    private void checkParams(Map<String, String> params) {
        if(params.size() > 3) {
            throw new RuntimeException("too many params. size = " + params.size());
        }
        params.keySet().forEach(key -> {
            if(!key.matches("^axis0[0-2]$")){
                throw new RuntimeException("invalid key regex. key = " + key);
            }
        });
        params.keySet().forEach(key -> {
            if(!params.get(key).contains(":")) {
                throw new RuntimeException("invalid param regex. value = " + params.get(key));
            }
        });
    }
}

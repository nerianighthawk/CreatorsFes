package jp.co.unirita.creatorsfes.teamc.service;

import jp.co.unirita.creatorsfes.teamc.model.Node;
import jp.co.unirita.creatorsfes.teamc.model.record.RecordFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NodeService {

    public Node execute(Map<String, String> params) throws Exception {
        Node root = new Node("ROOT");
        root.setRecords(RecordFactory.getRecordList());
        boolean isContainRecord = Boolean.valueOf(params.get("getRecord"));
        params.remove("getRecord");

        checkKeyRegex(params.keySet());
        int count = 0;
        while(params.size() > 0) {
            if(count > 9){
                throw new RuntimeException("NAZO");
            }
            List<String> list = new ArrayList<>();
            for(String key: params.keySet()){
                if(key.startsWith("axis" +  count)){
                    list.add(key);
                }
            }
            list.sort((s1, s2) -> s1.compareTo(s2));
            System.out.println("axis " + String.join(". ", list));
            for(String key: list){
                root.addAxis(params.get(key));
            }
            list.forEach(params::remove);
            root.nextAxis();
            count++;
        }
        root.close(isContainRecord);
        return root;
    }

    private void checkKeyRegex(Set<String> keySet) {
        keySet.forEach(key -> {
            if(!key.matches("^axis[0-9]{2}$")){
                throw new RuntimeException("invalid key regex. key = " + key);
            }
        });
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("axis00");
        set.add("axis67");
        set.add("axis99");
        new NodeService().checkKeyRegex(set);
    }
}

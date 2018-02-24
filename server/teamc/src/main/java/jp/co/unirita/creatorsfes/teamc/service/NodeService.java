package jp.co.unirita.creatorsfes.teamc.service;

import jp.co.unirita.creatorsfes.teamc.model.Node;
import jp.co.unirita.creatorsfes.teamc.model.record.RecordFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NodeService {

    public Node execute(Map<String, String> params) throws Exception {
        Node root = new Node("ROOT");
//        root.setRecords(RecordFactory.getRecordList());
//        root.addAxis("month:01");
//        root.nextAxis();
//        root.addAxis("departmentId:d00000");
//        root.nextAxis();
//        root.addAxis("userId");
//        root.close();
        return root;
    }
}

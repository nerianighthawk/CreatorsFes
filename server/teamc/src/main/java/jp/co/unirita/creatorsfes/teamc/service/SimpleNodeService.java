package jp.co.unirita.creatorsfes.teamc.service;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.unirita.creatorsfes.teamc.model.BranchNode;
import jp.co.unirita.creatorsfes.teamc.model.LeafNode;
import jp.co.unirita.creatorsfes.teamc.model.Node;
import jp.co.unirita.creatorsfes.teamc.model.NodeData;

@Service
public class SimpleNodeService {
    @Autowired
    NodeService nodeService;

    public Node execute(Map<String, String> params) throws Exception {
    	NodeData root = nodeService.execute(params, false);
    	return convert(root);
    }

    private Node convert(NodeData node) {
    	if (node.getChildren() != null) {
        	BranchNode branch = new BranchNode(node);
        	for (String key : node.getChildren().keySet()) {
        		branch.getChildren().put(key, convert(node.getChildren().get(key)));
        	}
        	return branch;
    	} else {
    		LeafNode leaf = new LeafNode(node);
        	return leaf;
    	}
    }

}

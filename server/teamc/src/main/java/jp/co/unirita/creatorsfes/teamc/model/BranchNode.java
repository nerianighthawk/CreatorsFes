package jp.co.unirita.creatorsfes.teamc.model;

import java.util.Map;
import java.util.TreeMap;

import lombok.Data;

@Data
public class BranchNode extends Node {
    private Map<String, Node> children = new TreeMap<>();

    public BranchNode(NodeData node) {
    	super(node);
    }
}

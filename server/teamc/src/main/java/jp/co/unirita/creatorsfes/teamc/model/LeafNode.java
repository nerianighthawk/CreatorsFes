package jp.co.unirita.creatorsfes.teamc.model;

import lombok.Data;

@Data
public class LeafNode extends Node {

	public LeafNode(NodeData node) {
		super(node);
	}
}

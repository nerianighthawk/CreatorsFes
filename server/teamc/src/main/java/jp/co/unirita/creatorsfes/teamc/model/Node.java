package jp.co.unirita.creatorsfes.teamc.model;

import lombok.Data;

@Data
public class Node {
    // データ件数
    private int count;
    // 合計
    private int sum;
    //平均
    private double average;
    // 標準偏差
    private double standardDeviation;
    
	public Node(NodeData node) {
		this.count = node.getResult().getCount();
		this.sum = node.getResult().getSum();
		this.average = node.getResult().getAverage();
		this.standardDeviation = node.getResult().getStandardDeviation();
	}

}

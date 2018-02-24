package jp.co.unirita.creatorsfes.teamc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnalysisResult {

    // データ件数
    private int count;

    // 合計
    private int sum;

    //平均
    private double average;

    // 偏差
    private double deviation;

    // 分散
    private double dispersion;

    // 標準偏差
    private double standardDeviation;
}

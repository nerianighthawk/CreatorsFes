package jp.co.unirita.creatorsfes.teamc.model;

import jp.co.unirita.creatorsfes.teamc.model.record.Record;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Node {

    private String value;
    private Map<String, Node> children = null;
    private List<Record> records;
    private List<String> axis;
    private AnalysisResult result;

    public Node(String value) {
        this.value = value;
        this.axis = new ArrayList<>();
        this.records = new ArrayList<>();
        result = new AnalysisResult();
    }

    public void addAxis(String columnName) {
        if(children == null) {
            axis.add(columnName);
        } else {
            children.keySet().forEach(key -> children.get(key).addAxis(columnName));
        }
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public void nextAxis() {
        if(children != null){
            children.keySet().forEach(key -> children.get(key).nextAxis());
        } else {
            children = new HashMap<>();
            List<Record> tmp = new ArrayList<>();

            for (Record record : records) {
                boolean use = false;
                for (String key : axis) {
                    if (key.contains(":")) {
                        String[] values = key.split(":");
                        if (record.getParam(values[0]).equals(values[1])) {
                            addChild(key, record);
                            use = true;
                        }
                    } else {
                        addChild(key, record);
                        use = true;
                    }
                }
                if (!use) {
                    tmp.add(new Record(record));
                }
            }
            records = new ArrayList<>();
            tmp.forEach(records::add);
        }
    }

    public void close() {
        calc();
    }

    private void addChild(String key, Record record) {
        if(children.containsKey(key)) {
            children.get(key).addRecord(new Record(record));
        } else {
            Node node = new Node(record.getParam(key));
            node.addRecord(new Record(record));
            children.put(key, node);
        }
    }

    private void calc() {
        if(children == null) {
            calcLeaf();
        }
        int sum = 0, count = 0;
        for(String key: children.keySet()) {
            sum += children.get(key).getResult().getSum();
            count += children.get(key).getResult().getCount();
        }
        double average = sum / (double)count;
        result.setCount(count);
        result.setSum(sum);
        result.setAverage(average);

        children.keySet().stream()
                .map( key -> children.get(key).getResult())
                .forEach(result -> {
                    // TODO 偏差と分散と標準偏差を求める
                });
    }

    public void calcLeaf() {
        int sum = 0, count = 0;
        for(Record record: records) {
            sum += record.getParamAsInt("overtimeMinutes");
            count++;
        }
        double average = sum / (double)records.size();
        result.setCount(count);
        result.setSum(sum);
        result.setAverage(average);
    }
}

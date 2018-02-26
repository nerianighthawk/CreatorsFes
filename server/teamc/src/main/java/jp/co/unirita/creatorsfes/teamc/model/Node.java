package jp.co.unirita.creatorsfes.teamc.model;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class Node {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private String name;
    private String value;
    private Map<String, Node> children = null;
    private List<Record> records;
    private List<String> axis;
    private AnalysisResult result;

    public Node(String name, String value) {
        this.name = name;
        this.value = value;
        logger.info("[Node] new node. name = " + name + ", value = " + value);

        this.axis = new ArrayList<>();
        this.records = new ArrayList<>();
        result = new AnalysisResult();
    }

    public void addAxis(String columnName) {
        if(children != null) {
            logger.info("[addAxis] columnName = " + columnName);
            axis.add(columnName);
            passRecord(columnName);
            children.keySet().forEach(key -> children.get(key).addAxis(columnName));
        }
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    private void passRecord(String axis) {
        for (Record record : records) {
            if (axis.contains(":")) {
                String[] values = axis.split(":");
                if (record.getParam(values[0]).equals(values[1])) {
                    addChild(values[0], values[1], record);
                }
            } else {
                addChild(axis, record.getParam(axis), record);
            }
        }
    }

    public void nextAxis() {
        if(children != null){
            children.keySet().stream().map(children::get).forEach(Node::nextAxis);
        } else {
            children = new HashMap<>();
            logger.info("[nextAxis] node = " + value);
        }
    }

    public void close(boolean isContainRecord) {
        logger.info("[close] close node tree.");
        calc(isContainRecord);
    }

    private void addChild(String name, String value, Record record) {
        if(children.containsKey(value)) {
            children.get(value).addRecord(record);
        } else {
            Node node = new Node(name, record.getParam(name));
            node.addRecord(record);
            children.put(value, node);
        }
    }

    private void calc(boolean isContainRecord) {
        if(children != null) {
            children.keySet()
                    .stream().parallel()
                    .map(children::get).forEach(node -> node.calc(isContainRecord));
        }
        int sum = 0, count = 0;
        for(Record record: records) {
            sum += record.getParamAsInt("overtimeMinutes");
            count++;
        }
        double average = sum / (double)count;
        result.setCount(count);
        result.setSum(sum);
        result.setAverage(average);

        double dSum = 0;
        for(Record record: records) {
            dSum += Math.pow(record.getParamAsInt("overtimeMinutes") - result.getAverage(), 2);
        }
        result.setDSum(dSum);
        result.setDispersion(dSum / result.getCount());
        result.setStandardDeviation(Math.sqrt(result.getDispersion()));
        if(!isContainRecord) {
            records = null;
        }
    }
}

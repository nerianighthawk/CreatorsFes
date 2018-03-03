package jp.co.unirita.creatorsfes.teamc.model;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class NodeData {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private String axisName;
    private String axisValue;
    private Map<String, NodeData> children = null;
    private List<Record> records;
    private List<String> axisNames;
    private Map<String, Set<String>> axisValues;
    private AnalysisResult result;

    public NodeData(String axisName, String axisValue) {
        this.axisName = axisName;
        this.axisValue = axisValue;
        logger.info("[Node] new node. axisName = " + axisName + ", axisValue = " + axisValue);

        this.records = new ArrayList<>();
        this.axisNames = new ArrayList<>();
        this.axisValues = new LinkedHashMap<>();
        this.result = new AnalysisResult();
    }

    public void addAxis(String columnName) {
        if(children != null) {
            logger.info("[addAxis] columnName = " + columnName);
            axisNames.add(columnName);
            passRecord(columnName);
            children.keySet().forEach(key -> children.get(key).addAxis(columnName));
        }
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    private void passRecord(String axis) {
        String key = axis;
        Set<String> axisValue = new TreeSet<>();
        for (Record record : records) {
            if (axis.contains(":")) {
                String[] values = axis.split(":");
                key = values[1];
                if (record.getParam(values[0]).equals(values[1])) {
                    addChild(values[0], values[1], record);
                    axisValue.add(values[1]);
                }
            } else {
                addChild(axis, record.getParam(axis), record);
                axisValue.add(record.getParam(axis));
            }
        }
        axisValues.put(key, axisValue);
    }

    public void nextAxis() {
        if(children != null){
            children.keySet().stream().map(children::get).forEach(NodeData::nextAxis);
        } else {
            children = new TreeMap<>();
            logger.info("[nextAxis] node = " + axisValue);
        }
    }

    public void close(boolean isContainRecord) {
        logger.info("[close] close node tree.");
        calc(isContainRecord);
    }

    private void addChild(String name, String value, Record record) {
        String key = name + ":" + value;
        NodeData node = children.get(key);
        if (node == null) {
            node = new NodeData(name, record.getParam(name));
            children.put(key, node);
        }
        node.addRecord(record);
    }

    private void calc(boolean isContainRecord) {
        if(children != null) {
            children.keySet()
                    .stream().parallel()
                    .map(children::get).forEach(node -> node.calc(isContainRecord));
        }
        int sum = 0;
        for(Record record: records) {
            sum += record.getParamAsInt("overtimeMinutes");
        }
        result.setCount(records.size());
        result.setSum(sum);
        result.setAverage(result.getSum() / (double)result.getCount());

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

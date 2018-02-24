package jp.co.unirita.creatorsfes.teamc.model;

import jp.co.unirita.creatorsfes.teamc.model.record.Record;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Node {

    public static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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
            logger.info("[addAxis] columnName = " + columnName);
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
            children.keySet().stream().map(children::get).forEach(Node::nextAxis);
        } else {
            children = new HashMap<>();
            List<Record> tmp = new ArrayList<>();

            for (Record record : records) {
                boolean use = false;
                for (String key : axis) {
                    if (key.contains(":")) {
                        String[] values = key.split(":");
                        if (record.getParam(values[0]).equals(values[1])) {
                            addChild(values[0], record);
                            use = true;
                        }
                    } else {
                        addChild(record.getParam(key), record);
                        use = true;
                    }
                }
                if (!use) {
                    tmp.add(new Record(record));
                }
            }
            records = new ArrayList<>();
            tmp.forEach(records::add);
            logger.info("[nextAxis] node = " + value);
        }

    }

    public void close(boolean isContainRecord) {
        logger.info("[close] close node tree.");
        calc(isContainRecord);
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

    private void calc(boolean isContainRecord) {
        if(children != null) {
            children.keySet()
                    .stream().parallel()
                    .map(children::get).forEach(node -> node.calc(isContainRecord));
        }
        int sum = 0, count = 0;
        if(children != null) {
            for (String key : children.keySet()) {
                sum += children.get(key).getResult().getSum();
                count += children.get(key).getResult().getCount();
            }
        }
        for(Record record: records) {
            sum += record.getParamAsInt("overtimeMinutes");
            count++;
        }
        double average = sum / (double)count;
        result.setCount(count);
        result.setSum(sum);
        result.setAverage(average);

        double dSum = 0;
        if(children != null) {
            for (String key : children.keySet()) {
                dSum += children.get(key).getResult().getDSum();
            }
        }
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

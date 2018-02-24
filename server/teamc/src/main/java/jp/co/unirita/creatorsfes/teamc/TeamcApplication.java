package jp.co.unirita.creatorsfes.teamc;

import jp.co.unirita.creatorsfes.teamc.model.record.Record;
import jp.co.unirita.creatorsfes.teamc.model.record.RecordFactory;
import jp.co.unirita.creatorsfes.teamc.util.AxisUtil;
import jp.co.unirita.creatorsfes.teamc.util.axis.Axis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TeamcApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(TeamcApplication.class, args);

        List<Record> records = RecordFactory.getRecordList();
        List<Axis> axis = AxisUtil.getAxisList();
        axis.forEach(a -> a.classify(records));
    }
}

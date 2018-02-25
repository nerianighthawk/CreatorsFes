package jp.co.unirita.creatorsfes.teamc;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jp.co.unirita.creatorsfes.teamc.cache.RecordCache;
import jp.co.unirita.creatorsfes.teamc.model.Record;
import jp.co.unirita.creatorsfes.teamc.util.AxisUtil;
import jp.co.unirita.creatorsfes.teamc.util.axis.Axis;

@SpringBootApplication
public class TeamcApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(TeamcApplication.class, args);

        List<Record> records = RecordCache.getRecordList();
        List<Axis> axis = AxisUtil.getAxisList();
        axis.forEach(a -> a.classify(records));
    }
}

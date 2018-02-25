package jp.co.unirita.creatorsfes.teamc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.unirita.creatorsfes.teamc.model.MasterData;
import jp.co.unirita.creatorsfes.teamc.model.Record;

public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static BufferedReader getBufferedReader(File file) throws Exception{
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
    }

    public static List<Record> loadRecordList() throws Exception {
        File file = new File("data/record.csv");
        try (BufferedReader br = getBufferedReader(file)) {
            List<Record> records = new ArrayList<>();
            String line =  br.readLine();
            String[] key = line.split(",");
            while ((line = br.readLine()) != null) {
                Record record = new Record();
                String[] values = line.split(",");
                if (key.length != values.length) {
                    throw new RuntimeException("invalid data: " + line);
                }
                for (int idx = 0; idx < key.length; idx++) {
                    record.setParam(key[idx], values[idx]);
                }
                records.add(record);
            }
            logger.info("[loadRecordList] Load " + records.size() + " records.");
            return records;
        } finally {
        }
    }

    public static List<MasterData> loadMasterData(String masterName) throws Exception{
        File file = new File("data/" + masterName + "MST.csv");
        try (BufferedReader br = getBufferedReader(file)) {
            List<MasterData> masters = new ArrayList<>();
            String line =  br.readLine();
            String[] key = line.split(",");
            while ((line = br.readLine()) != null) {
            	MasterData master = new MasterData();
                String[] values = line.split(",");
                if (key.length != values.length) {
                    throw new RuntimeException("invalid data: " + line);
                }
                for (int idx = 0; idx < key.length; idx++) {
                	master.setParam(key[idx], values[idx]);
                }
                masters.add(master);
            }
            logger.info("[loadMasterData] Load master data of " + masterName + " " + masters.size() + " records.");
            return masters;
        } finally {
        }
    }
}

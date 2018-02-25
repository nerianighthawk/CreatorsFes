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

import jp.co.unirita.creatorsfes.teamc.model.Master;
import jp.co.unirita.creatorsfes.teamc.model.Record;

public class FileUtil {
    public static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String AXIS_PACKAGE_NAME = "jp.co.unirita.creatorsfes.teamc.util.axis";
    //private static final String PACKAGE_SEPARATOR = ".";
    //private static final String CLASS_SUFFIX = ".class";

    private static BufferedReader getBufferedReader(File file) throws Exception{
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
    }

    /*
    public static Set<String> getClassList(String packageName) throws IOException, URISyntaxException {
        String rootPackageName = packageName.replace(PACKAGE_SEPARATOR, File.separator);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Enumeration<URL> rootUrls = classLoader.getResources(rootPackageName);

        Set<String> classNames = new HashSet<>();
        while (rootUrls.hasMoreElements()) {
            URL rootUrl = rootUrls.nextElement();
            Path rootPath = Paths.get(rootUrl.toURI());

            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    String pathName = path.toString();
                    if (pathName.endsWith(CLASS_SUFFIX)) {
                        int beginIndex = pathName.lastIndexOf(rootPackageName);
                        int endIndex = pathName.lastIndexOf(CLASS_SUFFIX);
                        String className = pathName.substring(beginIndex, endIndex).replace(File.separator, PACKAGE_SEPARATOR);
                        if (!className.equals(AXIS_PACKAGE_NAME + ".Axis")) {
                            classNames.add(className);
                        }
                    }
                    return super.visitFile(path, attrs);
                }
            });
        }
        return classNames;
    }
    */

    public static List<Record> loadRecordList() throws Exception {
        File file = new File("data/record.csv");
        try (BufferedReader br = getBufferedReader(file)) {
            List<Record> records = new ArrayList<>();
            String line =  br.readLine();
            String[] key = line.split(",");
            while((line = br.readLine()) != null) {
                Record record = new Record();
                String[] values = line.split(",");
                if(key.length != values.length) {
                    throw new RuntimeException("invalid data");
                }
                for(int idx = 0; idx < key.length; idx++) {
                    record.setParam(key[idx], values[idx]);
                }
                records.add(record);
            }
            logger.info("[loadRecordList] Load " + records.size() + " records.");
            return records;
        } finally {
        }
    }

    public static List<Master> loadMasterData(String masterName) throws Exception{
        File file = new File("data/" + masterName + "MST.csv");
        try (BufferedReader br = getBufferedReader(file)) {
            List<Master> masters = new ArrayList<>();
            String line;
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                masters.add(new Master(values[0], values[1]));
            }
            logger.info("[loadMasterData] Load master data of " + masterName + " " + masters.size() + " records.");
            return masters;
        } finally {
        }
    }
}

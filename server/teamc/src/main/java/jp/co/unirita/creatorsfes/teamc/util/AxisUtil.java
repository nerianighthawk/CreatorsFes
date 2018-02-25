package jp.co.unirita.creatorsfes.teamc.util;

import java.util.ArrayList;
import java.util.List;

import jp.co.unirita.creatorsfes.teamc.util.axis.Axis;

public class AxisUtil {

    private static List<Axis> axisList = null;


    public static List<Axis> getAxisList() throws Exception {
        if(axisList != null) {
            return axisList;
        }
        loadAxis();
        return axisList;
    }

    private static void loadAxis() throws Exception {
        axisList = new ArrayList<>();
        /*
        Set<String> classes = null;
        try {
            classes = FileUtil.getClassList(FileUtil.AXIS_PACKAGE_NAME);
        }catch (Exception e){
            e.printStackTrace();
        }
        for(String className: classes) {
            Class<?> clazz = Class.forName(className);
            Axis axis = (Axis)clazz.newInstance();
            axisList.add(axis);
        }
        */
        List<String> classes = new ArrayList<>();
        classes.add("HalfYearAxis");
        classes.add("OverTimeMinutesAxis");
        classes.add("QuarterYearAxis");
        for(String className: classes) {
            Class<?> clazz = Class.forName(FileUtil.AXIS_PACKAGE_NAME + "." + className);
            Axis axis = (Axis)clazz.newInstance();
            axisList.add(axis);
        }
    }
}

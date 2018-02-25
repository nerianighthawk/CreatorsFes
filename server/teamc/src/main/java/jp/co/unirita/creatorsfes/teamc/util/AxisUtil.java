package jp.co.unirita.creatorsfes.teamc.util;

import java.util.ArrayList;
import java.util.List;

import jp.co.unirita.creatorsfes.teamc.util.axis.Axis;

public class AxisUtil {
    private static final String AXIS_PACKAGE_NAME = "jp.co.unirita.creatorsfes.teamc.util.axis";
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
        String[] classNames = {
            "OverTimeMinutesAxis",
            "DepartmentAxis",
            "CompanyAxis", // DepartmentAxisの後
            "QuarterYearAxis",
            "HalfYearAxis",
        };
        for(String className: classNames) {
            Class<?> clazz = Class.forName(AXIS_PACKAGE_NAME + "." + className);
            Axis axis = (Axis)clazz.newInstance();
            axisList.add(axis);
        }
    }
}

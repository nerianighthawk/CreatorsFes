package makedata;

import java.util.Calendar;

public class DateInfo {
	static String[] weekTbl = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT", };
	String date;
	String week;

	public DateInfo(int lineNo, String line) {
		this.date = line.substring(0, 8);
		this.week = getWeek();
	}

	@Override
	public String toString() {
		return date + "," + week;
	}

	String getWeek() {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6)) - 1;
		int day = Integer.parseInt(date.substring(6, 8));
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, 0, 0, 0);
		c.add(Calendar.SECOND, 1);
		int n = c.get(Calendar.DAY_OF_WEEK);
		return weekTbl[n - 1];
	}

}

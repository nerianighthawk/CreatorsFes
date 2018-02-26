package makedata;

public class DateInfo {
	String yyyymmdd;

	public DateInfo(int lineNo, String line) {
		this.yyyymmdd = line.substring(0, 8);
	}

	@Override
	public String toString() {
		return yyyymmdd;
	}

}

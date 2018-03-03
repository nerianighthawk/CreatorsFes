package makedata;

public class LastInfo {
	String name;
	int population;
	
	int startidx;
	int range;

	public LastInfo(int lineNo, String line, int startidx) {
		String[] cols = line.split(",");
		this.name = cols[0];
		if (cols.length > 1) {
			this.population = Integer.parseInt(cols[1]);
		}

		this.startidx = startidx;
		if (population > 0) {
			this.range = population;
		} else {
			this.range = 20;
		}
	}

	@Override
	public String toString() {
		return name;
	}
	
	public int getStartidx() {
		return startidx;
	}

	public int getRange() {
		return range;
	}
	
}

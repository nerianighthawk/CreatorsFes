package makedata;

public class RankInfo {
	String id;
	String name;

	public RankInfo(int lineNo, String line) {
		this.id = String.format("r%05d", (lineNo-1));
		this.name = line;
	}

	@Override
	public String toString() {
		return name;
	}

}

package makedata;

public class DeptInfo {
	String id;
	String name;
	String compid;

	public DeptInfo(int lineNo, String line) {
		this.id = String.format("d%05d", (lineNo-1));
		this.name = line;
		this.compid = String.format("c00000");
	}

	@Override
	public String toString() {
		return id + "," + name + "," + compid;
	}

}

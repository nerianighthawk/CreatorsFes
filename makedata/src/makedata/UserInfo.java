package makedata;

import java.util.List;

public class UserInfo {
	static String[] rankMst = { "J1", "J2", "J3", "J4", "J5", "S1", "S2", };
	String id;
	String name;
	String divid;
	String rank;

	public UserInfo(int lineNo, String line, List<DeptInfo> deptList, List<RankInfo> rankList) {
		this.id = getUserId(lineNo);
		this.name = line;
		this.divid = getDeptId(deptList);
		this.rank = getRankId(rankList);
	}

	@Override
	public String toString() {
		return id + "," + name + "," + divid + "," + rank;
	}

	String getUserId(int lineNo) {
		return String.format("u%05d", (lineNo-1));
	}
	
	String getDeptId(List<DeptInfo> deptList) {
		int dice = (int)(deptList.size() * Math.random());
		return deptList.get(dice).id;
	}
	
	String getRankId(List<RankInfo> rankList) {
		int dice = (int)(rankList.size() * Math.random());
		return rankList.get(dice).name;
	}
	
}

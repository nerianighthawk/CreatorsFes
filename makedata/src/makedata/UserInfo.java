package makedata;

import java.util.List;

import util.RandUtil;

public class UserInfo {
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
		int dice = RandUtil.dice(deptList.size());
		return deptList.get(dice).id;
	}
	
	String getRankId(List<RankInfo> rankList) {
		int dice = RandUtil.dice(rankList.size());
		return rankList.get(dice).name;
	}
	
}

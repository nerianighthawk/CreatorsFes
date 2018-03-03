import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import makedata.DateInfo;
import makedata.DeptInfo;
import makedata.FactInfo;
import makedata.FirstInfo;
import makedata.LastInfo;
import makedata.RankInfo;
import makedata.UserInfo;
import util.FileUtil;
import util.RandUtil;

public class Main {
	//dir
	static String datasrc = "datasrc";
	static String dataout = "dataout";
	//src
	static String divsrc = "division.csv";
	static String ranksrc = "rank.csv";
	static String firstsrc = "first_name.csv";
	static String lastsrc = "last_name.csv";
	static String calsrc = "calendar.csv";
	//out
	static String deptmst = "departmentMST.csv";
	static String usermst = "userMST.csv";
	static String record = "record.csv";
	//list
	List<DeptInfo> deptList = new ArrayList<>();
	List<RankInfo> rankList = new ArrayList<>();
	List<FirstInfo> firstList = new ArrayList<>();
	int firstIdx;
	List<LastInfo> lastList = new ArrayList<>();
	int lastIdx;
	Set<String> userset = new HashSet<>();
	List<UserInfo> userList = new ArrayList<>();
	List<DateInfo> dateList = new ArrayList<>();
	List<FactInfo> factList = new ArrayList<>();

	void execute() throws Exception {
		FileUtil.mkdir(dataout);
		try (BufferedReader br = FileUtil.newReader(datasrc + "/" + divsrc)) {
			int lineNo = 1;
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				deptList.add(new DeptInfo(lineNo, line));
				lineNo++;
			}
		}
		try (BufferedReader br = FileUtil.newReader(datasrc + "/" + ranksrc)) {
			int lineNo = 1;
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				rankList.add(new RankInfo(lineNo, line));
				lineNo++;
			}
		}
		try (BufferedReader br = FileUtil.newReader(datasrc + "/" + firstsrc)) {
			int lineNo = 1;
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				FirstInfo info = new FirstInfo(lineNo, line, firstIdx);
				firstList.add(info);
				lineNo++;
				firstIdx += info.getRange();
			}
		}
		try (BufferedReader br = FileUtil.newReader(datasrc + "/" + lastsrc)) {
			int lineNo = 1;
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				LastInfo info = new LastInfo(lineNo, line, lastIdx);
				lastList.add(info);
				lineNo++;
				lastIdx += info.getRange();
			}
		}
		while (userset.size() < 100) {
			int dice1 = RandUtil.dice(lastIdx);
			int idx1 = 0;
			for (int i = lastList.size() - 1; i >= 0; i--) {
				if (lastList.get(i).getStartidx() < dice1) {
					idx1 = i;
					break;
				}
			}
			int dice2 = RandUtil.dice(firstIdx);
			int idx2 = 0;
			for (int i = firstList.size() - 1; i >= 0; i--) {
				if (firstList.get(i).getStartidx() < dice2) {
					idx2 = i;
					break;
				}
			}
			String name = lastList.get(idx1) + " " + firstList.get(idx2);
			if (userset.contains(name)) {
				System.out.println("conflict: " + name);
				continue;
			}
			userset.add(name);
		}
		{
			int lineNo = 1;
			for (String name : userset) {
				userList.add(new UserInfo(lineNo, name, deptList, rankList));
				lineNo++;
			}
		}
		try (BufferedReader br = FileUtil.newReader(datasrc + "/" + calsrc)) {
			int lineNo = 1;
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				dateList.add(new DateInfo(lineNo, line));
				lineNo++;
			}
		}
		for (DateInfo d : dateList) {
			for (UserInfo u : userList) {
				factList.add(new FactInfo(d, u));
			}
		}
		try (PrintWriter pw = FileUtil.newWriter(dataout + "/" + deptmst)) {
			pw.println("departmentId,departmentName,companyId");
			for (DeptInfo d : deptList) {
				pw.println(d);
			}
		}
		try (PrintWriter pw = FileUtil.newWriter(dataout + "/" + usermst)) {
			pw.println("userId,userName,gender,birthAge,departmentId,rank");
			for (UserInfo u : userList) {
				pw.println(u);
			}
		}
		try (PrintWriter pw = FileUtil.newWriter(dataout + "/" + record)) {
			pw.println("date,week,user,attendTime,leaveTime");
			for (FactInfo f : factList) {
				pw.println(f);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Main().execute();
	}

}

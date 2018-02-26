import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import makedata.DateInfo;
import makedata.DeptInfo;
import makedata.FactInfo;
import makedata.RankInfo;
import makedata.UserInfo;
import util.FileUtil;

public class Main {
	//dir
	static String datasrc = "datasrc";
	static String dataout = "dataout";
	//src
	static String divsrc = "division.csv";
	static String ranksrc = "rank.csv";
	static String usersrc = "personal_infomation.csv";
	static String calsrc = "calendar.csv";
	//out
	static String deptmst = "departmentMST.csv";
	static String usermst = "userMST.csv";
	static String record = "record.csv";
	//list
	List<DeptInfo> deptList = new ArrayList<>();
	List<RankInfo> rankList = new ArrayList<>();
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
		try (BufferedReader br = FileUtil.newReader(datasrc + "/" + usersrc)) {
			int lineNo = 1;
			while (lineNo <= 100) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				userList.add(new UserInfo(lineNo, line, deptList, rankList));
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
			pw.println("userId,userName,departmentId,rank");
			for (UserInfo u : userList) {
				pw.println(u);
			}
		}
		try (PrintWriter pw = FileUtil.newWriter(dataout + "/" + record)) {
			pw.println("date,userId,attendTime,leaveTime");
			for (FactInfo f : factList) {
				pw.println(f);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Main().execute();
	}

}

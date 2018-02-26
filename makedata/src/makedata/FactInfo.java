package makedata;

public class FactInfo {
	DateInfo date;
	UserInfo user;
	int pretime;
	int overtime;

	public FactInfo(DateInfo d, UserInfo u) {
		this.date = d;
		this.user = u;
		this.pretime = getPretime();
		this.overtime = getOvertime();
	}

	@Override
	public String toString() {
		int p = pretime + 8 * 60 + 30;
		String pre = String.format("%02d:%02d", p/60, p%60);
		int o = overtime + 17 * 60 + 30;
		String over = String.format("%02d:%02d", o/60, o%60);
		return date + "," + user.id + "," + pre + "," + over;
	}

	int getPretime() {
		int min = (int)(30 * Math.random());
		return min;
	}
	
	int getOvertime() {
		int dice = (int)(1000 * Math.random());
		int min = (int)(60 * Math.random());
		int night = (int)((14-6) * Math.random()) + 6;
		if (dice < 250) {
			return min/2;
		} else if (dice < 650) {
			return min + 30;
		} else if (dice < 800) {
			return 1*60 + min + 30;
		} else if (dice < 900) {
			return 2*60 + min + 30;
		} else if (dice < 950) {
			return 3*60 + min + 30;
		} else if (dice < 990) {
			return 4*60 + min + 30;
		} else if (dice < 999) {
			return 5*60 + min + 30;
		}
		System.out.println(night*60 + min);
		return night*60 + min;
	}
	
}

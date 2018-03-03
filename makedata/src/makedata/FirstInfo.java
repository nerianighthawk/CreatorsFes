package makedata;

import util.RandUtil;

public class FirstInfo {
	enum Gender {
		MALE,
		FEMALE,
	};

	String name;
	int birth;
	Gender gender;
	int age;
	
	int startidx;
	int range;

	public FirstInfo(int lineNo, String line, int startidx) {
		String[] cols = line.split(",");
		this.name = cols[0];
		this.birth = Integer.parseInt(cols[1]);
		if (cols[2].equals("M")) {
			this.gender = Gender.MALE;
		} else {
			this.gender = Gender.FEMALE;
		}
		this.age = (2013 - birth) + RandUtil.dice(10) - 5;

		this.startidx = startidx;
		this.range = 100;
		if (gender == Gender.FEMALE) {
			range /= 4;
		}
		if (age > 60) {
			range /= 20;
		} else if (age > 50) {
			range /= 10;
		} else if (age > 40) {
			range /= 5;
		} else if (age > 30) {
			range /= 2;
		} else {
			range /= 1;
		}
	}

	@Override
	public String toString() {
		return name + "," + gender + "," + age;
	}

	public int getStartidx() {
		return startidx;
	}

	public int getRange() {
		return range;
	}
	
}

package s4_18;

public final class StaticValue {// 상수선언 클래스
	final static int continue_ = 1, finish_ = 2, BABY1 = 1, BABY2 = 2, Child = 3, Teen = 4, Adult = 5, Senior = 6,
			BABYDAY = 0, BABYNIGHT = 0, CHILDDAY = 46000, CHILDNIGHT = 36000, TEENDAY = 52000, TEENNIGHT = 43000,
			ADULTDAY = 59000, ADULTNIGHT = 50000, SENIORDAY = 46000, SENIORNIGHT = 36000, HANDY = 2, HEROE = 3,
			MANYCHILD = 4, PREGNANT = 5, ARMY = 6;
	final static float DISCOUNT_RATEOF_HANDY_OR_HEROE = (float) 0.5, DISCOUNT_RATEOF_ARMY = (float) 0.49,
			DISCOUNT_RATEOF_MANYCHILD = (float) 0.3;

	final static String ticketType(int a) {
		String[] ticket = { "주간권", "야간권" };
		return ticket[a - 1];
	}

	final static String humanType(int a) {
		String[] humantype = { "아기", "아기", "어린이", "청소년", "성인", "노인" };
		return humantype[a - 1];
	}

	final static String specialType(int a) {
		String[] specialtype = { "없음", "장애인우대적용", "국가유공자우대적용", "다둥이행복카드적용", "임산부우대적용", "휴가장병우대적용" };
		return specialtype[a - 1];
	}
}

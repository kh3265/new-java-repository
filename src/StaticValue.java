package s4_18;

public final class StaticValue {// ������� Ŭ����
	final static int continue_ = 1, finish_ = 2, BABY1 = 1, BABY2 = 2, Child = 3, Teen = 4, Adult = 5, Senior = 6,
			BABYDAY = 0, BABYNIGHT = 0, CHILDDAY = 46000, CHILDNIGHT = 36000, TEENDAY = 52000, TEENNIGHT = 43000,
			ADULTDAY = 59000, ADULTNIGHT = 50000, SENIORDAY = 46000, SENIORNIGHT = 36000, HANDY = 2, HEROE = 3,
			MANYCHILD = 4, PREGNANT = 5, ARMY = 6;
	final static float DISCOUNT_RATEOF_HANDY_OR_HEROE = (float) 0.5, DISCOUNT_RATEOF_ARMY = (float) 0.49,
			DISCOUNT_RATEOF_MANYCHILD = (float) 0.3;

	final static String ticketType(int a) {
		String[] ticket = { "�ְ���", "�߰���" };
		return ticket[a - 1];
	}

	final static String humanType(int a) {
		String[] humantype = { "�Ʊ�", "�Ʊ�", "���", "û�ҳ�", "����", "����" };
		return humantype[a - 1];
	}

	final static String specialType(int a) {
		String[] specialtype = { "����", "����ο������", "���������ڿ������", "�ٵ����ູī������", "�ӻ�ο������", "�ް��庴�������" };
		return specialtype[a - 1];
	}
}

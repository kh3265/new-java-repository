package s4_18;

import java.util.Scanner;

public class P1 {
	public static int visiting_Date, ticket_Type, numberof_Tickets, prefer_Number, ticket_Price, human_Type, age, continue_or_Not, finish_or_Not;
	public static int try_Number = 0;
	public static int total_Price = 0;
	public static int[] ticket_Typearr = new int[10];
	public static int[] human_Typearr = new int[10];
	public static int[] count_Ticketarr = new int[10];
	public static int[] ticket_Pricearr = new int[10];
	public static int[] special_Typearr = new int[10];
	public static String id_Number = new String(new char[13]);

	final static int continue_ = 1, finish_ = 2, BABY1 = 1, BABY2 = 2, Child = 3, Teen = 4, Adult = 5, Senior = 6,
			BABYDAY = 0, BABYNIGHT = 0, CHILDDAY = 46000, CHILDNIGHT = 36000, TEENDAY = 52000, TEENNIGHT = 43000,
			ADULTDAY = 59000, ADULTNIGHT = 50000, SENIORDAY = 46000, SENIORNIGHT = 36000,
			HANDY=2, HEROE=3, MANYCHILD=4, PREGNANT=5, ARMY=6;
	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("�湮 �������� �Է��Ͽ��ּ���.(ex.20220414)");
		int visiting_Date = scanner.nextInt();// ���糯¥ �ҷ����� ���� ���� �� �� �𸥴�. �׷��� ��¥ �Է¹���.

		while (true) {
			while (true) {
				try_Number = try_Number + 1; // ù��° ȸ�� ,,�ٽù߱��Ҷ��� �ι�°ȸ��,,
				ticket_Type = inputTicketType();
				age = inputIdNumber(visiting_Date);
				human_Type = humantype(age);
				ticket_Price = price(ticket_Type, human_Type);

				inputHowmanyTickets();
				discount_uv();
				continue_or_Not = continueorNot();
				if (continue_or_Not == continue_) {
					continue;
				}
				if (continue_or_Not == finish_) {
					break;
				}
			}
			printingTicket();
			finish_or_Not = finshorNot();

			if (finish_or_Not == continue_) {
				continue;
			} else if (finish_or_Not == finish_) {
				break;
			}
		}
		closingment();
	}

	public static int inputTicketType() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("������ �����ϼ���\n");
		System.out.printf("1. �ְ���\n");
		System.out.printf("2. �߰���\n");
		int ticket_Type = scanner.nextInt();
		ticket_Typearr[try_Number - 1] = ticket_Type;
		return ticket_Type;
	}

	public static int inputIdNumber(int visiting_Date) {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("�ֹι�ȣ�� �Է��ϼ���\n");
		String id_Number = scanner.nextLine();// <<���̰��>>
		int birth_Date = (id_Number.charAt(0) - 48) * 100000 + (id_Number.charAt(1) - 48) * 10000
				+ (id_Number.charAt(2) - 48) * 1000 + (id_Number.charAt(3) - 48) * 100 + (id_Number.charAt(4) - 48) * 10
				+ (id_Number.charAt(5) - 48) * 1;// �ƽ�Ű�ڵ� �̿� 94���
		if (id_Number.charAt(6) <= '2') {
			birth_Date += 19000000;
		} else
			birth_Date += 20000000; // ���� ������ Ȯ��
		age = (visiting_Date / 10000) - (birth_Date / 10000);
		if ((visiting_Date % 10000) < (birth_Date % 10000)) {
			age = age - 1;
		} else
			age = age;// ���� �������� Ȯ��
		System.out.printf("�����̴� %d�Դϴ�.\n", age);// ������ ���
		return age;
	}

	public static int inputHowmanyTickets() {
		Scanner scanner = new Scanner(System.in);
		while (true) {// <<Ƽ�� ���� �κ� ����>>
			System.out.printf("��� �ֹ��Ͻðڽ��ϱ�?(�ִ� 10��)\n");
			int numberof_Tickets = scanner.nextInt();
			count_Ticketarr[try_Number - 1] = numberof_Tickets;// ù��° ȸ���� Ƽ�ϼ����� count_Ticketarr�迭 ù�ڸ��� �Էµ�
			if (0 < numberof_Tickets && numberof_Tickets < 11) {
				ticket_Price = ticket_Price * numberof_Tickets;
				break;
			} else
				System.out.printf("�ٽ� �Է����ּ���");
			continue;
		}
		return numberof_Tickets;
	}

	public static String ticketType(int a) {
		String[] ticket = { "�ְ���", "�߰���" };
		return ticket[a - 1];
	}

	public static String humanType(int a) {
		String[] humantype = { "�Ʊ�", "�Ʊ�", "���", "û�ҳ�", "����", "����" };
		return humantype[a - 1];
	}

	public static String specialType(int a) {
		String[] specialtype = { "����", "����ο������", "���������ڿ������", "�ٵ����ູī������", "�ӻ�ο������", "�ް��庴�������" };
		return specialtype[a - 1];
	}

	public static int humantype(int age_) {
		int human_Type = 6;
		if (age_ < 1) {
			human_Type = BABY1;
		} else if (age_ < 3 && age_ >= 1) {
			human_Type = BABY2;
		} else if (3 <= age_ && age_ < 13) {
			human_Type = Child;
		} else if (13 <= age_ && age_ < 19) {
			human_Type = Teen;
		} else if (19 <= age_ && age_ < 65) {
			human_Type = Adult;
		} else if (65 <= age_) {
			human_Type = Senior;
		}
		return human_Type;
	}

	public static int price(int a, int b) {
		int[][] price_Arr = { { BABYDAY, BABYDAY, CHILDDAY, TEENDAY, ADULTDAY, SENIORDAY }, // �ְ� ����ǥ
				{ BABYNIGHT, BABYNIGHT, CHILDNIGHT, TEENNIGHT, ADULTNIGHT, SENIORNIGHT },// �߰� ����ǥ
		};
		System.out.printf("������ %d���Դϴ�.", price_Arr[a - 1][b - 1]);
		human_Typearr[try_Number - 1] = human_Type;// ù��° ȸ���� ��������� human_Typearr�迭 ù�ڸ��� �Էµ�. �ι�°ȸ������ �迭 �ι�° �ڸ��� �Էµ�.
													// ����°ȸ����..
		return price_Arr[a - 1][b - 1]; // ex)2��6���� �߰�-���� �����̴�.
	}

	public static int ticketDiscount(int specialtype, int price) {
		if (specialtype == HANDY || specialtype == HEROE) {// �����,������������ ���
			price = (int) ((double) price * 0.5);
		}
		if (specialtype == PREGNANT) {// �ӻ���� ���
			price = (int) (price * 0.5);
		}
		if (specialtype == ARMY) {// �ް��庴�ϰ��
			price = (int) (price * 0.51);
		}
		if (specialtype == MANYCHILD) {// �ٵ����ູī���ϰ��
			price = (int) (price * 0.7);
		}
		return price;
	}

	public static int discount_uv() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("�������� �����ϼ���\n");
		System.out.printf("1.����\n");
		System.out.printf("2.�����\n");
		System.out.printf("3.����������\n");
		System.out.printf("4.�ٵ��� �ູī��\n");
		System.out.printf("5.�ӻ��\n");
		System.out.printf("6.�ް��庴 ���\n");
		int prefer_Number = scanner.nextInt();
		special_Typearr[try_Number - 1] = prefer_Number;// ù��° ȸ���� �������� special_Typearr�迭 ù�ڸ��� �Էµ�.
		ticket_Price = ticketDiscount(prefer_Number, ticket_Price);
		ticket_Pricearr[try_Number - 1] = ticket_Price;// ù��°ȸ���� Ƽ�ϰ����� ticket_Pricearr�迭 ù�ڸ��� �Էµ�.
		total_Price = total_Price + ticket_Price;
		return ticket_Price;
	}

	public static int continueorNot() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("��� �߱��Ͻðڽ��ϱ�?\n");
		System.out.printf("1.Ƽ�Ϲ߱�\n");
		System.out.printf("2.����\n");

		int continue_or_Not = scanner.nextInt();
		return continue_or_Not;
	}

	public static int printingTicket() {
		System.out.printf("Ƽ�Ϲ߱��������մϴ�. �����մϴ�.\n\n");
		System.out.printf("===================�Ե�����===================\n");
		for (int i = 0; i < try_Number; i++) {
			System.out.printf("%s %-6s X %-2d   %-6d    %-10s\n", ticketType(ticket_Typearr[i]),
					humanType(human_Typearr[i]), count_Ticketarr[i], ticket_Pricearr[i],
					specialType(special_Typearr[i]));
		}
		System.out.printf("����� �Ѿ��� %d���Դϴ�.\n", total_Price);
		System.out.printf("==============================================\n\n");

		return 0;
	}

	public static int finshorNot() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("��� ����(1: ���ο� �ֹ�, 2: ���α׷� ����) : ");
		int finish_or_Not = scanner.nextInt();
		return finish_or_Not;
	}

	public static int closingment() {
		System.out.printf("���α׷��� �����մϴ�.\n");
		return 0;
	}

}

package s4_18;

import java.util.Scanner;

public class Startprogram {
	Orderdata orderItem = new Orderdata();

	void startprogram() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("�湮 �������� �Է��Ͽ��ּ���.(ex.20220414)");
		int visiting_Date = scanner.nextInt();// ���糯¥ �ҷ����� ���� ���� �� �� �𸥴�. �׷��� ��¥ �Է¹���.

		while (true) {
			while (true) {
				orderItem.try_Number = orderItem.try_Number + 1; // ù��° ȸ�� ,,�ٽù߱��Ҷ��� �ι�°ȸ��,,
				orderItem.ticket_Type = inputTicketType();
				orderItem.age = inputIdNumber(visiting_Date);
				orderItem.human_Type = humantype(orderItem.age);
				orderItem.ticket_Price = price(orderItem.ticket_Type, orderItem.human_Type);

				inputHowmanyTickets();
				discount_uv();
				orderItem.continue_or_Not = continueorNot();
				if (orderItem.continue_or_Not == StaticValue.continue_) {
					continue;
				}
				if (orderItem.continue_or_Not == StaticValue.finish_) {
					break;
				}
			}
			printingTicket();
			orderItem.finish_or_Not = finshorNot();

			if (orderItem.finish_or_Not == StaticValue.continue_) {
				continue;
			} else if (orderItem.finish_or_Not == StaticValue.finish_) {
				break;
			}
		}
		closingment();
	}

	int inputTicketType() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("������ �����ϼ���\n");
		System.out.printf("1. �ְ���\n");
		System.out.printf("2. �߰���\n");
//		try {
		orderItem.ticket_Type = scanner.nextInt();
//		}catch(NullPointerException e) {
//		System.out.println("�����ڵ带 üũ���ּ���!");
//		}finally{
//		System.out.println("����ó���ڵ尡 �������� ����Ǿ����ϴ�.");
//		}
		orderItem.ticket_Typearr[orderItem.try_Number - 1] = orderItem.ticket_Type;
		return orderItem.ticket_Type;
	}

	int inputIdNumber(int visiting_Date) {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("�ֹι�ȣ�� �Է��ϼ���\n");
		orderItem.id_Number = scanner.nextLine();// <<���̰��>>
		int birth_Date = (orderItem.id_Number.charAt(0) - 48) * 100000 + (orderItem.id_Number.charAt(1) - 48) * 10000
				+ (orderItem.id_Number.charAt(2) - 48) * 1000 + (orderItem.id_Number.charAt(3) - 48) * 100
				+ (orderItem.id_Number.charAt(4) - 48) * 10 + (orderItem.id_Number.charAt(5) - 48) * 1;// �ƽ�Ű�ڵ� �̿� 94���
		if (orderItem.id_Number.charAt(6) <= '2') {
			birth_Date += 19000000;
		} else
			birth_Date += 20000000; // ���� ������ Ȯ��
		orderItem.age = (visiting_Date / 10000) - (birth_Date / 10000);
		if ((visiting_Date % 10000) < (birth_Date % 10000)) {
			orderItem.age = orderItem.age - 1;
		} else
			orderItem.age = orderItem.age;// ���� �������� Ȯ��
		System.out.printf("�����̴� %d�Դϴ�.\n", orderItem.age);// ������ ���
		return orderItem.age;
	}

	int inputHowmanyTickets() {
		Scanner scanner = new Scanner(System.in);
		while (true) {// <<Ƽ�� ���� �κ� ����>>
			System.out.printf("��� �ֹ��Ͻðڽ��ϱ�?(�ִ� 10��)\n");
			orderItem.numberof_Tickets = scanner.nextInt();
			orderItem.count_Ticketarr[orderItem.try_Number - 1] = orderItem.numberof_Tickets;// ù��° ȸ���� Ƽ�ϼ�����
																								// count_Ticketarr�迭
																								// ù�ڸ��� �Էµ�
			if (0 < orderItem.numberof_Tickets && orderItem.numberof_Tickets < 11) {
				orderItem.ticket_Price = orderItem.ticket_Price * orderItem.numberof_Tickets;
				break;
			} else
				System.out.printf("�ٽ� �Է����ּ���");
			continue;
		}
		return orderItem.numberof_Tickets;
	}
////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////

	int humantype(int age_) {
		int human_Type = 6;
		if (age_ < 1) {
			human_Type = StaticValue.BABY1;
		} else if (age_ < 3 && age_ >= 1) {
			human_Type = StaticValue.BABY2;
		} else if (3 <= age_ && age_ < 13) {
			human_Type = StaticValue.Child;
		} else if (13 <= age_ && age_ < 19) {
			human_Type = StaticValue.Teen;
		} else if (19 <= age_ && age_ < 65) {
			human_Type = StaticValue.Adult;
		} else if (65 <= age_) {
			human_Type = StaticValue.Senior;
		}
		return human_Type;
	}

	int price(int a, int b) {
		int[][] price_Arr = {
				{ StaticValue.BABYDAY, StaticValue.BABYDAY, StaticValue.CHILDDAY, StaticValue.TEENDAY,
						StaticValue.ADULTDAY, StaticValue.SENIORDAY }, // �ְ� ����ǥ
				{ StaticValue.BABYNIGHT, StaticValue.BABYNIGHT, StaticValue.CHILDNIGHT, StaticValue.TEENNIGHT,
						StaticValue.ADULTNIGHT, StaticValue.SENIORNIGHT },// �߰� ����ǥ
		};
		System.out.printf("������ %d���Դϴ�.", price_Arr[a - 1][b - 1]);
		orderItem.human_Typearr[orderItem.try_Number - 1] = orderItem.human_Type;// ù��° ȸ���� ��������� human_Typearr�迭 ù�ڸ���
																					// �Էµ�. �ι�°ȸ������ �迭 �ι�°
		// �ڸ���
		// �Էµ�.
		// ����°ȸ����..
		return price_Arr[a - 1][b - 1]; // ex)2��6���� �߰�-���� �����̴�.
	}

	int ticketDiscount(int specialtype, int price) {
		if (specialtype == StaticValue.HANDY || specialtype == StaticValue.HEROE
				|| specialtype == StaticValue.PREGNANT) {// �����,������������ ���
			price = (int) ((double) price * StaticValue.DISCOUNT_RATEOF_HANDY_OR_HEROE);
		}
		if (specialtype == StaticValue.ARMY) {// �ް��庴�ϰ��
			price = (int) (price * StaticValue.DISCOUNT_RATEOF_ARMY);
		}
		if (specialtype == StaticValue.MANYCHILD) {// �ٵ����ູī���ϰ��
			price = (int) (price * StaticValue.DISCOUNT_RATEOF_MANYCHILD);
		}
		return price;
	}

	int discount_uv() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("�������� �����ϼ���\n");
		System.out.printf("1.����\n");
		System.out.printf("2.�����\n");
		System.out.printf("3.����������\n");
		System.out.printf("4.�ٵ��� �ູī��\n");
		System.out.printf("5.�ӻ��\n");
		System.out.printf("6.�ް��庴 ���\n");
		orderItem.prefer_Number = scanner.nextInt();
		orderItem.special_Typearr[orderItem.try_Number - 1] = orderItem.prefer_Number;// ù��° ȸ���� �������� special_Typearr�迭
																						// ù�ڸ��� �Էµ�.
		orderItem.ticket_Price = ticketDiscount(orderItem.prefer_Number, orderItem.ticket_Price);
		orderItem.ticket_Pricearr[orderItem.try_Number - 1] = orderItem.ticket_Price;// ù��°ȸ���� Ƽ�ϰ����� ticket_Pricearr�迭
																						// ù�ڸ��� �Էµ�.
		orderItem.total_Price = orderItem.total_Price + orderItem.ticket_Price;
		return orderItem.ticket_Price;
	}

	int continueorNot() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("��� �߱��Ͻðڽ��ϱ�?\n");
		System.out.printf("1.Ƽ�Ϲ߱�\n");
		System.out.printf("2.����\n");

		int continue_or_Not = scanner.nextInt();
		return continue_or_Not;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	int printingTicket() {
		System.out.printf("Ƽ�Ϲ߱��������մϴ�. �����մϴ�.\n\n");
		System.out.printf("===================�Ե�����===================\n");
		for (int i = 0; i < orderItem.try_Number; i++) {
			System.out.printf("%s %-6s X %-2d   %-6d    %-10s\n", StaticValue.ticketType(orderItem.ticket_Typearr[i]),
					StaticValue.humanType(orderItem.human_Typearr[i]), orderItem.count_Ticketarr[i],
					orderItem.ticket_Pricearr[i], StaticValue.specialType(orderItem.special_Typearr[i]));
		}
		System.out.printf("����� �Ѿ��� %d���Դϴ�.\n", orderItem.total_Price);
		System.out.printf("==============================================\n\n");

		return 0;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	int finshorNot() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("��� ����(1: ���ο� �ֹ�, 2: ���α׷� ����) : ");
		int finish_or_Not = scanner.nextInt();
		return finish_or_Not;
	}

	int closingment() {
		System.out.printf("���α׷��� �����մϴ�.\n");
		return 0;
	}

}

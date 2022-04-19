package s4_18;

import java.util.Scanner;

public class Startprogram {
	Orderdata orderItem = new Orderdata();

	void startprogram() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("방문 예정일을 입력하여주세요.(ex.20220414)");
		int visiting_Date = scanner.nextInt();// 현재날짜 불러오는 것을 아직 할 줄 모른다. 그래서 날짜 입력받음.

		while (true) {
			while (true) {
				orderItem.try_Number = orderItem.try_Number + 1; // 첫번째 회차 ,,다시발권할때는 두번째회차,,
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
		System.out.printf("권종을 선택하세요\n");
		System.out.printf("1. 주간권\n");
		System.out.printf("2. 야간권\n");
//		try {
		orderItem.ticket_Type = scanner.nextInt();
//		}catch(NullPointerException e) {
//		System.out.println("기존코드를 체크해주세요!");
//		}finally{
//		System.out.println("예외처리코드가 오류없이 진행되었습니다.");
//		}
		orderItem.ticket_Typearr[orderItem.try_Number - 1] = orderItem.ticket_Type;
		return orderItem.ticket_Type;
	}

	int inputIdNumber(int visiting_Date) {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("주민번호를 입력하세요\n");
		orderItem.id_Number = scanner.nextLine();// <<나이계산>>
		int birth_Date = (orderItem.id_Number.charAt(0) - 48) * 100000 + (orderItem.id_Number.charAt(1) - 48) * 10000
				+ (orderItem.id_Number.charAt(2) - 48) * 1000 + (orderItem.id_Number.charAt(3) - 48) * 100
				+ (orderItem.id_Number.charAt(4) - 48) * 10 + (orderItem.id_Number.charAt(5) - 48) * 1;// 아스키코드 이용 94출력
		if (orderItem.id_Number.charAt(6) <= '2') {
			birth_Date += 19000000;
		} else
			birth_Date += 20000000; // 몇년대 생인지 확인
		orderItem.age = (visiting_Date / 10000) - (birth_Date / 10000);
		if ((visiting_Date % 10000) < (birth_Date % 10000)) {
			orderItem.age = orderItem.age - 1;
		} else
			orderItem.age = orderItem.age;// 생일 지났는지 확인
		System.out.printf("만나이는 %d입니다.\n", orderItem.age);// 만나이 계산
		return orderItem.age;
	}

	int inputHowmanyTickets() {
		Scanner scanner = new Scanner(System.in);
		while (true) {// <<티켓 수량 부분 시작>>
			System.out.printf("몇개를 주문하시겠습니까?(최대 10개)\n");
			orderItem.numberof_Tickets = scanner.nextInt();
			orderItem.count_Ticketarr[orderItem.try_Number - 1] = orderItem.numberof_Tickets;// 첫번째 회차의 티켓수량이
																								// count_Ticketarr배열
																								// 첫자리에 입력됨
			if (0 < orderItem.numberof_Tickets && orderItem.numberof_Tickets < 11) {
				orderItem.ticket_Price = orderItem.ticket_Price * orderItem.numberof_Tickets;
				break;
			} else
				System.out.printf("다시 입력해주세요");
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
						StaticValue.ADULTDAY, StaticValue.SENIORDAY }, // 주간 가격표
				{ StaticValue.BABYNIGHT, StaticValue.BABYNIGHT, StaticValue.CHILDNIGHT, StaticValue.TEENNIGHT,
						StaticValue.ADULTNIGHT, StaticValue.SENIORNIGHT },// 야간 가격표
		};
		System.out.printf("가격은 %d원입니다.", price_Arr[a - 1][b - 1]);
		orderItem.human_Typearr[orderItem.try_Number - 1] = orderItem.human_Type;// 첫번째 회차의 사람종류가 human_Typearr배열 첫자리에
																					// 입력됨. 두번째회차에는 배열 두번째
		// 자리에
		// 입력됨.
		// 세번째회차는..
		return price_Arr[a - 1][b - 1]; // ex)2행6열은 야간-노인 가격이다.
	}

	int ticketDiscount(int specialtype, int price) {
		if (specialtype == StaticValue.HANDY || specialtype == StaticValue.HEROE
				|| specialtype == StaticValue.PREGNANT) {// 장애인,국가유공자일 경우
			price = (int) ((double) price * StaticValue.DISCOUNT_RATEOF_HANDY_OR_HEROE);
		}
		if (specialtype == StaticValue.ARMY) {// 휴가장병일경우
			price = (int) (price * StaticValue.DISCOUNT_RATEOF_ARMY);
		}
		if (specialtype == StaticValue.MANYCHILD) {// 다둥이행복카드일경우
			price = (int) (price * StaticValue.DISCOUNT_RATEOF_MANYCHILD);
		}
		return price;
	}

	int discount_uv() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("우대사항을 선택하세요\n");
		System.out.printf("1.없음\n");
		System.out.printf("2.장애인\n");
		System.out.printf("3.국가유공자\n");
		System.out.printf("4.다둥이 행복카드\n");
		System.out.printf("5.임산부\n");
		System.out.printf("6.휴가장병 우대\n");
		orderItem.prefer_Number = scanner.nextInt();
		orderItem.special_Typearr[orderItem.try_Number - 1] = orderItem.prefer_Number;// 첫번째 회차의 우대사항이 special_Typearr배열
																						// 첫자리에 입력됨.
		orderItem.ticket_Price = ticketDiscount(orderItem.prefer_Number, orderItem.ticket_Price);
		orderItem.ticket_Pricearr[orderItem.try_Number - 1] = orderItem.ticket_Price;// 첫번째회차의 티켓가격이 ticket_Pricearr배열
																						// 첫자리에 입력됨.
		orderItem.total_Price = orderItem.total_Price + orderItem.ticket_Price;
		return orderItem.ticket_Price;
	}

	int continueorNot() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("계속 발권하시겠습니까?\n");
		System.out.printf("1.티켓발권\n");
		System.out.printf("2.종료\n");

		int continue_or_Not = scanner.nextInt();
		return continue_or_Not;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	int printingTicket() {
		System.out.printf("티켓발권을종료합니다. 감사합니다.\n\n");
		System.out.printf("===================롯데월드===================\n");
		for (int i = 0; i < orderItem.try_Number; i++) {
			System.out.printf("%s %-6s X %-2d   %-6d    %-10s\n", StaticValue.ticketType(orderItem.ticket_Typearr[i]),
					StaticValue.humanType(orderItem.human_Typearr[i]), orderItem.count_Ticketarr[i],
					orderItem.ticket_Pricearr[i], StaticValue.specialType(orderItem.special_Typearr[i]));
		}
		System.out.printf("입장료 총액은 %d원입니다.\n", orderItem.total_Price);
		System.out.printf("==============================================\n\n");

		return 0;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	int finshorNot() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("계속 진행(1: 새로운 주문, 2: 프로그램 종료) : ");
		int finish_or_Not = scanner.nextInt();
		return finish_or_Not;
	}

	int closingment() {
		System.out.printf("프로그램을 종료합니다.\n");
		return 0;
	}

}

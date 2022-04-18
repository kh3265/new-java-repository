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
		System.out.printf("방문 예정일을 입력하여주세요.(ex.20220414)");
		int visiting_Date = scanner.nextInt();// 현재날짜 불러오는 것을 아직 할 줄 모른다. 그래서 날짜 입력받음.

		while (true) {
			while (true) {
				try_Number = try_Number + 1; // 첫번째 회차 ,,다시발권할때는 두번째회차,,
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
		System.out.printf("권종을 선택하세요\n");
		System.out.printf("1. 주간권\n");
		System.out.printf("2. 야간권\n");
		int ticket_Type = scanner.nextInt();
		ticket_Typearr[try_Number - 1] = ticket_Type;
		return ticket_Type;
	}

	public static int inputIdNumber(int visiting_Date) {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("주민번호를 입력하세요\n");
		String id_Number = scanner.nextLine();// <<나이계산>>
		int birth_Date = (id_Number.charAt(0) - 48) * 100000 + (id_Number.charAt(1) - 48) * 10000
				+ (id_Number.charAt(2) - 48) * 1000 + (id_Number.charAt(3) - 48) * 100 + (id_Number.charAt(4) - 48) * 10
				+ (id_Number.charAt(5) - 48) * 1;// 아스키코드 이용 94출력
		if (id_Number.charAt(6) <= '2') {
			birth_Date += 19000000;
		} else
			birth_Date += 20000000; // 몇년대 생인지 확인
		age = (visiting_Date / 10000) - (birth_Date / 10000);
		if ((visiting_Date % 10000) < (birth_Date % 10000)) {
			age = age - 1;
		} else
			age = age;// 생일 지났는지 확인
		System.out.printf("만나이는 %d입니다.\n", age);// 만나이 계산
		return age;
	}

	public static int inputHowmanyTickets() {
		Scanner scanner = new Scanner(System.in);
		while (true) {// <<티켓 수량 부분 시작>>
			System.out.printf("몇개를 주문하시겠습니까?(최대 10개)\n");
			int numberof_Tickets = scanner.nextInt();
			count_Ticketarr[try_Number - 1] = numberof_Tickets;// 첫번째 회차의 티켓수량이 count_Ticketarr배열 첫자리에 입력됨
			if (0 < numberof_Tickets && numberof_Tickets < 11) {
				ticket_Price = ticket_Price * numberof_Tickets;
				break;
			} else
				System.out.printf("다시 입력해주세요");
			continue;
		}
		return numberof_Tickets;
	}

	public static String ticketType(int a) {
		String[] ticket = { "주간권", "야간권" };
		return ticket[a - 1];
	}

	public static String humanType(int a) {
		String[] humantype = { "아기", "아기", "어린이", "청소년", "성인", "노인" };
		return humantype[a - 1];
	}

	public static String specialType(int a) {
		String[] specialtype = { "없음", "장애인우대적용", "국가유공자우대적용", "다둥이행복카드적용", "임산부우대적용", "휴가장병우대적용" };
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
		int[][] price_Arr = { { BABYDAY, BABYDAY, CHILDDAY, TEENDAY, ADULTDAY, SENIORDAY }, // 주간 가격표
				{ BABYNIGHT, BABYNIGHT, CHILDNIGHT, TEENNIGHT, ADULTNIGHT, SENIORNIGHT },// 야간 가격표
		};
		System.out.printf("가격은 %d원입니다.", price_Arr[a - 1][b - 1]);
		human_Typearr[try_Number - 1] = human_Type;// 첫번째 회차의 사람종류가 human_Typearr배열 첫자리에 입력됨. 두번째회차에는 배열 두번째 자리에 입력됨.
													// 세번째회차는..
		return price_Arr[a - 1][b - 1]; // ex)2행6열은 야간-노인 가격이다.
	}

	public static int ticketDiscount(int specialtype, int price) {
		if (specialtype == HANDY || specialtype == HEROE) {// 장애인,국가유공자일 경우
			price = (int) ((double) price * 0.5);
		}
		if (specialtype == PREGNANT) {// 임산부일 경우
			price = (int) (price * 0.5);
		}
		if (specialtype == ARMY) {// 휴가장병일경우
			price = (int) (price * 0.51);
		}
		if (specialtype == MANYCHILD) {// 다둥이행복카드일경우
			price = (int) (price * 0.7);
		}
		return price;
	}

	public static int discount_uv() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("우대사항을 선택하세요\n");
		System.out.printf("1.없음\n");
		System.out.printf("2.장애인\n");
		System.out.printf("3.국가유공자\n");
		System.out.printf("4.다둥이 행복카드\n");
		System.out.printf("5.임산부\n");
		System.out.printf("6.휴가장병 우대\n");
		int prefer_Number = scanner.nextInt();
		special_Typearr[try_Number - 1] = prefer_Number;// 첫번째 회차의 우대사항이 special_Typearr배열 첫자리에 입력됨.
		ticket_Price = ticketDiscount(prefer_Number, ticket_Price);
		ticket_Pricearr[try_Number - 1] = ticket_Price;// 첫번째회차의 티켓가격이 ticket_Pricearr배열 첫자리에 입력됨.
		total_Price = total_Price + ticket_Price;
		return ticket_Price;
	}

	public static int continueorNot() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("계속 발권하시겠습니까?\n");
		System.out.printf("1.티켓발권\n");
		System.out.printf("2.종료\n");

		int continue_or_Not = scanner.nextInt();
		return continue_or_Not;
	}

	public static int printingTicket() {
		System.out.printf("티켓발권을종료합니다. 감사합니다.\n\n");
		System.out.printf("===================롯데월드===================\n");
		for (int i = 0; i < try_Number; i++) {
			System.out.printf("%s %-6s X %-2d   %-6d    %-10s\n", ticketType(ticket_Typearr[i]),
					humanType(human_Typearr[i]), count_Ticketarr[i], ticket_Pricearr[i],
					specialType(special_Typearr[i]));
		}
		System.out.printf("입장료 총액은 %d원입니다.\n", total_Price);
		System.out.printf("==============================================\n\n");

		return 0;
	}

	public static int finshorNot() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("계속 진행(1: 새로운 주문, 2: 프로그램 종료) : ");
		int finish_or_Not = scanner.nextInt();
		return finish_or_Not;
	}

	public static int closingment() {
		System.out.printf("프로그램을 종료합니다.\n");
		return 0;
	}

}

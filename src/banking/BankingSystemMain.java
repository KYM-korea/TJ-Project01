package banking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {
	public static void showMenu() {
		System.out.println("------------ Menu ------------");
		System.out.print("[1]계좌개설    ");
		System.out.print("[2]입금\t");
		System.out.println("[3]출금");
		System.out.print("[4]전체계좌 정보출력");
		System.out.println("    [5]저장옵션");
		System.out.print("[6]ThreebyThreeGame");
		System.out.println("\t[7]종료");
		System.out.println("------------------------------");
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		AccountManager am = new AccountManager();
		AutoSaver as = new AutoSaver(am);
		
		boolean dt = false;
		
		am.read();
		
		while(true) {
			try {
				showMenu();
				System.out.print("[선택] >> ");
				int choice = sc.nextInt();
				System.out.println();
				
				switch(choice) {
				case ICustomDefine.MAKE:
					System.out.println("*** 신규계좌개설 ***");
					System.out.println("[ 1 ] 보통계좌");
					System.out.println("[ 2 ] 신용신뢰계좌");
					System.out.print("[선택] >> ");
					int mc = sc.nextInt();
					System.out.println();
					if(!(mc == 1 || mc ==2)) {
						MenuSelectException mse = new MenuSelectException();
						throw mse;
					}
					am.makeAccount(mc);
					break;
				case ICustomDefine.DEPOSIT:
					am.depositMoney();
					break;
				case ICustomDefine.WITHDRAW:
					am.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE:
					am.showAccInfo();
					break;
				case ICustomDefine.SAVE:
					if(Thread.activeCount()==2) {
						dt = true;
					}
					if(dt && Thread.activeCount()==1) {
						as = new AutoSaver(am);
						dt= false;
					}
					am.SaveD(as);
					break;
				case ICustomDefine.GAME_START:
//					System.out.println("[게임 난이도]");
//					System.out.println("[1]초급(3x3)  [2]중급(4x4)  [3]고급(5x5)");
//					System.out.print("[선택] >> "); int diff = sc.nextInt();
//					if(diff < 0 || diff > 3) {
//						MenuSelectException mse = new MenuSelectException();
//						throw mse;
//					}
					TbyT_Game ttg = new TbyT_Game(1);
					ttg.Start(ttg);;
					break;
				case ICustomDefine.EXIT:
					am.save();
					System.out.println("[프로그램 종료]");
					return;
				default:
					MenuSelectException mse = new MenuSelectException();
					throw mse;
				}
			}catch(InputMismatchException e) {
				System.err.println("[예외발생]문자 입력 불가");
				sc.nextLine();
			}catch(MenuSelectException e) {
				System.out.println(e.getMessage());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
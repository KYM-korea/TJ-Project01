package banking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {
	HashSet<Account> ac = new HashSet<Account>();
	
	public void read() {
		try {
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("src/AccountInfo.obj"));
			while(true) {
				Account acc = (Account)in.readObject();
				ac.add(acc);
			}
		}catch(ClassNotFoundException e) {
			System.err.println("[클래스 정보 없음]");
		}catch(FileNotFoundException e) {
			System.err.println("[저장된 정보 없음]");
		}catch(IOException e) {
			System.out.println("[계좌정보 호출 완료]");
		}
	}
	
	public void save() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("src/AccountInfo.obj"));
			for(Account acc : ac) {
				out.writeObject(acc);
			}
			out.close();
		}catch(IOException e) {
			e.printStackTrace();
			System.err.println("[입력 예외 발생]");
		}
	}
	
	
	public void autosave() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter("src/AutoSaveAccount.txt"));
			
			for(Account acc : ac) {
				out.println(acc);
			}
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void makeAccount(int num) {
		Scanner sc1 = new Scanner(System.in);
		try {
			String m_n, n, t_g;
			int m, r;
			System.out.print("[계좌번호] >> ");  m_n = sc1.nextLine();
			System.out.print("[고객이름] >> ");  n = sc1.nextLine();
			System.out.print("[잔고] >> ");  m = sc1.nextInt();
			if(m < 0) {
				MenuSelectException mse = new MenuSelectException();
				throw mse;
			}
			System.out.println("정수형식으로 입력");
			System.out.print("[기본이자]% >> ");  r= sc1.nextInt();
			sc1.nextLine();
			
			if(num == 1) {
				NormalAccount na = new NormalAccount(n, m_n, m, r);
				
				if(ac.add(na)) {
					ac.add(na);
				}else {
					System.out.println("[중복된 계좌 발견]\n[덮어쓰기 (Y/N)]");
					String check_id=sc1.nextLine();
					if(check_id.substring(0, 1).equalsIgnoreCase("Y")) {
						ac.remove(na);
						ac.add(na);
						System.out.println("[덮어쓰기 완료]");
					}else{
						System.out.println("[계좌개설 취소]");
						return;
					}
				}
			}
			
			else if(num == 2 ) {
				System.out.print("[신용등급](A,B,C등급) >> ");
				t_g = sc1.nextLine();
				if(t_g.equalsIgnoreCase("a")||t_g.equalsIgnoreCase("b")||t_g.equalsIgnoreCase("c")) {
					HighCreditAccount ha = new HighCreditAccount(n, m_n, m, r, t_g);
					if(ac.add(ha)) {
						ac.add(ha);
					}else {
						System.err.println("[중복된 계좌 발견]\n[덮어쓰기 (Y/N)]");
						String check_id=sc1.nextLine();
						if(check_id.substring(0, 1).equalsIgnoreCase("Y")) {
							ac.remove(ha);
							ac.add(ha);
							System.out.println("[덮어쓰기 완료]");
						}else{
							System.out.println("[계좌개설 취소]");
							return;
						}
					}
				}else {
					System.err.println("[등급표기 오류]");
					System.out.println();
					return;
				}
			}
			
			System.out.println("[계좌개설 완료]");
			System.out.println();
		}catch(InputMismatchException e) {
			System.err.println("[입력 오류]");
			System.out.println();
		}catch(Exception e) {
			sc1.nextLine();
		}
	}
	
	public void showAccInfo() {
		System.out.println("******************************");
		System.out.println("*        계좌정보출력        *");
		System.out.println("******************************");
		for(Account acc : ac) {
			acc.showAccInfo();
		}
		System.out.println("[전체 계좌정보 출력 완료]");
		System.out.println();
	}
	
	public void depositMoney() {
		Scanner sc1 = new Scanner(System.in);
		try {
			System.out.println("[계좌번호와 입금할 금액 입력]");
			System.out.print("[계좌번호] >> ");String m_n = sc1.nextLine();
			System.out.print("[입 금 액] >> ");int m = sc1.nextInt();
			if(m < 0 || (m%500)!=0) {
				MenuSelectException mse = new MenuSelectException();
				throw mse;
			}
			for(Account acc : ac) {
				if(acc.getM_num().equals(m_n)) {
					acc.add_m(m);
					System.out.println("**********************************");
					System.out.println("\t["+m+"원 입금 완료]");
					System.out.println("\t[잔고 :"+acc.getMoney()+"원]");
					System.out.println("**********************************");
					System.out.println();
					return;
				}
			}
			System.err.println("[계좌번호 미존재]");
			System.out.println();
		}catch(InputMismatchException e) {
			System.err.println("[예외발생]문자는 입력할 수 없습니다.");
			sc1.nextLine();
			System.out.println();
		}catch(MenuSelectException e) {
			sc1.nextLine();
		}
	}
	
	public void withdrawMoney() {
		Scanner sc1 = new Scanner(System.in);		
		try {
			System.out.println("[계좌번호와 출금할 금액 입력]");
			System.out.print("[계좌번호] >> "); String m_n = sc1.nextLine();
			System.out.print("[출금액] >> "); int m = sc1.nextInt(); sc1.nextLine();
			if(m < 0 || (m%1000)!=0) {
				MenuSelectException mse = new MenuSelectException();
				throw mse;
			}
			for(Account acc : ac) {
				if(acc.getM_num().equals(m_n)) {
					if(acc.getMoney() < m) {
						System.err.println("[잔고 부족]\n[금액 전체 출금 (Y/N)]");
						String yn = sc1.nextLine();
						if(yn.substring(0, 1).equalsIgnoreCase("Y")) {
							System.out.println("[잔고: " + acc.getMoney()+"원 전부 출금]");
							acc.setMoney(0);
							return;
						}else{
							System.out.println("[출금 취소]");
							return;
						}
					}else {	
						acc.setMoney(acc.getMoney()-m);
					}
					System.out.println("**********************************");
					System.out.println("\t["+m+"원 출금 완료]");
					System.out.println("\t[잔고 :"+acc.getMoney()+"원]");
					System.out.println("**********************************");
					System.out.println();
					return;
				}
			}
			System.err.println("[계좌번호 미존재]");
			System.out.println();
		}catch(InputMismatchException e) {
			System.err.println("[예외발생]문자는 입력할 수 없습니다.");
			sc1.nextLine();
			System.out.println();
		}catch(Exception e) {
		}
	}

	public void SaveD(AutoSaver as) {		
		Scanner sc1 = new Scanner(System.in);
		System.out.println("[1]자동저장on");
		System.out.println("[2]자동저장off");
		System.out.print("선택 >> ");
		int deamonC = sc1.nextInt();
		
		if(deamonC==1) {
			if(as.isAlive()) {
				System.err.println("[이미 자동저장 실행중]");
			}else {
				as.setDaemon(true);
				as.start();
			}
		}else if(deamonC==2) {
			if(as.isAlive()) {
				as.interrupt();
				System.out.println("[자동저장 종료]");
			}else {
				System.err.println("[자동저장 미실행 중]");
			}
		}
		System.out.println();
	}
}

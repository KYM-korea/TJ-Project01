package banking;

import java.util.ArrayList;
import java.util.Scanner;

public class TbyT_Game {
	
	ArrayList<String> arr = new ArrayList<String>();
	int ch_index, suffle = 50;
	
	public TbyT_Game(int diff) {
		int c=9;
		switch(diff) {
		case 1:c = 9;break;
		case 2:c = 16;break;
		case 3:c = 25;break;
		}
		for(int i = 1 ; i <= c ; i++) {
			if(i==c) arr.add("X");
			else arr.add(String.valueOf(i));
		}	
	}
	
	public void sort(TbyT_Game a) {
		for(int i = 0 ; i < suffle; i++) {
			int r = (int)(Math.random()*5)%4+1;
			switch(r) {
			case 1:
				a.move_ch("A");
				break;
			case 2:
				a.move_ch("S");
				break;
			case 3:
				a.move_ch("W");
				break;
			case 4:
				a.move_ch("D");
				break;
			}
			if(ch_index==-1) i--;
		}
	}
	
	public void map() {
		for(int i = 1 ; i < Math.sqrt(arr.size()) ; i++) {
		System.out.print("========");		
		}
		System.out.println("==");
		for(int i = 1 ; i <= arr.size() ; i++) {
			if(i%(Math.sqrt(arr.size()))==0) {
				System.out.print(arr.get(i-1)+"\t");
				System.out.println();
			}else {
				System.out.print(arr.get(i-1)+"\t");
			}
		}
		for(int i = 1 ; i < Math.sqrt(arr.size()) ; i++) {
			System.out.print("========");		
		}
		System.out.println("==\n");
	}
	
	public boolean move_ch(String choice) {
		ch_index = -1;
		
		int mod = (int)(Math.sqrt(arr.size()));
		
		for(String check : arr) {
			if(check.equals("X")) {
				ch_index = arr.indexOf(check);
				break;
			}
		}
		
		if(choice.equalsIgnoreCase("W") && ch_index < arr.size()-mod) {
			move(choice);
		}else if(choice.equalsIgnoreCase("S") && ch_index > mod-1) {
			move(choice);
		}else if(choice.equalsIgnoreCase("A") && ((ch_index%mod)!=mod-1)) {
			move(choice);
		}else if(choice.equalsIgnoreCase("D") && ((ch_index%mod)!=0)) {
			move(choice);
		}else {
			ch_index = -1;
			return false;
		}
		return true;
	}
	public void move(String check) {
		int move=0;
		
		switch(check) {
		case "W","w":
			move = (int)Math.sqrt(arr.size()); break;
		case "A","a":
			move = 1; break;
		case "S","s":
			move = -(int)Math.sqrt(arr.size()); break;
		case "D","d":
			move = -1; break;
		}
		
		String tmp = arr.get(ch_index+move);
		arr.add(ch_index+move,arr.get(ch_index));
		arr.remove(ch_index+move+1);
		arr.add(ch_index, tmp);
		arr.remove(ch_index+1);
		
	}
	
	public boolean collect() {
		int[] check = new int[arr.size()-1];
		
		for(int i = 0 ; i < arr.size() -1 ; i++) {
			if(arr.get(i).equals("X")) {
				return false;
			}
			
			check[i]=Integer.parseInt(arr.get(i));
			
			for(int j = 0 ; j < i ; j++) {
				if(check[j]>check[i]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void Start(TbyT_Game tg) {
		Scanner sc = new Scanner(System.in);
		
		String c;
		while(true) {
			tg.sort(tg);
			
			boolean clear = false;
			
			for(int i = 100 ; i > 0; i--) {
				System.out.println("[남은횟수 : "+i+"]");
				tg.map();
				System.out.println("[명령어] a:Right d:left s:up w:down x:exit");
				System.out.print("[선택] >> ");
				c = sc.nextLine();
				if(c.equalsIgnoreCase("x")) {return;}
				else if(c.equalsIgnoreCase("W")||c.equalsIgnoreCase("A")||
						c.equalsIgnoreCase("S")||c.equalsIgnoreCase("D")){
					if(!(tg.move_ch(c))){
						i++;
						System.err.println("[이동 불가]");
						System.out.println();
					}
				}
				else {
					i++;
					System.err.println("[정해진 값을 입력]");
					System.out.println();
					continue;
				}
				if(tg.collect()) {
					System.out.println("┌─────────────────┐");
					System.out.println("│******정답*******│");
					System.out.println("│※※  퍼즐 해결  ※※│");
					System.out.println("└─────────────────┘");
					tg.map();
					clear = true;
					break;
				}
			}
			if(!clear) {
				System.err.println("┌─────────────────┐");
				System.err.println("│※※    실패     ※※│");
				System.err.println("└─────────────────┘");
				tg.map();
			}
			System.out.println("[재시작을 희망시 Y를 입력 나머지는 종료]");
			String cc = sc.nextLine();
			if(cc.substring(0,1).equalsIgnoreCase("Y")) {
				continue;
			}else {
				System.out.println("[게임 종료]");
				System.out.println();
				return;
			}
		}
	}
}

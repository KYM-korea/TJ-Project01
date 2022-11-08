package banking;

import java.util.Arrays;
import java.util.Scanner;

public class test_TbyT {
	
	String[][] arr;
	int X=2,Y=2;
	
	public test_TbyT() {
		
		int input_num;
		
		arr= new String[3][3];
		
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3; j++) {
				input_num = j+1+(i*3);
				arr[i][j] = Integer.toString(input_num);
			}
		}
		arr[2][2]="X";
	}
	
	public void show_map() {
		for(int i = 0 ; i < arr.length; i++) {
			for(int j = 0 ; j < arr[i].length ; j++) {
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public void move_ch(String choice) {
		
		if(Y!=2 && choice.equalsIgnoreCase("A")) {
			String tmp = arr[X][Y+1];
			arr[X][Y+1]="X";
			arr[X][Y]=tmp;
			Y++;
		}else if(Y!=0 && choice.equalsIgnoreCase("D")) {
			String tmp = arr[X][Y-1];
			arr[X][Y-1]="X";
			arr[X][Y]=tmp;
			Y--;
		}else if(X!=2 && choice.equalsIgnoreCase("W")) {
			String tmp = arr[X+1][Y];
			arr[X+1][Y]="X";
			arr[X][Y]=tmp;
			X++;
		}else if(X!=0 && choice.equalsIgnoreCase("S")) {
			String tmp = arr[X-1][Y];
			arr[X-1][Y]="X";
			arr[X][Y]=tmp;
			X--;
		}else {
			System.out.println("잘못된 움직임입니다.");
		}
		
	}
	
	public void suffle() {
		for(int i = 0 ; i < 3 ; i++) {
			int r = (int)(Math.random()*5)%4+1;
			switch(r) {
			case 1:move_ch("A");break;
			case 2:move_ch("S");break;
			case 3:move_ch("D");break;
			case 4:move_ch("W");break;
			}
		}
	}
	
	public boolean collect_ch() {
		String[][] arr2 = {{"1","2","3"},{"4","5","6"},{"7","8","X"}};
		if(Arrays.deepEquals(arr, arr2)) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		test_TbyT ttt = new test_TbyT();
		ttt.suffle();
		while(true) {
			ttt.show_map();
			
			System.out.println("움직임을 입력하세요.");
			String select = sc.nextLine();
			
			ttt.move_ch(select);
			if(ttt.collect_ch()) {
				System.out.println("정답");
				System.out.println("종료합니다.");
				return;
			}
		}
	}
}
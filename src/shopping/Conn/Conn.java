package shopping.Conn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Conn implements Inter_Connect{
	public Connection con;
	public ResultSet rs;
	public PreparedStatement ps;
	public CallableStatement cs;
	
	public Conn(String id, String pass) {
		try {
			Class.forName(ORACLE_DRIVER);
			connect_r(id,pass);
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public void connect_r(String id, String pass) {
		try {
			con = DriverManager.getConnection(Oracle_URL, id, pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void execute() {}
	
	public void close() {
		try {
			if(con!=null) con.close();
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(cs!=null) cs.close();
		}catch(Exception e) {
			System.out.println("DB연결끊기 도중 오류 발생");
		}
	}
	
	public String scan(String title) {
		Scanner sc = new Scanner(System.in);
		System.out.println(title+"을(를) 입력 (exit->종료)");
		String input=sc.nextLine();
		if("EXIT".equalsIgnoreCase(input)) {
			System.out.println("프로그램을 종료합니다.");
			close();
			System.exit(0);
		}
		return input;
	}
	
}

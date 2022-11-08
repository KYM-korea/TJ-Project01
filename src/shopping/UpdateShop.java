package shopping;

import java.sql.Types;

import shopping.Conn.Conn;

public class UpdateShop extends Conn{
	
	public UpdateShop() {
		super("education","1234");
	}
	
	public void execute() {
		try {
			String sql = "{call ShopUpdateGoods(?,?,?,?,?)}";
			cs = con.prepareCall(sql);
			cs.setString(1, scan("상품명"));
			cs.setString(2, scan("가격"));
			cs.setString(3, scan("제품코드"));
			cs.setString(4, scan("상품일련번호"));
			cs.registerOutParameter(5, Types.NUMERIC);
			
			cs.execute();
			
			int rc = cs.getInt(5);
			
			System.out.println("실행된 행의 개수 >> "+rc);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}   
	
	public static void main(String[] args) {
		UpdateShop us = new UpdateShop();
		us.execute();		
	}
}

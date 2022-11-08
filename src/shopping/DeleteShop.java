package shopping;

import java.sql.Types;

import shopping.Conn.Conn;

public class DeleteShop extends Conn{
	
	public DeleteShop() {
		super("education", "1234");
	}
	
	public void execute() {
		try {
			
			String sql = "{CALL ShopDeleteGoods(?,?)}";
			
			cs = con.prepareCall(sql);
			cs.setString(1, scan("삭제할 상품 일련번호"));
			cs.registerOutParameter(2, Types.NUMERIC);
			cs.execute();
			
			int rc = cs.getInt(2);
			
			System.out.println("실행된 행의 개수 >> " + rc);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public static void main(String[] args) {
		DeleteShop ds = new DeleteShop();
		ds.execute();
	}
}

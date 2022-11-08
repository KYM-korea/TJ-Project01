package shopping.Prepare;

import shopping.Conn.Conn;

public class InsertShop extends Conn{
	public InsertShop() {
		super("education", "1234");
	}
	
	public void execute() {
		try {
			String query = "INSERT INTO sh_goods(g_idx, goods_name, goods_price, p_code) "
					+ " VALUES (seq_total_idx.nextval, ?, ?, ?)";
			ps = con.prepareStatement(query);
			ps.setString(1, scan("상품명"));
			ps.setString(2, scan("상품가격"));
			ps.setString(3, scan("상품코드"));
			
			int rCount=ps.executeUpdate();
			System.out.println("실행된 행의 개수 >> "+rCount);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public static void main(String[] args) {
		InsertShop is = new InsertShop();
		is.execute();
	}
}
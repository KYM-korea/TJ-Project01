package shopping.State;

import java.sql.Statement;

import shopping.Conn.Conn;

public class SelectShop extends Conn{
	public SelectShop() {
		super("education","1234");
	}
	
	public void execute() {
		try {
			Statement st = con.createStatement();
			
			String in_name=scan("조회할 상품명");
			
			String query = "SELECT g_idx, goods_name, "
					+ " ltrim(to_char(goods_price,'999,990,000')),"
					+ " to_char(regidate, 'yyyy-mm-dd hh24:mi'),p_code "
					+ "FROM sh_goods WHERE goods_name LIKE '%"+in_name+"%'";
			
			rs=st.executeQuery(query);
			while(rs.next()) {
				String idx = rs.getString(1);
				String name = rs.getString(2);
				String price = rs.getString(3);
				String regidate = rs.getString(4);
				String p_code = rs.getString(5);
				System.out.printf("일련번호: %s || 상품명: %s || 가격: %s\n등록일:%s \t 제품코드: %s \n",
						idx, name, price, regidate, p_code);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public static void main(String[] args) {
		SelectShop ss = new SelectShop();
		ss.execute();
	}
}

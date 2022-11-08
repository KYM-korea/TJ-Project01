package banking;

public class NormalAccount extends Account{
	public NormalAccount(String n, String m_n, int m, int r) {
		// TODO Auto-generated constructor stub
		super(m_n, n, m, r);
	}
		
	@Override
	public void showAccInfo() {
		System.out.println("[일반계좌]");
		super.showAccInfo();
		System.out.println("==============================");
	}
	public void add_m(int money) {
		int g_m=getMoney();
		int g_r=getRate();
		g_m = g_m+money+(g_m*g_r/100);
		super.setMoney(g_m);
	}
}

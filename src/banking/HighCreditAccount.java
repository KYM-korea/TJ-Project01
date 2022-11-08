package banking;

public class HighCreditAccount extends Account{
	String t_grade;
	public HighCreditAccount(String n, String m_n, int m, int r, String t) {
		// TODO Auto-generated constructor stub
		super(m_n,n,m,r);
		t_grade = t;
	}
	@Override
	public void showAccInfo() {
		// TODO Auto-generated method stub
		System.out.println("[신용계좌 (등급 : "+ t_grade+") ]");
		super.showAccInfo();
		System.out.println("==============================");
	}
	
	public void add_m(int money) {
		int g_m=getMoney();
		int g_r=getRate();
		
		if (t_grade.equalsIgnoreCase("A")){
			g_m = g_m+money+(g_m*g_r/100)+(g_m*(ICustomDefine.A)/100);
		}else if(t_grade.equalsIgnoreCase("B")) {
			g_m = g_m+money+(g_m*g_r/100)+(g_m*(ICustomDefine.B)/100);
		}else {
			g_m = g_m+money+(g_m*g_r/100)+(g_m*(ICustomDefine.C)/100);
		}
		
		super.setMoney(g_m);
	}
	
	public String toString() {
		return super.toString()+"\t 신용등급:"+t_grade;
	}
}

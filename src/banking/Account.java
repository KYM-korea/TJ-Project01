package banking;

import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Serializable{
	
	private String name, m_num;
	private int money,rate;
	
	public Account(String m_n, String n, int m, int r) {
		name = n;
		m_num=m_n;
		money=m;
		rate=r;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getM_num() {
		return m_num;
	}

	public void setM_num(String m_num) {
		this.m_num = m_num;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public void showAccInfo() {
		System.out.println("계좌번호 : " + m_num+"\t고객이름 : " + name);
		System.out.println("잔고 : " + money+"\t기본이자 : "+ rate+"%");
	}
	public String toString() {
		return "계좌번호:" + getM_num() +"\t 계좌주:"+ getName() +"\t 잔고:"+ getMoney() +"\t 기본이자율:"+ getRate()+"%";
	}
	public int hashCode() {
		int hash = Objects.hash(m_num);
		return hash;
	}
	
	public boolean equals(Object obj) {
		Account account = (Account)obj;
		if((account.m_num).equals(m_num)) {
			return true;
		}
		return false;
	}
	public abstract void add_m(int money);
}

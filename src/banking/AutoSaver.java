package banking;

public class AutoSaver extends Thread {
	AccountManager amas;
	
	public AutoSaver(AccountManager am) {
		amas=am;
	}
	
	public void run(){
		while(true) {
			try {
				amas.autosave();
				sleep(5000);
			}catch(InterruptedException e){
				return;
			}
			
		}
	}
}

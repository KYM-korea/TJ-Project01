package banking;

public class MenuSelectException extends Exception {
	public MenuSelectException() {
		System.err.println("[예외발생] 지정 외 숫자가 입력되었습니다.");
		System.out.println();
	}
}
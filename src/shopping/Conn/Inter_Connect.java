package shopping.Conn;

public interface Inter_Connect {

	String ORACLE_DRIVER="oracle.jdbc.OracleDriver";
	String Oracle_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	void execute();
	void close();
	
}
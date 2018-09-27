
public class MainClienteTCP{
	public static void main(String[] args) {
		ClienteTCP C = new ClienteTCP("127.0.0.1",9090);
		C.iniciar();
	}

}

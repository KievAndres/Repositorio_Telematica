import java.io.IOException;
import java.net.UnknownHostException;


public class MainClienteUDP {
	public static void main(String[] args) throws IOException {
		ClienteUDP C = new ClienteUDP("127.0.0.1",9999);
		C.iniciar();
	}

}

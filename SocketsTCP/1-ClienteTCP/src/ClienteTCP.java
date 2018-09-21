import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {
	Socket sCliente;
	Scanner entrada;
	PrintStream salida;
	String host;
	int puerto;
	String mensajeSolicitud = "";
	String mensajeRespuesta= "";
	public ClienteTCP(String h,int p) {
		host = h;
		puerto = p;
	}
	public void iniciar(){
		try {
			//Estableciendo conexion con el servidor
			sCliente = new Socket(host,puerto);
			System.out.println("********CONEXION INICIADA********");
			//Mostramos el ip y el puerto que nos ayudaran a la conexion
			System.out.println("Conectado a: " + sCliente.getRemoteSocketAddress());
			//Obtengo una regerencia a los flujos de datos de entrada y salida
			salida = new PrintStream(sCliente.getOutputStream());
			entrada = new Scanner(sCliente.getInputStream());
			//////////////////////////////////////////////////////////////////
			//Este bloque de codigo se encarga de enviar mensajes al servidor
			Scanner lectura = new Scanner(System.in);
			System.out.print("\nCual es tu nombre :");
			mensajeSolicitud = lectura.nextLine();
			salida.println(mensajeSolicitud);
			mensajeRespuesta = entrada.nextLine();
			System.out.println("Respuesta del servidor: "+mensajeRespuesta);
		} catch (Exception e) {
			e.printStackTrace();
			finalizar();
		}
	}
	public void finalizar(){
		try {
			salida.close();
			entrada.close();
			sCliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

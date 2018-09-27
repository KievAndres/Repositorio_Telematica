import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorTCP {
	ServerSocket sServidor;
	Socket sCliente;
	int puerto;
	PrintStream salida;
	Scanner entrada;
	String mensajeSolicitud = "";
	String mensajeRespuesta = "";
	
	public ServidorTCP(int p){
		puerto = p;
	}
	public void iniciar(){
		try {
			sServidor = new ServerSocket(puerto);
			System.out.println("- SERVIDOR TCP INICIADO -");
			System.out.println("- Esperando Cliente -");
			while(true){
				sCliente = sServidor.accept();
				entrada = new Scanner(sCliente.getInputStream());
				salida = new PrintStream(sCliente.getOutputStream());
		
				mensajeSolicitud = entrada.nextLine();
				
				System.out.println("Solicitud del Cliente :"+mensajeSolicitud);
				mensajeRespuesta = "Bienvenido "+ mensajeSolicitud;
				salida.println(mensajeRespuesta);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			finalizar();
		}
		finally{
			finalizar();
		}
	}
	public void finalizar(){
		try {
			salida.close();
			entrada.close();
			sServidor.close();
			sCliente.close();
			System.out.println("Conexion Finalizada...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int sumar(int a,int b){
		return a+b;
	}
}

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ClienteUDP {
	int puerto;
	InetAddress servidorDestino;
	DatagramSocket socketUDP;
	DatagramPacket paqueteRecibido;
	DatagramPacket paqueteAEnviar;
	public ClienteUDP(String h,int p) throws UnknownHostException {
		servidorDestino = InetAddress.getByName(h);
		puerto = p;
	}
	public void iniciar() throws IOException{
		Scanner sc = new Scanner(System.in);
		//Creamos un socket bajo UDP
			socketUDP = new DatagramSocket();
			//ENVIANDO PAQUETE
			//Creamos un datagrama , con el mensaje, la longitud, la direccion ip y el puerto
			
			System.out.print("\nCual es tu nombre: ");
			String mensajeLeer = sc.nextLine();
			byte mensajeE[] = new byte[1024];
			mensajeE = mensajeLeer.getBytes();
			paqueteAEnviar = new DatagramPacket(mensajeE,mensajeE.length,servidorDestino,puerto);
			//Enviamos el Datagrama
			socketUDP.send(paqueteAEnviar);
			//RECIBIENDO PAQUETE
			byte mensajeR[] = new byte[1024];
			paqueteRecibido = new DatagramPacket(mensajeR,mensajeR.length);
			//Esperamos a que nos llegue respuesta desde el servidor
			socketUDP.receive(paqueteRecibido);
			//Ha llegado un datagrama, para ver los datos se utiliza getData()
			String recibido = new String(paqueteRecibido.getData());
			System.out.println("Respuesta del servidor : "+recibido);
			finalizar();
	}
	public void finalizar(){
		try {
			socketUDP.close();
			System.out.println("Conexion Finalizada...!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

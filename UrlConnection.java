import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnection {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:80/inf273/index.html");
            HttpURLConnection conexionHTTP = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conexionHTTP.getInputStream()));            
            String respuestaLinea;            
            while ((respuestaLinea = in.readLine()) != null) {
                System.out.println(respuestaLinea);
            }
            in.close();           
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}

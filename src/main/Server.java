package main;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Server {

    public static void main(String[] args) throws Exception {
        int puerto = 6789;
        ExecutorService pool = Executors.newFixedThreadPool(3);
        ServerSocket servidor = new ServerSocket(puerto);
        
        try {
            System.out.println("Servidor iniciado en el puerto " + puerto + "\n");
            
            while (true) {
                Socket cliente = servidor.accept();
                pool.execute(new SolicitudHttp(cliente));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            pool.shutdown();
            servidor.close();
        }
        

    }

}

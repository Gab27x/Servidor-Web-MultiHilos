package main;
import java.io.*;
import java.net.*;
import java.util.*;


public final class SolicitudHttp implements Runnable{

        final static String CRLF = "\r\n";
        Socket socket;

        // Constructor
        public SolicitudHttp(Socket socket) throws Exception {
            this.socket = socket;
        }

        // Implementa el método run() de la interface Runnable.
        public void run(){
            try {
                proceseSolicitud();
            } catch (Exception e) {
                System.out.println(e);
            }
        
        }

        private static void enviarBytes(FileInputStream fis, OutputStream os) throws Exception{
            // Construye un buffer de 1KB para guardar los bytes cuando van hacia el socket.
            byte[] buffer = new byte[1024];
            int bytes = 0;

            // Copia el archivo solicitado hacia el output stream del socket.
            while((bytes = fis.read(buffer)) != -1 ) {
                os.write(buffer, 0, bytes);
            }
        }
        
        private String contentType(String fileName) {
            if (fileName.endsWith(".htm") || fileName.endsWith(".html")) {
                return "text/html";
            }
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                return "image/jpeg";
            }
            if (fileName.endsWith(".gif")) {
                return "image/gif";
            }
            if (fileName.endsWith(".png")) {
                return "image/png";
            }
            return "application/octet-stream";
        }

        private void proceseSolicitud() throws Exception {
            // Referencia al stream de salida del socket.
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            // Referencia y filtros (InputStreamReader y BufferedReader)para el stream de entrada.
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
            // Lee la línea de solicitud HTTP
            String lineaDeSolicitud = br.readLine();
            System.out.println("\nLinea de solicitud: " + lineaDeSolicitud + "\n");

            String lineaDelHeader = null;
            while ((lineaDelHeader = br.readLine()).length() != 0) {

                System.out.println("Linea del Header: " + lineaDelHeader);
            }
                
                
                // Extrae el nombre del archivo de la línea de solicitud.
                StringTokenizer partesLinea = new StringTokenizer(lineaDeSolicitud);
                partesLinea.nextToken();  // "salta" sobre el método, se supone que debe ser "GET"
                String nombreArchivo = partesLinea.nextToken();
                nombreArchivo = "." + nombreArchivo;
                FileInputStream fis = null;
                boolean existeArchivo = true;

                try {
                    fis = new FileInputStream(nombreArchivo);
                    
                } catch (FileNotFoundException e) {
                    existeArchivo = false;
                }
                String lineaDeEstado = null;
                String lineaDeTipoContenido = null;
                String cuerpoMensaje = null;

                if (existeArchivo) {
                    lineaDeEstado = "HTTP/1.1 200 OK" + CRLF;
                    lineaDeTipoContenido = "Content-type: " + contentType( nombreArchivo ) + CRLF;
                } else {
                    lineaDeEstado = "HTTP/1.1 404 Not Found" + CRLF;
                    lineaDeTipoContenido = "Content-type: text/html" + CRLF;
                    cuerpoMensaje = "<HTML>" + 
                            "<HEAD><TITLE>404 Not Found</TITLE></HEAD>" +
                            "<BODY><b>404</b> Not Found</BODY></HTML>";
                }
                os.writeBytes(lineaDeEstado);
                os.writeBytes(lineaDeTipoContenido);
                os.writeBytes(CRLF);
                if (existeArchivo) {
                    enviarBytes(fis, os);
                    fis.close();
                } else {
                    os.writeBytes(cuerpoMensaje);
                }


            os.close();
            br.close();
            socket.close();
    }
}
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler extends Thread {
    private ServerSocket serverSocket;
    private PannelloImmagine panel;


    public ClientHandler(int port,PannelloImmagine panel) throws IOException {

        this.panel = panel;
        this.serverSocket = new ServerSocket(port);
        System.out.println("Avviato");

    }//costruttore

    @Override
    public void run() {

        int count = 0;
        long startTime = System.currentTimeMillis();

        try{

            while (!isInterrupted()) {
                Socket clientSocket = serverSocket.accept();

                // Ricevi l'immagine dal client
                BufferedImage image = receiveImage(clientSocket);

                // Salva l'immagine ricevuta nell'attributo
                synchronized (this) {
                    panel.image = image;
                    panel.repaint();
                }

                count++;

                // Controlla se è passato un secondo
                long currentTime = System.currentTimeMillis();
                if (currentTime - startTime >= 1000) {
                    panel.fps = count;
                    count = 0; // Reimposta il contatore
                    startTime = currentTime; // Reimposta il timestamp
                }

            }

        } catch (IOException ignored) {

        }

    }

    private BufferedImage receiveImage(Socket clientSocket) throws IOException {

        byte[] imageBytes = clientSocket.getInputStream().readAllBytes();
        return ImageIO.read(new ByteArrayInputStream(imageBytes));

    }

}

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ImageSender extends Thread{

    private int port;
    private String ip;

    private static final Object lock = new Object();
    private static BufferedImage imageToSend;

    public ImageSender(int port, String ip) {
        this.port = port;
        this.ip = ip;
    }//costruttore


    @Override
    public void run(){

        try{

            while(!interrupted()){

                BufferedImage image;
                synchronized (lock){

                    while(imageToSend == null){

                        lock.wait();

                    }//while
                    image = imageToSend;
                    imageToSend = null;

                }//synchronized

                sendImage(image);

            }//while

        } catch (Exception ignored) {

        }


    }

    public static void enqueueImage(BufferedImage image) {
        synchronized (lock) {
            imageToSend = image;
            lock.notify();
        }
    }

    private void sendImage(BufferedImage image) throws InterruptedException {
        try (Socket socket = new Socket(ip,port)) {
            OutputStream outputStream = socket.getOutputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "JPEG", byteArrayOutputStream);
            byte[] size = byteArrayOutputStream.toByteArray();
            outputStream.write(size);
            outputStream.flush();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException ignored) {
            System.out.println("server offline");
            Thread.sleep(2000);
        }
    }
}

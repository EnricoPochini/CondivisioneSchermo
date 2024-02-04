import java.awt.*;
import java.io.*;

public class Mandante {
    public static void main(String[] args) throws IOException, InterruptedException, AWTException {

        //socket
        String ip = "127.0.0.1";
        int port = 70;

        //robot
        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

        //image getter
        ImageGetter imageGetter = new ImageGetter(robot,screenRect);
        imageGetter.start();
        //<--synchronized-->
        //image sender
        ImageSender imageSender = new ImageSender(port,ip);
        imageSender.start();


    }



}

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageGetter extends Thread{

    private Robot robot;
    private Rectangle screen;

    public ImageGetter(Robot robot, Rectangle screen) {
        this.robot = robot;
        this.screen = screen;
    }//costruttore

    @Override
    public void run(){

        try{
            while(!interrupted()){

                BufferedImage image = robot.createScreenCapture(screen);

                ImageSender.enqueueImage(image);

            }//while


        } catch (Exception ignored) {

        }


    }
}

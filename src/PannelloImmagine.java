import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PannelloImmagine extends JPanel{

    public BufferedImage image;
    public JFrame win;
    public int fps = 40;


    PannelloImmagine(JFrame win){

        this.win = win;
        this.image = null;

        this.setFocusable(true);
        this.setVisible(true);

    }//costruttore




    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(image!=null){


            BufferedImage defImage = getScaledImage(image,win.getWidth(),win.getHeight());
            g2.drawImage(defImage,0,0,null);
            drawFps(g2);

        }//if
        else{

            drawText(g2);

        }//else

        g.dispose();
        g2.dispose();


    }

    private void drawText(Graphics2D g2){

        String text = "OFFLINE";
        Font font = new Font("italic",Font.BOLD,30);
        g2.setFont(font);

        FontMetrics metrics = g2.getFontMetrics(font);
        int stringWidth = metrics.stringWidth(text);
        int stringHeight = metrics.getHeight();

        int x = (win.getWidth() - stringWidth) / 2;
        int y = (win.getHeight() - stringHeight) / 2 + metrics.getAscent();

        g2.drawString(text, x, y);


    }

    private void drawFps(Graphics2D g2){

        String text = String.valueOf(fps);
        Font font = new Font("italic",Font.BOLD,30);
        g2.setFont(font);
        g2.setColor(Color.white);

        g2.drawString(text, 30, 30);


    }

    private BufferedImage getScaledImage(BufferedImage image,int width,int height){

        BufferedImage resizedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();


        return resizedImage;

    }


}

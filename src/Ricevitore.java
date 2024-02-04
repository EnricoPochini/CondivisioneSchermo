import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Ricevitore {
    public static void main(String[] args) throws IOException {

        //socket
        int portaAscolto = 70;


        //window settings
        Dimension windowSize = new Dimension(800,500);
        JFrame win = initWindow(windowSize,"segzyVirsus");
        PannelloImmagine panel = new PannelloImmagine(win);
        win.add(panel);

        //client handler
        ClientHandler clientHandler = new ClientHandler(portaAscolto,panel);
        clientHandler.start();


    }


    private static JFrame initWindow(Dimension size, String title){
        JFrame win = new JFrame(title);
        win.setSize(size);
        win.setResizable(true);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setFocusable(true);
        win.setVisible(true);
        win.setLocationRelativeTo(null);

        return win;
    }


}

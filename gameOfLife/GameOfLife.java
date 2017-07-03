import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ярослав on 09.06.2017.
 */
public class GameOfLife {
    public static void main(String[] args) {
        Input hfhf = new Input();
        ArrayList<KeyEvent> events = (ArrayList) hfhf.getKeyEvents();
        CanvasGame game = new CanvasGame(new Dimension(25*75, 25*35));
        Scene scene = new Scene(game);
        game.setScene(scene);
        JFrame frame = new JFrame();
        frame.setFocusable(false);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        game.createBufferStrategy(2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
        frame.requestFocus();
        game.requestFocus();
        game.start();
        while (game.getRunning().get()){
        }
        frame.dispose();



    }
}

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Ярослав on 10.06.2017.
 */
public class Scene {
    private CanvasGame game;
    public Scene(CanvasGame game){
        this.game = game;
    }
    public void update(long nanosPassed, Graphics2D g){
        procInput();
        int x;
        int y;
        if (game.getMatrix() != null){
            y = 0;
            for (int a[]: game.getMatrix()){
                x = 0;
                for (int b: a){
                    if (b == 1) {
                        g.setColor(Color.blue);
                    }
                    if (b == 0){
                        g.setColor(Color.white);
                    }
                    if (b == 2) {
                        g.setColor(Color.green);
                    }
                    if (b == 3) {
                        g.setColor(Color.gray);
                    }
                    g.fillRect(x + 1, y + 1, 23, 23);
                    x+=25;
                }
                y+=25;
            }
        }
    }
    public void draw(Graphics2D g){
        g.setColor(Color.black);
        int x = 0;
        int y = 0;
        while (y < game.getHeight()){
            x = 0;
            while (x < game.getWidth()) {
                g.drawRect(x+1, y+1, 23, 23);
             x+=25;
            }
            y+=25;
        }

    }
    public void procInput(){
                Collection<KeyEvent> keyEvent = game.getInput().getKeyEvents();
        for (KeyEvent a: keyEvent){
                System.out.println(a.getKeyCode());
                if (a.getKeyCode() == 27){
                    game.setRunning(new AtomicBoolean(false));
                }
                if (a.getKeyCode() == 8)
                    game.setMatrix(0,0,100);
                if (a.getKeyCode() == 32)
                {
                    game.changeColor();
                    game.evolve();
                }
        }
            Collection<MouseEvent> mouseEvents = game.getInput().getMouseEvents();
        for (MouseEvent m: mouseEvents){
            System.out.println(m.getX()+" "+m.getY()+" "+m.getButton()+" "+m.getClickCount());
            if (m.getButton() == 1)
                game.setMatrix(m.getX(),m.getY(), 1);
            if (m.getButton() == 3)
                game.setMatrix(m.getX(),m.getY(), 0);
        }
    }
}

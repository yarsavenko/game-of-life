import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ярослав on 09.06.2017.
 */
public class Input implements MouseListener, KeyListener {
    private Collection<KeyEvent> keyEvents;
    private Collection<MouseEvent> mouseEvents;
    public Input (){
        keyEvents = new ArrayList<>();
        mouseEvents = new ArrayList<>();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    public Collection<KeyEvent> getKeyEvents(){
        Collection<KeyEvent> events = new ArrayList<>(keyEvents);
        keyEvents.clear();
        return events;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        keyEvents.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Collection<MouseEvent> getMouseEvents(){
        Collection<MouseEvent> events = new ArrayList<>(mouseEvents);
        mouseEvents.clear();
        return events;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
       mouseEvents.add(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseEvents.add(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.event.FocusListener;

/**
 * Created by Ярослав on 09.06.2017.
 */
public class CanvasGame extends Canvas implements Runnable {
    private Input input;
    private AtomicBoolean running;
    private Thread thread;
    private Delay delay;
    private Scene scene;
    private int[][] matrix;

    public Thread getThread(){
        return thread;
    }
    public AtomicBoolean getRunning(){
        return running;
    }
    public void setRunning(AtomicBoolean running){
        this.running = running;
    }

    public int[][] getMatrix(){
        return matrix;
    }
    public Input getInput(){
        return input;
    }
    public CanvasGame (Dimension dimension){
        matrix = new int[dimension.height/25][dimension.width/25];
        setSize(dimension);
        running = new AtomicBoolean(false);
        delay = new Delay(150);
        input = new Input();
        addMouseListener(input);
        addKeyListener(input);
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }
    public void setMatrix(int x, int y,int value){
        if (value == 100)
            this.matrix = new int[matrix.length][matrix[0].length];
        else
                 this.matrix[y/25][x/25] = value;
    }
    public void start(){
        if (running.compareAndSet(false,true)){
            thread = new Thread(this);
            thread.start();
        }
    }
    public void setScene(Scene scene){
        this.scene = scene;
    }
    @Override
    public void run() {
        long timestart = System.nanoTime();

        while (running.get()){

            if (scene == null)
                continue;
            long nowTime = System.nanoTime();
            long nanopassed = nowTime - timestart;
            timestart = nowTime;

            Graphics2D graphics2D = (Graphics2D)getBufferStrategy().getDrawGraphics();
            scene.update(nanopassed, graphics2D);
            scene.draw(graphics2D);
            getBufferStrategy().show();
        }
    }
    public void changeColor(){
        int x = 0;
        int y = 0;
        for (int a[]: matrix){
            x = 0;
            for(int b:a){
                if (b == 2)
                    setMatrix(x, y, 1);
                if (b == 3)
                    setMatrix(x, y, 0);
                x+=25;
            }
            y+=25;
        }
    }
    public void evolve(){
        int a = 0;
        int b;
        int c;
        int mat[][] = new int[matrix.length][matrix[0].length];
        while (a < this.matrix.length){
            b = 0;
            while (b < this.matrix[a].length){
              c = 0;
                if ((b == 0 && matrix[a][matrix[a].length-1] == 1) || (b > 0 &&matrix[a][b-1] == 1))
                    c++;
                if ((b == matrix[a].length - 1 && matrix[a][0] == 1) || (b <  matrix[a].length - 1 &&matrix[a][b + 1] == 1))
                    c++;
                if ((a == 0 && matrix[matrix.length-1][b]==1) || (a > 0 &&matrix[a - 1][b]==1))
                    c++;
                if ((a == 0&& b==0&& matrix[matrix.length-1][matrix[a].length-1]==1) || (a >0 && b > 0 &&matrix[a - 1][b-1]==1))
                    c++;
                if ((a == 0 && b == matrix[a].length - 1&& matrix[matrix.length-1][0]==1) || (a > 0&& b < matrix[a].length - 1&& matrix[a - 1][b+1]==1))
                    c++;
                if ((a == matrix.length-1 && matrix[0][b]==1) || (a < matrix.length-1 &&matrix[a + 1][b]==1))
                    c++;
                if ((a == matrix.length-1&& b==0&& matrix[0][matrix[a].length-1]==1) || (b > 0 && a < matrix.length-1 &&matrix[a + 1][b-1]==1))
                    c++;
                if ((a == matrix.length-1 && b == matrix[a].length - 1&& matrix[0][0]==1) || (a < matrix.length-1 && b < matrix[a].length - 1 && matrix[a + 1][b+1]==1))
                    c++;
                if (a == 0 && (b == 0 || b == 1))
                    System.out.println("c = "+c);
                if (matrix[a][b] != 1 && c == 3)
                    mat[a][b]= 2;
                else if (matrix[a][b] == 1 &&(c < 2 || c > 3))
                    mat[a][b] = 3;
                else
                    mat[a][b] = matrix[a][b];
                b++;
            }
            a++;
        }
        matrix = mat;
    }
}

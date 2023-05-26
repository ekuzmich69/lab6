import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Field extends JPanel implements MouseMotionListener, MouseListener {
    private boolean paused;
    public double mouseX;
    public double mouseY;
    public boolean xar = false;
    private ArrayList<BouncingBall> balls = new ArrayList(10);
    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            Field.this.repaint();
        }
    });

    public Field() {
        this.setBackground(Color.WHITE);
        this.repaintTimer.start();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;

        BouncingBall ball;
        for (Iterator var4 = this.balls.iterator(); var4.hasNext(); ball.paint(canvas)) {
            ball = (BouncingBall) var4.next();
            if (this.xar) {
                ball.toMouse(this.mouseX, this.mouseY);
            }
        }

    }

    public void addBall() {
        BouncingBall ball = new BouncingBall(this);
        this.balls.add(ball);
    }

    public synchronized void pause() {
        this.paused = true;
    }

    public synchronized void resume() {
        this.paused = false;
        this.notifyAll();
    }

    public synchronized void canMove(BouncingBall ball) throws InterruptedException {
        if (this.paused) {
            this.wait();
        }

    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        this.mouseX = (double) e.getX();
        this.mouseY = (double) e.getY();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}
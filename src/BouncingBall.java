
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class BouncingBall implements Runnable {
    private static final int MAX_RADIUS = 40;
    private static final int MIN_RADIUS = 3;
    private static final int MAX_SPEED = 15;
    private int speed;
    private double speedX;
    private double speedY;
    private double x;
    private double y;
    private int radius;
    private Field field;
    private Color color;

    public BouncingBall(Field field) {
        this.field = field;
        this.radius = (new Double(Math.random() * 37.0)).intValue() + 3;
        this.speed = (new Double((double)Math.round((float)(75 / this.radius)))).intValue();
        if (this.speed > 15) {
            this.speed = 15;
        }

        double angle = Math.random() * 2.0 * Math.PI;
        this.speedX = 3.0 * Math.cos(angle);
        this.speedY = 3.0 * Math.sin(angle);
        this.color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
        this.x = Math.random() * (field.getSize().getWidth() - (double)(2 * this.radius)) + (double)this.radius;
        this.y = Math.random() * (field.getSize().getWidth() - (double)(2 * this.radius)) + (double)this.radius;
        Thread thisThread = new Thread(this);
        thisThread.start();
    }

    public void run() {
        try {
            while(true) {
                this.field.canMove(this);
                if (this.x + this.speedX <= (double)this.radius) {
                    this.speedX = -this.speedX;
                    this.x = (double)this.radius;
                } else if (this.x + this.speedX >= (double)(this.field.getWidth() - this.radius)) {
                    this.speedX = -this.speedX;
                    this.x = (double)(new Double((double)(this.field.getWidth() - this.radius))).intValue();
                } else if (this.y + this.speedY <= (double)this.radius) {
                    this.speedY = -this.speedY;
                    this.y = (double)this.radius;
                } else if (this.y + this.speedY >= (double)(this.field.getHeight() - this.radius)) {
                    this.speedY = -this.speedY;
                    this.y = (double)(new Double((double)(this.field.getHeight() - this.radius))).intValue();
                } else {
                    this.x += this.speedX;
                    this.y += this.speedY;
                }

                Thread.sleep((long)(16 - this.speed));
            }
        } catch (InterruptedException var2) {
        }
    }

    public void paint(Graphics2D canvas) {
        canvas.setColor(this.color);
        canvas.setPaint(this.color);
        Ellipse2D.Double ball = new Ellipse2D.Double(this.x - (double)this.radius, this.y - (double)this.radius, (double)(2 * this.radius), (double)(2 * this.radius));
        canvas.draw(ball);
        canvas.fill(ball);
    }

    public void toMouse(double mouseX, double mouseY) {
        if (mouseY - this.y != 0.0 && mouseX - this.x != 0.0) {
            double angle = Math.atan(Math.abs(mouseY - this.y) / Math.abs(mouseX - this.x));
            this.speedX = 3.0 * Math.cos(angle);
            this.speedY = 3.0 * Math.sin(angle);
            if (mouseY - this.y < 0.0) {
                this.speedY = -this.speedY;
            }

            if (mouseX - this.x < 0.0) {
                this.speedX = -this.speedX;
            }
        }

    }
}

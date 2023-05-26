import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JCheckBoxMenuItem xarizmaMenuItem;
    private Field field = new Field();

    public MainFrame() {
        super("Прыгающие мячики");
        this.setSize(700, 500);
        Toolkit kit = Toolkit.getDefaultToolkit();
        this.setLocation((kit.getScreenSize().width - 700) / 2, (kit.getScreenSize().height - 500) / 2);
        this.setExtendedState(6);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            public void actionPerformed(ActionEvent arg0) {
                MainFrame.this.field.addBall();
                if (!MainFrame.this.pauseMenuItem.isEnabled() && !MainFrame.this.resumeMenuItem.isEnabled()) {
                    MainFrame.this.pauseMenuItem.setEnabled(true);
                }

            }
        };
        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);
        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);
        Action pauseAction = new AbstractAction("Приостановить движение") {
            public void actionPerformed(ActionEvent arg0) {
                MainFrame.this.field.pause();
                MainFrame.this.pauseMenuItem.setEnabled(false);
                MainFrame.this.resumeMenuItem.setEnabled(true);
            }
        };
        this.pauseMenuItem = controlMenu.add(pauseAction);
        this.pauseMenuItem.setEnabled(false);
        Action resumeAction = new AbstractAction("Возобновить движение") {
            public void actionPerformed(ActionEvent arg0) {
                MainFrame.this.field.resume();
                MainFrame.this.resumeMenuItem.setEnabled(true);
                MainFrame.this.resumeMenuItem.setEnabled(false);
            }
        };
        this.resumeMenuItem = controlMenu.add(resumeAction);
        this.resumeMenuItem.setEnabled(false);
        JMenu effectMenu = new JMenu("Эффекты");
        menuBar.add(effectMenu);
        AbstractAction effectAction = new AbstractAction("Харизма") {
            public void actionPerformed(ActionEvent arg0) {
                MainFrame.this.field.xar = MainFrame.this.xarizmaMenuItem.isSelected();
            }
        };
        this.xarizmaMenuItem = new JCheckBoxMenuItem(effectAction);
        effectMenu.add(this.xarizmaMenuItem);
        this.xarizmaMenuItem.setEnabled(true);
        this.getContentPane().add(this.field, "Center");
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        class T1 extends Thread {
            T1() {
            }

            public void run() {
                System.out.println("Я умею пользоваться потоками");
            }
        }

        Thread thread1 = new T1();
        thread1.start();
    }
}

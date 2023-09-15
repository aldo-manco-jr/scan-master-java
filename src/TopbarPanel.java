import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by unieuro on 4/23/2020.
 */
public class TopbarPanel extends JPanel {

    public static Date date;
    public static DateFormat dateFormat;

    public static final int WIDTH_PANEL = 300;

    private static final Font FONT_BOLD = new Font("ARIAL", Font.BOLD, 18);
    private final Font FONT_NORMALE = new Font("ARIAL", Font.PLAIN, 18);
    private final Font FONT_POINTS = new Font("ARIAL", Font.BOLD, 27);

    public static final int DISTANZA_ELEMENTI = 50;

    private static final int GIOCATORE_1_Y = DISTANZA_ELEMENTI * 2;
    private static final int GIOCATORE_2_Y = DISTANZA_ELEMENTI * 3;

    private static final int BORDO_BOTTONE = 2;

    public TopbarPanel() {

        this.setSize(1165, 100);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        date = new Date();
        System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43

        startTime();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1165, 80);

        g.setColor(Color.BLACK);

        g.setFont(FONT_BOLD);
        g.drawString(DatabaseManagement.nameCurrentPanel, 420, 45);
        g.drawString(dateFormat.format(date), 1000, 45);
    }

    public void startTime() {

        Thread timerThread = new Thread(new TopbarPanel.TimerThread());
        timerThread.start();

    }

    private class TimerThread implements Runnable {

        @Override
        public void run() {

            try {
                while (true) {

                    Thread.sleep(10_000);
                    date = new Date();

                    repaint();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
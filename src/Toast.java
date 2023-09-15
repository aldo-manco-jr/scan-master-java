import javax.swing.*;
import java.awt.*;

class Toast extends JPanel {

    String s;

    JWindow w;

    public Toast(String s, int x, int y) {
        w = new JWindow();

        // make the background transparent
        w.setBackground(new Color(0, 0, 0, 0));

        this.s=s;
        w.add(this);
        w.setLocation(x, y);
        w.setSize(300, 100);
    }

    public void paintComponent(Graphics g) {
        int wid = g.getFontMetrics().stringWidth(s);
        int hei = g.getFontMetrics().getHeight();

        // draw the boundary of the Toast and fill it
        g.setColor(Color.black);
        g.fillRect(10, 10, wid + 30, hei + 10);
        g.setColor(Color.black);
        g.drawRect(10, 10, wid + 30, hei + 10);

        // set the color of text
        g.setColor(new Color(255, 255, 255, 240));
        g.drawString(s, 25, 27);
        int t = 250;

        // draw the shadow of the Toast
        for (int i = 0; i < 4; i++) {
            t -= 60;
            g.setColor(new Color(0, 0, 0, t));
            g.drawRect(10 - i, 10 - i, wid + 30 + i * 2,
                    hei + 10 + i * 2);
        }
    }

    // function to pop up the Toast
    void showToast() {
        try {
            w.setOpacity(1);
            w.setVisible(true);

            // wait for some time
            Thread.sleep(2000);

            // make the message disappear slowly
            for (double d = 1.0; d > 0.2; d -= 0.1) {
                Thread.sleep(100);
                w.setOpacity((float) d);
            }

            // set the visibility to false
            w.setVisible(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
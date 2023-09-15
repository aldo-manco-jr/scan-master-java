import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by unieuro on 4/23/2020.
 */
public class ButtonsPanel extends JPanel{

    private static final Font FONT_BOLD = new Font("ARIAL", Font.BOLD, 18);
    private final Font FONT_NORMALE = new Font("ARIAL", Font.PLAIN, 18);
    private final Font FONT_POINTS = new Font("ARIAL", Font.BOLD, 27);

    private RectangleButton mainWindowButton;
    private RectangleButton databaseManagerButton;

    public static final int DISTANZA_ELEMENTI = 50;

    private static final int GIOCATORE_1_Y = DISTANZA_ELEMENTI * 2;
    private static final int GIOCATORE_2_Y = DISTANZA_ELEMENTI * 3;

    private static final int BORDO_BOTTONE = 2;

    public ButtonsPanel() {

        this.setSize(1165, 100);

        mainWindowButton = new RectangleButton(14, 14, 180, 50, Color.LIGHT_GRAY);
        databaseManagerButton = new RectangleButton(214, 14, 180, 50, Color.LIGHT_GRAY);

        mainWindowButton.setLabel("Pagina Principale", 24, 44);
        databaseManagerButton.setLabel("Database Manager", 224, 44);

        this.addMouseListener(new MyMouseListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1165, 80);

        g.setFont(FONT_BOLD);

        mainWindowButton.draw(g);
        databaseManagerButton.draw(g);
    }

    private class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (mainWindowButton.isClicked(e)) {
                DatabaseManagement.changeProgramStatus(0);
            }

            else if (databaseManagerButton.isClicked(e)){
                DatabaseManagement.changeProgramStatus(1);
            }
        }
    }
}

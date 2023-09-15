import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by unieuro on 3/18/2020.
 */
public class SelectionPanel extends JPanel implements FocusListener {

    public static final int WIDTH_PANEL = 300;

    private static final Font FONT_BOLD = new Font("ARIAL", Font.BOLD, 18);
    private final Font FONT_NORMALE = new Font("ARIAL", Font.PLAIN, 18);
    private final Font FONT_POINTS = new Font("ARIAL", Font.BOLD, 27);

    private JTextField nameFirstPlayer;
    private JTextField nameSecondPlayer;
    private JTextField nameThirdPlayer;
    private JTextField nameFourthPlayer;
    private JTextField nameFifthPlayer;

    public static final int DISTANZA_ELEMENTI = 50;

    private static final int GIOCATORE_1_Y = DISTANZA_ELEMENTI * 2;
    private static final int GIOCATORE_2_Y = DISTANZA_ELEMENTI * 3;

    private static final int BORDO_BOTTONE = 2;

    public SelectionPanel() {

        this.setSize(1165, 240);

        nameFirstPlayer = new JTextField();
        nameSecondPlayer = new JTextField();
        nameThirdPlayer = new JTextField();
        nameFourthPlayer = new JTextField();
        nameFifthPlayer = new JTextField();
        //nameFirstPlayer.setText("/home/pi/Desktop/items.db");
        nameSecondPlayer.setText("db/items.db");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1165, 240);

        g.setColor(Color.BLACK);

        nameSecondPlayer.setBounds(DISTANZA_ELEMENTI + 48, 20, 320, 30);
        nameFirstPlayer.setBounds(DISTANZA_ELEMENTI + 50, 60, 320, 30);
        nameThirdPlayer.setBounds(DISTANZA_ELEMENTI - 3, 100, 320, 30);
        nameFourthPlayer.setBounds(DISTANZA_ELEMENTI + 80, 140, 320, 30);
        nameFifthPlayer.setBounds(DISTANZA_ELEMENTI + 60, 180, 320, 30);

        DatabaseManagement.getSelectionPanel().add(nameFirstPlayer);
        DatabaseManagement.getSelectionPanel().add(nameSecondPlayer);
        DatabaseManagement.getSelectionPanel().add(nameThirdPlayer);
        DatabaseManagement.getSelectionPanel().add(nameFourthPlayer);
        DatabaseManagement.getSelectionPanel().add(nameFifthPlayer);

        if (nameFirstPlayer.isFocusOwner()) {
            nameFirstPlayer.requestFocus();
            nameFirstPlayer.setCaretPosition(nameFirstPlayer.getText().length());
            nameFirstPlayer.selectAll();
        } else if (nameSecondPlayer.isFocusOwner()) {
            nameSecondPlayer.requestFocus();
            nameSecondPlayer.setCaretPosition(nameSecondPlayer.getText().length());
        } else if (nameThirdPlayer.isFocusOwner()) {
            nameThirdPlayer.requestFocus();
            nameThirdPlayer.setCaretPosition(nameThirdPlayer.getText().length());
        } else if (nameFourthPlayer.isFocusOwner()) {
            nameFourthPlayer.requestFocus();
            nameFourthPlayer.setCaretPosition(nameFourthPlayer.getText().length());
        } else if (nameFifthPlayer.isFocusOwner()) {
            nameFifthPlayer.requestFocus();
            nameFifthPlayer.setCaretPosition(nameFifthPlayer.getText().length());
        }

        g.setFont(FONT_BOLD);

        g.drawString("Path DB", 20, 40);
        g.drawString("Barcode", 20, 80);
        g.drawString("ID", 20, 120);
        g.drawString("Descrizione", 20, 160);
        g.drawString("Filename", 20, 200);
    }

    public JTextField getNameFirstPlayer() {
        return nameFirstPlayer;
    }

    public JTextField getNameSecondPlayer() {
        return nameSecondPlayer;
    }

    public JTextField getNameThirdPlayer() {
        return nameThirdPlayer;
    }

    public JTextField getNameFourthPlayer() {
        return nameFourthPlayer;
    }

    public JTextField getNameFifthPlayer() {
        return nameFifthPlayer;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (!(e.getSource() instanceof JTextField)) return;
        JTextField txt = (JTextField) e.getSource();
        txt.selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}

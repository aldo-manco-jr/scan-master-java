import utils.ExtensionFileFilter;
import utils.LayoutFileFilter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by unieuro on 3/18/2020.
 */
public class CRUDPanel extends JPanel implements FocusListener {

    public static final int WIDTH_PANEL = 300;

    private static final Font FONT_BOLD = new Font("ARIAL", Font.BOLD, 18);
    private final Font FONT_NORMALE = new Font("ARIAL", Font.PLAIN, 18);
    private final Font FONT_POINTS = new Font("ARIAL", Font.BOLD, 27);

    private JTextField nameFirstPlayer;
    private JTextField nameSecondPlayer;
    private JTextField nameThirdPlayer;
    private JTextField nameFourthPlayer;

    private RectangleButton insertRecordButton;
    private RectangleButton updateRecordButton;
    private RectangleButton deleteRecordButton;
    private RectangleButton chooseImageButton;

    Thread timerThread;

    Toast toast = new Toast("",0,0);

    protected static final String[] EXTENSIONS = {".png", ".jpg"};

    protected static final String EXTENSION = ".jpg";

    protected static final LayoutFileFilter SAVE_AS_IMAGE =
            new LayoutFileFilter("JPEG Image Format", EXTENSION, true);

    public static final int DISTANZA_ELEMENTI = 50;

    public CRUDPanel() {

        this.setSize(1165, 240);

        nameFirstPlayer = new JTextField();
        nameSecondPlayer = new JTextField();
        nameThirdPlayer = new JTextField();
        nameFourthPlayer = new JTextField();
        //nameFirstPlayer.setText("/home/pi/Desktop/items.db");
        nameSecondPlayer.setText("db/items.db");

        insertRecordButton = new RectangleButton(614, 14, 180, 50, Color.LIGHT_GRAY);
        updateRecordButton = new RectangleButton(614, 84, 180, 50, Color.LIGHT_GRAY);
        deleteRecordButton = new RectangleButton(614, 154, 180, 50, Color.LIGHT_GRAY);
        chooseImageButton = new RectangleButton(DISTANZA_ELEMENTI + 60, 180, 110, 30, Color.LIGHT_GRAY);

        insertRecordButton.setLabel("NUOVO", 624, 45);
        updateRecordButton.setLabel("SALVA", 624, 115);
        deleteRecordButton.setLabel("CANCELLA", 624, 185);
        chooseImageButton.setLabel("Scegli File", DISTANZA_ELEMENTI + 70, 203);

        nameFirstPlayer.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                DatabaseManagement.getUpdatePanel().updateConnection();
                DatabaseManagement.getUpdatePanel().repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                DatabaseManagement.getUpdatePanel().updateConnection();
                DatabaseManagement.getUpdatePanel().repaint();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                DatabaseManagement.getUpdatePanel().updateConnection();
                DatabaseManagement.getUpdatePanel().repaint();
            }
        });

        this.addMouseListener(new CRUDPanel.MyMouseListener());

        toast.setVisible(true);
    }

    @Override
    @SuppressWarnings("Duplicates")
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1165, 240);

        g.setColor(Color.BLACK);

        nameSecondPlayer.setBounds(DISTANZA_ELEMENTI + 48, 20, 320, 30);
        nameFirstPlayer.setBounds(DISTANZA_ELEMENTI - 3, 60, 320, 30);
        nameThirdPlayer.setBounds(DISTANZA_ELEMENTI + 50, 100, 320, 30);
        nameFourthPlayer.setBounds(DISTANZA_ELEMENTI + 80, 140, 320, 30);

        DatabaseManagement.getCRUDPanel().add(nameFirstPlayer);
        DatabaseManagement.getCRUDPanel().add(nameSecondPlayer);
        DatabaseManagement.getCRUDPanel().add(nameThirdPlayer);
        DatabaseManagement.getCRUDPanel().add(nameFourthPlayer);

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
        }

        g.setFont(FONT_BOLD);

        g.drawString("Path DB", 20, 40);
        g.drawString("ID", 20, 80);
        g.drawString("Barcode", 20, 120);
        g.drawString("Descrizione", 20, 160);
        g.drawString("Filename", 20, 200);

        insertRecordButton.draw(g);
        updateRecordButton.draw(g);
        deleteRecordButton.draw(g);
        chooseImageButton.draw(g);
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

    @Override
    public void focusGained(FocusEvent e) {
        if (!(e.getSource() instanceof JTextField)) return;
        JTextField txt = (JTextField) e.getSource();
        txt.selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    protected String chooseSaveFile() {
        JFileChooser fileChooser = new JFileChooser();
        ExtensionFileFilter pFilter = new ExtensionFileFilter(SAVE_AS_IMAGE);
        fileChooser.setFileFilter(pFilter);
        int status = fileChooser.showSaveDialog(DatabaseManagement.databaseManagement);
        String fileName = "";

        if (status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                fileName = selectedFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(fileName);
        return fileName;
    }

    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    private class MyMouseListener extends MouseAdapter {

        @Override
        @SuppressWarnings("Duplicates")
        public void mouseClicked(MouseEvent e) {

            if (insertRecordButton.isClicked(e)) {

                DatabaseManagement.getUpdatePanel().image_url=null;
                nameFirstPlayer.setText("");
                nameFirstPlayer.setEditable(false);
                DatabaseManagement.getCRUDPanel().getNameThirdPlayer().setText("");
                DatabaseManagement.getCRUDPanel().getNameFourthPlayer().setText("");

            } else if (updateRecordButton.isClicked(e)) {

                if (!nameFirstPlayer.isEditable()){

                    nameFirstPlayer.setEditable(true);
                    DatabaseManagement.getUpdatePanel().insertCommand();
                    DatabaseManagement.getUpdatePanel().repaint();
                    DatabaseManagement.getCRUDPanel().getNameThirdPlayer().setText("");
                    DatabaseManagement.getCRUDPanel().getNameFourthPlayer().setText("");
                    toast = new Toast("Oggetto Aggiunto", 860, 890);
                    toast.showToast();

                }else if (nameFirstPlayer.isEditable()){

                    DatabaseManagement.getUpdatePanel().updateCommand();
                    DatabaseManagement.getUpdatePanel().repaint();
                    toast = new Toast("Oggetto Aggiornato", 860, 890);
                    toast.showToast();

                }

            } else if (deleteRecordButton.isClicked(e)) {

                if (nameFirstPlayer.isEditable()){

                    DatabaseManagement.getUpdatePanel().deleteCommand();
                    DatabaseManagement.getUpdatePanel().image_url=null;
                    DatabaseManagement.getUpdatePanel().repaint();
                    DatabaseManagement.getCRUDPanel().getNameThirdPlayer().setText("");
                    DatabaseManagement.getCRUDPanel().getNameFourthPlayer().setText("");
                    toast = new Toast("Oggetto Cancellato", 860, 890);
                    toast.showToast();
                }

            } else if (chooseImageButton.isClicked(e)) {
                imagePath = chooseSaveFile();
                DatabaseManagement.getUpdatePanel().image_url=imagePath;
                DatabaseManagement.getUpdatePanel().repaint();
            }
        }
    }

}

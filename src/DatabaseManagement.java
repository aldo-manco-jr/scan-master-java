import javax.swing.*;
import java.awt.*;
import java.sql.Statement;

/**
 * Created by unieuro on 3/18/2020.
 */
public class DatabaseManagement extends JFrame {

    static DatabaseManagement databaseManagement;

    public static String nameCurrentPanel;

    public static final int PANEL_WIDTH = 450;
    public static final int PANEL_HEIGHT = 450;

    public static final int MAIN_WINDOW = 0;
    public static final int DATABASE_MANAGER = 1;

    public static int status;

    public static final int INSERTION_FLAG=10;
    public static final int UPDATE_FLAG=11;
    public static final int DELETION_FLAG=12;

    public static int operationStatus;

    public static final Point PANEL_LOCATION = new Point(0, 14);

    private static TopbarPanel topbarPanel;

    private static SelectionPanel selectionPanel;
    private static ImagePanel imagePanel;

    private static CRUDPanel CRUDPanel;
    private static UpdatePanel updatePanel;

    private static ButtonsPanel buttonsPanel;

    public DatabaseManagement() {
        this.setSize(1200, 1200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        topbarPanel = new TopbarPanel();
        topbarPanel.setLocation(new Point(14 + PANEL_LOCATION.x, PANEL_LOCATION.y));
        this.getContentPane().add(topbarPanel);

        selectionPanel = new SelectionPanel();
        selectionPanel.setLocation(new Point(14 + PANEL_LOCATION.x, 100 + PANEL_LOCATION.y));
        this.getContentPane().add(selectionPanel);

        imagePanel = new ImagePanel();
        imagePanel.setLocation(DatabaseManagement.PANEL_LOCATION);
        imagePanel.setLocation(new Point(14 + PANEL_LOCATION.x, 340 + PANEL_LOCATION.y));
        this.getContentPane().add(imagePanel);

        CRUDPanel = new CRUDPanel();
        CRUDPanel.setLocation(new Point(14 + PANEL_LOCATION.x, 100 + PANEL_LOCATION.y));
        this.getContentPane().add(CRUDPanel);

        updatePanel = new UpdatePanel();
        updatePanel.setLocation(new Point(14 + PANEL_LOCATION.x, 340 + PANEL_LOCATION.y));
        this.getContentPane().add(updatePanel);

        buttonsPanel = new ButtonsPanel();
        buttonsPanel.setLocation(new Point(14 + PANEL_LOCATION.x, 340 + 487 + PANEL_LOCATION.y));
        this.getContentPane().add(buttonsPanel);

        changeProgramStatus(MAIN_WINDOW);
    }

    static Statement statement;

    public static void main(String[] args) {

        databaseManagement = new DatabaseManagement();
        databaseManagement.setVisible(true);

        /*try {
            System.out.println(9);
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\unieuro\\Desktop\\BarcodeDB\\db\\items.db");
            System.out.println(9);
            statement = connection.createStatement();

            statement.execute("CREATE TABLE items(id VARCHAR(255) UNIQUE NOT NULL, image_url VARCHAR(255) NOT NULL)");
            statement.execute("INSERT INTO items(id, image_url) VALUES (\"ERE\", \"C:\\Users\\unieuro\\Downloads\\abstract-dynamic-pattern-wallpaper-vector_53876-59131.jpg\")");

            statement.execute("SELECT * FROM items");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()){
                System.out.println(resultSet.getString("image_url"));
            }

            statement.close();
            connection.close();

        }catch (SQLException e){
            System.err.println("Something went wrong");
        }*/
    }

    public static void changeProgramStatus(int pStatus) {
        switch (pStatus) {
            case DatabaseManagement.MAIN_WINDOW:

                status=MAIN_WINDOW;

                selectionPanel.setVisible(true);
                imagePanel.setVisible(true);
                topbarPanel.setVisible(true);
                buttonsPanel.setVisible(true);
                CRUDPanel.setVisible(false);
                updatePanel.setVisible(false);

                nameCurrentPanel = "Pagina Principale";

                DatabaseManagement.getUpdatePanel().image_url=null;
                DatabaseManagement.getCRUDPanel().getNameFirstPlayer().setText("");
                DatabaseManagement.getCRUDPanel().getNameThirdPlayer().setText("");
                DatabaseManagement.getCRUDPanel().getNameFourthPlayer().setText("");

                getTopbarPanel().repaint();

                getImagePanel().startTime();

                break;

            case DatabaseManagement.DATABASE_MANAGER:

                status=DATABASE_MANAGER;

                selectionPanel.setVisible(false);
                imagePanel.setVisible(false);
                topbarPanel.setVisible(true);
                buttonsPanel.setVisible(true);
                CRUDPanel.setVisible(true);
                updatePanel.setVisible(true);

                nameCurrentPanel = "Database Manager";

                DatabaseManagement.getImagePanel().image=null;
                DatabaseManagement.getSelectionPanel().getNameFirstPlayer().setText("");
                DatabaseManagement.getSelectionPanel().getNameThirdPlayer().setText("");
                DatabaseManagement.getSelectionPanel().getNameFourthPlayer().setText("");
                DatabaseManagement.getSelectionPanel().getNameFifthPlayer().setText("");

                getTopbarPanel().repaint();

                break;
        }
    }

    public static int getStatus() {
        return status;
    }

    public static ImagePanel getImagePanel() {
        return imagePanel;
    }

    public static SelectionPanel getSelectionPanel() {
        return selectionPanel;
    }

    public static TopbarPanel getTopbarPanel() {
        return topbarPanel;
    }

    public static CRUDPanel getCRUDPanel() {
        return CRUDPanel;
    }

    public static ButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public static UpdatePanel getUpdatePanel() {
        return updatePanel;
    }
}
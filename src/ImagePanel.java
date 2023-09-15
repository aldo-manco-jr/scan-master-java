import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.*;

/**
 * Created by unieuro on 3/18/2020.
 */
public class ImagePanel extends JPanel {

    Thread timerThread;

    Image image = null;

    public ImagePanel() {

        this.setSize(1165, 287);
    }

    @Override
    @SuppressWarnings("Duplicates")
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet1 = null;

        try {
            System.out.println("jdbc:sqlite:" + DatabaseManagement.getSelectionPanel().getNameSecondPlayer().getText());
            //Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DatabaseManagement.getSelectionPanel().getNameSecondPlayer().getText().replace("\\", "\\\\"));
            connection = DriverManager.getConnection("jdbc:sqlite:" + DatabaseManagement.getSelectionPanel().getNameSecondPlayer().getText());

            statement = connection.createStatement();
            statement.execute("SELECT image_url, description, id FROM items WHERE barcode = " + "\"" + DatabaseManagement.getSelectionPanel().getNameFirstPlayer().getText() + "\"");
            System.out.println("SELECT image_url, description, id FROM items WHERE barcode = " + "\"" + DatabaseManagement.getSelectionPanel().getNameFirstPlayer().getText() + "\"");

            resultSet1 = statement.getResultSet();

            String image_url = resultSet1.getString("image_url");
            int indexFileName = image_url.lastIndexOf("\\");
            String filename = image_url.substring(indexFileName + 1, image_url.length());
            System.out.println(filename);
            String description = resultSet1.getString("description");
            String id_string = resultSet1.getString("id");

            System.out.println(image_url + " " + description + " " + id_string + " " + filename);
            System.out.println(3);

            /*File file = new File(string);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);*/

            DatabaseManagement.getSelectionPanel().getNameThirdPlayer().setText(id_string);
            DatabaseManagement.getSelectionPanel().getNameFourthPlayer().setText(description);
            DatabaseManagement.getSelectionPanel().getNameFifthPlayer().setText(filename);
            image = ImageIO.read(new File(image_url));

        } catch (Exception e) {
            System.out.println("err");
        } finally {
            if (resultSet1 != null) {
                try {
                    resultSet1.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
            System.out.println(32);
        }

        try {
            g.drawImage(image, 0, 0, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startTime() {

        timerThread = new Thread(new ImagePanel.TimerThread());
        timerThread.start();

    }

    private class TimerThread implements Runnable {

        @Override
        public void run() {

            try {
                while (DatabaseManagement.getStatus() == DatabaseManagement.MAIN_WINDOW) {

                    Thread.sleep(1000);
                    DatabaseManagement.getSelectionPanel().getNameFirstPlayer().selectAll();
                    repaint();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

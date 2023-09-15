import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.*;

/**
 * Created by unieuro on 4/24/2020.
 */
public class UpdatePanel extends JPanel {

    String image_url;

    public UpdatePanel() {

        this.setSize(1165, 287);
    }

    @Override
    @SuppressWarnings("Duplicates")
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image image = null;

        try {
            image = ImageIO.read(new File(image_url));
        }catch (Exception e){
            System.err.println("no image yet");
        }

        try {
            g.drawImage(image, 0, 0, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateConnection(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet1 = null;

        try {
            System.out.println("jdbc:sqlite:" + DatabaseManagement.getCRUDPanel().getNameSecondPlayer().getText());
            //Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DatabaseManagement.getSelectionPanel().getNameSecondPlayer().getText().replace("\\", "\\\\"));
            connection = DriverManager.getConnection("jdbc:sqlite:" + DatabaseManagement.getCRUDPanel().getNameSecondPlayer().getText());

            statement = connection.createStatement();
            statement.execute("SELECT image_url, description, barcode FROM items WHERE id = " + Integer.parseInt(DatabaseManagement.getCRUDPanel().getNameFirstPlayer().getText()));
            System.out.println("SELECT image_url, description, barcode FROM items WHERE id = " + Integer.parseInt(DatabaseManagement.getCRUDPanel().getNameFirstPlayer().getText()));

            resultSet1 = statement.getResultSet();

            this.image_url = resultSet1.getString("image_url");
            String description = resultSet1.getString("description");
            String barcode = resultSet1.getString("barcode");

            System.out.println(image_url + " " + description + " " + barcode);

            DatabaseManagement.getCRUDPanel().getNameThirdPlayer().setText(barcode);
            DatabaseManagement.getCRUDPanel().getNameFourthPlayer().setText(description);

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
    }

    public void insertCommand() {

        Connection connection = null;
        Statement statement = null;

        try {

            System.out.println("jdbc:sqlite:" + DatabaseManagement.getCRUDPanel().getNameSecondPlayer().getText());
            //Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DatabaseManagement.getSelectionPanel().getNameSecondPlayer().getText().replace("\\", "\\\\"));
            connection = DriverManager.getConnection("jdbc:sqlite:" + DatabaseManagement.getCRUDPanel().getNameSecondPlayer().getText());

            statement = connection.createStatement();
            System.out.println("INSERT INTO items(barcode, description, image_url) VALUES (" +
                    "\"" + DatabaseManagement.getCRUDPanel().getNameThirdPlayer().getText() + "\"," +
                    "\"" + DatabaseManagement.getCRUDPanel().getNameFourthPlayer().getText() + "\"," +
                    "\"" + DatabaseManagement.getCRUDPanel().getImagePath().substring(2, DatabaseManagement.getCRUDPanel().getImagePath().length()) + "\"" +
                    ")");
            statement.execute("INSERT INTO items(barcode, description, image_url) VALUES (" +
                    "\"" + DatabaseManagement.getCRUDPanel().getNameThirdPlayer().getText() + "\"," +
                    "\"" + DatabaseManagement.getCRUDPanel().getNameFourthPlayer().getText() + "\"," +
                    "\"" + DatabaseManagement.getCRUDPanel().getImagePath().substring(2, DatabaseManagement.getCRUDPanel().getImagePath().length()) + "\"" +
                    ")");

            this.image_url = DatabaseManagement.getCRUDPanel().getImagePath().substring(2, DatabaseManagement.getCRUDPanel().getImagePath().length());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        }
    }

    public void updateCommand() {

        Connection connection = null;
        Statement statement = null;

        try {

            System.out.println("jdbc:sqlite:" + DatabaseManagement.getCRUDPanel().getNameSecondPlayer().getText());
            //Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DatabaseManagement.getSelectionPanel().getNameSecondPlayer().getText().replace("\\", "\\\\"));
            connection = DriverManager.getConnection("jdbc:sqlite:" + DatabaseManagement.getCRUDPanel().getNameSecondPlayer().getText());

            statement = connection.createStatement();

            if (DatabaseManagement.getCRUDPanel().getImagePath()==null){
                System.out.println("UPDATE items SET " +
                        "barcode = " + "\"" + DatabaseManagement.getCRUDPanel().getNameThirdPlayer().getText() + "\"," +
                        "description = " + "\"" + DatabaseManagement.getCRUDPanel().getNameFourthPlayer().getText() + "\"" +
                        " WHERE id = " + Integer.parseInt(DatabaseManagement.getCRUDPanel().getNameFirstPlayer().getText()));
                statement.execute("UPDATE items SET " +
                        "barcode = " + "\"" + DatabaseManagement.getCRUDPanel().getNameThirdPlayer().getText() + "\", " +
                        "description = " + "\"" + DatabaseManagement.getCRUDPanel().getNameFourthPlayer().getText() + "\"" +
                        " WHERE id = " + Integer.parseInt(DatabaseManagement.getCRUDPanel().getNameFirstPlayer().getText()));
            }else {
                System.out.println("UPDATE items SET " +
                        "barcode = " + "\"" + DatabaseManagement.getCRUDPanel().getNameThirdPlayer().getText() + "\"," +
                        "description = " + "\"" + DatabaseManagement.getCRUDPanel().getNameFourthPlayer().getText() + "\"," +
                        "image_url = " + "\"" + DatabaseManagement.getCRUDPanel().getImagePath().substring(2, DatabaseManagement.getCRUDPanel().getImagePath().length()) + "\"" +
                        " WHERE id = " + Integer.parseInt(DatabaseManagement.getCRUDPanel().getNameFirstPlayer().getText()));
                statement.execute("UPDATE items SET " +
                        "barcode = " + "\"" + DatabaseManagement.getCRUDPanel().getNameThirdPlayer().getText() + "\", " +
                        "description = " + "\"" + DatabaseManagement.getCRUDPanel().getNameFourthPlayer().getText() + "\", " +
                        "image_url = " + "\"" + DatabaseManagement.getCRUDPanel().getImagePath().substring(2, DatabaseManagement.getCRUDPanel().getImagePath().length()) + "\"" +
                        " WHERE id = " + Integer.parseInt(DatabaseManagement.getCRUDPanel().getNameFirstPlayer().getText()));

                this.image_url = DatabaseManagement.getCRUDPanel().getImagePath().substring(2, DatabaseManagement.getCRUDPanel().getImagePath().length());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        }
    }

    public void deleteCommand() {

        Connection connection = null;
        Statement statement = null;

        try {

            System.out.println("jdbc:sqlite:" + DatabaseManagement.getCRUDPanel().getNameSecondPlayer().getText());
            //Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DatabaseManagement.getSelectionPanel().getNameSecondPlayer().getText().replace("\\", "\\\\"));
            connection = DriverManager.getConnection("jdbc:sqlite:" + DatabaseManagement.getCRUDPanel().getNameSecondPlayer().getText());

            statement = connection.createStatement();
            System.out.println("DELETE FROM items WHERE id = " + Integer.parseInt(DatabaseManagement.getCRUDPanel().getNameFirstPlayer().getText()));
            statement.execute("DELETE FROM items WHERE id = " + Integer.parseInt(DatabaseManagement.getCRUDPanel().getNameFirstPlayer().getText()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        }
    }
}

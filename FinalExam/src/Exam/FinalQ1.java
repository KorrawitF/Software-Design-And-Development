// Dev: Korrawit S. 6211548

package Exam;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinalQ1 extends Application {
    private Button btView = new Button("View");
    private Button btInsert = new Button("Insert");
    private Button btUpdate = new Button("Update");
    private Button btClear = new Button("Clear");
    private TextField tfID = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfFirstName = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfCountry = new TextField();
    private TextField tfPostal = new TextField();
    private TextField tfTelephone = new TextField();
    private Label lblStatus = new Label();

    // The Statement for processing queries
    private Statement stmt;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        VBox vBox = new VBox(5);

        HBox hBox1 = new HBox(5);
        hBox1.getChildren().addAll(new Label("ID"), tfID);
        HBox hBox2 = new HBox(5);
        hBox2.getChildren().addAll(new Label("First Name"), tfFirstName,
                new Label("Last Name"), tfLastName);
        tfLastName.setPrefColumnCount(10);
        tfFirstName.setPrefColumnCount(10);

        HBox hBox3 = new HBox(5);
        hBox3.getChildren().addAll(new Label("Address"), tfAddress, new Label("City"), tfCity);
        HBox hBox4 = new HBox(5);
        hBox4.getChildren().addAll(new Label("Postal Code"), tfPostal,
                new Label("Country"), tfCountry);
        HBox hBox5 = new HBox(5);
        hBox5.getChildren().addAll(new Label("Telephone"), tfTelephone);

        vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5);

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(btView, btInsert, btUpdate, btClear);
        hBox.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(vBox);
        pane.setTop(lblStatus);
        pane.setBottom(hBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 500, 200);
        primaryStage.setTitle("Final Exam Question 1 (2/2021)"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        initializeDB();
        btUpdate.setDisable(true);
        btView.setOnAction(e -> view());
        btInsert.setOnAction(e -> insert());
        btUpdate.setOnAction(e -> update());
        btClear.setOnAction(e -> clear());
    }

    // a
    private void initializeDB() {
        try {
            // Connect to the local InterBase database
            Connection conn = DriverManager.getConnection("jdbc:mysql://18.140.131.225/staff", "staff", "staff_ce2104");
            System.out.println("Database connected\n");
            lblStatus.setText("Database Connected");
            stmt = conn.createStatement();
        } catch (Exception ex) {
            lblStatus.setText("Connection fail!");
        }
    }

    // b
    /** Insert a new record */
    private void insert() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp ts = new Timestamp(date.getTime());
        String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ts);
        String id = tfID.getText();
        String firstname = tfFirstName.getText();
        String lastname = tfLastName.getText();
        String address = tfAddress.getText();
        String city = tfCity.getText();
        String post = tfPostal.getText();
        String country = tfCountry.getText();
        String phone = tfTelephone.getText();
        String updateBy = "Andy";
        try {
            String insertion = "INSERT INTO staff(id, firstName, lastName, address, city, postal, country, telephone, updateBy, update_date) VALUES ("
                    + id + "," + "'" + firstname + "'" + "," + "'" + lastname + "'" + "," + "'" + address + "'" + ","
                    + "'" + city + "'" + "," + post + "," + "'" + country + "'" + "," + phone + "," + "'" + updateBy
                    + "'" + "," + "'" + s + "')";
            stmt.execute(insertion);
            tfID.clear();
            tfFirstName.clear();
            tfLastName.clear();
            tfAddress.clear();
            tfCity.clear();
            tfPostal.clear();
            tfCountry.clear();
            tfTelephone.clear();
            lblStatus.setText("Insertion completed!");
        } catch (Exception e) {
            System.out.println(e);
            lblStatus.setText("Error occur during insertion!");
        }
    }

    // c
    /** View record by ID */
    private void view() {
        // Build a SQL SELECT statement
        String id = tfID.getText();
        try {
            String query = "SELECT * FROM staff WHERE id = " + id + ";";
            ResultSet rset = stmt.executeQuery(query);
            if (rset.next()) {
                tfFirstName.setText(rset.getString("firstName"));
                tfLastName.setText(rset.getString("lastName"));
                tfAddress.setText(rset.getString("address"));
                tfCity.setText(rset.getString("city"));
                tfCountry.setText(rset.getString("country"));
                tfPostal.setText(rset.getString("postal"));
                tfTelephone.setText(rset.getString("telephone"));
                btUpdate.setDisable(false);
                btInsert.setDisable(true);
                btView.setDisable(true);
            } else {
                lblStatus.setText("ID not found!");
            }
        } catch (Exception e) {
            System.out.println(e);
            lblStatus.setText("Error occur during view data");
        }
    }

    // d
    /** Update a record */
    private void update() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp ts = new Timestamp(date.getTime());
        String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ts);
        String id = tfID.getText();
        String firstname = tfFirstName.getText();
        String lastname = tfLastName.getText();
        String address = tfAddress.getText();
        String city = tfCity.getText();
        String post = tfPostal.getText();
        String country = tfCountry.getText();
        String phone = tfTelephone.getText();
        String updateBy = "Andy";
        try {
            String updateStmt = "UPDATE staff SET firstName = '" + firstname + "', lastName = '" + lastname
                    + "', address = '" + address + "', city = '" + city + "', postal = " + post + ", country = '"
                    + country
                    + "', telephone = " + phone + ", updateBy = '" + updateBy + "', update_date = '" + s
                    + "' WHERE id = "
                    + id + ";";
            stmt.execute(updateStmt);
            tfID.clear();
            tfFirstName.clear();
            tfLastName.clear();
            tfAddress.clear();
            tfCity.clear();
            tfPostal.clear();
            tfCountry.clear();
            tfTelephone.clear();
            btUpdate.setDisable(true);
            btInsert.setDisable(false);
            btView.setDisable(false);
            lblStatus.setText("Data updated!");
        } catch (Exception e) {
            System.out.println(e);
            lblStatus.setText("Error occur during update!");
        }
    }

    // e
    /** Clear text fields */
    private void clear() {
        tfID.clear();
        tfFirstName.clear();
        tfLastName.clear();
        tfAddress.clear();
        tfCity.clear();
        tfPostal.clear();
        tfCountry.clear();
        tfTelephone.clear();
        lblStatus.setText("");
        btUpdate.setDisable(true);
        btInsert.setDisable(false);
        btView.setDisable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

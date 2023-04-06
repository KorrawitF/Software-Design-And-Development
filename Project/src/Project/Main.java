package Project;

import java.sql.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.chart.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Main extends Application {

    private Statement stmt;
    private TextField tfSSN = new TextField();
    private TextField tffirstname = new TextField();
    private TextField tfmi = new TextField();
    private TextField tflastname = new TextField();
    private TextField tfphone = new TextField();
    private DatePicker tfbirthDate = new DatePicker();
    private TextField tfstreet = new TextField();
    private TextField tfzipcode = new TextField();
    private Label lblStatus = new Label();
    XYChart.Series dataSeries1 = new XYChart.Series();
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    BarChart barchart = new BarChart(xAxis, yAxis);
    ObservableList<PieChart.Data> PieChartData = FXCollections.observableArrayList();
    final PieChart piechart = new PieChart(PieChartData);
    Button btnUpdate = new Button("Update");
    Button btnInsert = new Button("Insert");
    Button btnDelete = new Button("Delete");
    Button btnClear = new Button("Clear");
    String ID[] = { "BIOL", "CS", "CHEM", "MATH" };
    private ComboBox PickdeptID = new ComboBox<>(FXCollections.observableArrayList(ID));

    public void start(Stage primaryStage) {
        initialDB();
        Button btnShowDeptID = new Button("Search");
        btnDelete.setPadding(new Insets(10, 30, 10, 30));
        btnUpdate.setPadding(new Insets(10, 30, 10, 30));
        btnInsert.setPadding(new Insets(10, 30, 10, 30));
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnClear.setDisable(true);
        yAxis.setLabel("Number of people");
        xAxis.setLabel("Dept ID");
        Label ssn = new Label("SSN");
        Label deptID = new Label("Dept ID:");
        Label firstname = new Label("Firstname:");
        Label mi = new Label("Middle initial:");
        Label lastname = new Label("Lastname:");
        Label birthdate = new Label("Birth Date:");
        Label phone = new Label("Phone:");
        Label zipcode = new Label("Zip Code:");
        lblStatus.setPadding(new Insets(4, 0, 0, 10));
        lblStatus.setTextFill(Color.RED);
        zipcode.setPadding(new Insets(4));
        ssn.setPadding(new Insets(4));
        deptID.setPadding(new Insets(4));
        firstname.setPadding(new Insets(4));
        mi.setPadding(new Insets(4));
        lastname.setPadding(new Insets(4));
        birthdate.setPadding(new Insets(4));
        phone.setPadding(new Insets(4));

        final String pattern = "yyyy-MM-dd";
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        tfbirthDate.setConverter(converter);
        tfbirthDate.setPromptText(pattern.toLowerCase());
        tfbirthDate.requestFocus();

        ChartBuilder();

        barchart.getData().add(dataSeries1);
        barchart.setLegendVisible(false);

        piechart.setLegendVisible(false);

        HBox graphBox = new HBox();
        graphBox.getChildren().addAll(piechart, barchart);
        graphBox.setAlignment(Pos.CENTER);
        graphBox.setStyle(
                "-fx-border-color: grey; -fx-border-insets: 5; -fx-border-width: 1; -fx-border-style: solid; -fx-border-radius: 10;");
        HBox queryBox = new HBox();
        queryBox.getChildren().addAll(ssn, tfSSN, deptID, (btnShowDeptID), (btnClear));
        queryBox.setPadding(new Insets(10));
        queryBox.setSpacing(5);
        queryBox.setAlignment(Pos.CENTER);
        queryBox.setStyle(
                "-fx-border-color: grey; -fx-border-insets: 5; -fx-border-width: 1; -fx-border-style: solid; -fx-border-radius: 10;");
        HBox first_last_name = new HBox();
        first_last_name.getChildren().addAll(firstname, tffirstname, mi, tfmi, lastname, tflastname);
        HBox datePicker = new HBox();
        datePicker.getChildren().addAll(birthdate, tfbirthDate, phone, tfphone);
        datePicker.setPadding(new Insets(10, 0, 0, 0));
        HBox zipAndDept = new HBox();
        zipAndDept.getChildren().addAll(zipcode, tfzipcode, deptID, PickdeptID, lblStatus);
        zipAndDept.setPadding(new Insets(10, 0, 0, 0));
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(btnInsert, btnUpdate, btnDelete);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(50);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle(
                "-fx-border-color: grey; -fx-border-insets: 5; -fx-border-width: 1; -fx-border-style: solid; -fx-border-radius: 10;");
        VBox ResultBox = new VBox();
        ResultBox.getChildren().addAll(first_last_name, datePicker, new Label("Street:"), tfstreet, zipAndDept);
        ResultBox.setPadding(new Insets(10));
        ResultBox.setStyle(
                "-fx-border-color: grey; -fx-border-insets: 5; -fx-border-width: 1; -fx-border-style: solid; -fx-border-radius: 10;");
        VBox querySide = new VBox();
        querySide.getChildren().addAll(queryBox, ResultBox, buttonBox);
        querySide.setPadding(new Insets(10));

        HBox MainBox = new HBox(5);
        MainBox.getChildren().addAll(querySide, graphBox);
        MainBox.setPadding(new Insets(10));

        tfSSN.setPrefColumnCount(10);
        tffirstname.setPrefColumnCount(6);
        tflastname.setPrefColumnCount(6);
        tfmi.setPrefColumnCount(2);
        tfphone.setPrefColumnCount(10);
        tfzipcode.setPrefWidth(70);
        tfbirthDate.setPrefWidth(150);
        tfbirthDate.setPromptText("yyyy-MM-dd");
        PickdeptID.setPrefWidth(80);
        btnShowDeptID.setOnAction(e -> query());
        btnClear.setOnAction(e -> Clear());
        btnUpdate.setOnAction(e -> Update());
        btnInsert.setOnAction(e -> Insert());
        btnDelete.setOnAction(e -> Delete());

        Scene scene = new Scene(MainBox);
        primaryStage.setTitle("Course Project");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void initialDB() {
        try {
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("", "", "");
            System.out.println("Database connected");
            stmt = connection.createStatement();

        } catch (Exception e) {
            System.out.println("Connection lost");
            lblStatus.setText("Connectio lost!");
        }
    }

    private void ChartBuilder() {
        barchart.setAnimated(false);
        dataSeries1.getData().clear();
        barchart.setAnimated(true);
        PieChartData.clear();
        try {
            ResultSet deptIDSet = stmt.executeQuery(
                    "SELECT deptID, COUNT(deptID) AS deptCount FROM Student GROUP BY deptID;");
            while (deptIDSet.next()) {
                String ID = deptIDSet.getString(1);
                Double number = Double.parseDouble(deptIDSet.getString(2));
                dataSeries1.getData().add(new XYChart.Data(ID, number));
                PieChartData.add(new PieChart.Data(ID, number));
            }
            piechart.setAnimated(false);
            piechart.setAnimated(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void query() {
        String ssn = tfSSN.getText();
        lblStatus.setText("");
        try {
            String queryString = "SELECT * FROM Student WHERE ssn= " + ssn;

            ResultSet rset = stmt.executeQuery(queryString);

            if (rset.next()) {
                String firstname = rset.getString("firstname");
                String mi = rset.getString("mi");
                String lastname = rset.getString("lastname");
                String phone = rset.getString("phone");
                String RawbirthDate = rset.getString("birthDate");
                LocalDate birthDate = LocalDate.parse(RawbirthDate);
                String street = rset.getString("street");
                String zipcode = rset.getString("zipCode");
                String udeptID = rset.getString("deptID");
                tffirstname.setText(firstname);
                tfmi.setText(mi);
                tflastname.setText(lastname);
                tfphone.setText(phone);
                tfbirthDate.setValue((birthDate));
                tfstreet.setText(street);
                tfzipcode.setText(zipcode);
                PickdeptID.setValue(udeptID);
                btnClear.setDisable(false);
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                btnInsert.setDisable(true);
            } else {
                lblStatus.setText("Not Found");
            }
        } catch (Exception e) {
            lblStatus.setText("SSN is empty");
            tffirstname.clear();
            tfmi.clear();
            tflastname.clear();
            tfphone.clear();
            tfbirthDate.setValue(null);
            tfstreet.clear();
            tfzipcode.clear();
            PickdeptID.getSelectionModel().clearSelection();
            btnClear.setDisable(true);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
            btnInsert.setDisable(false);
            System.out.println("Invalid data entered!");
        }
    }

    private void Clear() {
        tfSSN.clear();
        tffirstname.clear();
        tfmi.clear();
        tflastname.clear();
        tfphone.clear();
        tfbirthDate.setValue(null);
        tfstreet.clear();
        tfzipcode.clear();
        PickdeptID.getSelectionModel().clearSelection();
        btnClear.setDisable(true);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnInsert.setDisable(false);
        lblStatus.setText("");
    }

    private void Update() {
        String ssn = tfSSN.getText();
        String firstname = tffirstname.getText();
        String mi = tfmi.getText().toUpperCase();
        String lastname = tflastname.getText();
        String phone = tfphone.getText();
        LocalDate birthday = tfbirthDate.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String checkDate = birthday.format(formatter);
        String street = tfstreet.getText();
        String zipcode = tfzipcode.getText();
        String deptId = (String) PickdeptID.getValue();
        try {
            if (firstname.trim().isEmpty() || mi.trim().isEmpty() || lastname.trim().isEmpty() || phone.trim().isEmpty()
                    || street.trim().isEmpty() || zipcode.trim().isEmpty()
                    || deptId.trim().isEmpty()) {
                lblStatus.setText("There is empty field.");
            } else if (!isNumeric(zipcode)) {
                lblStatus.setText("Zipcode: only number!");
            } else if (!isNumeric(phone)) {
                lblStatus.setText("Phone: only number!");
            } else if (limiter(phone, 10, 10)) {
                lblStatus.setText("Phone: 10 number only!");
            } else if (!isValidDate(checkDate)) {
                lblStatus.setText("Invalide date entered!");
            } else if (limiter(zipcode, 5, 5)) {
                lblStatus.setText("Zipcode: 5 number only!");
            } else if (limiter(firstname, 10, 0)) {
                lblStatus.setText("Firstname is  too long!");
            } else if (limiter(lastname, 10, 0)) {
                lblStatus.setText("Lastname is  too long!");
            } else if (limiter(mi, 1, 0)) {
                lblStatus.setText("MiddleInitial only 1 char!");
            } else {
                String updateString = "UPDATE Student SET firstname = '" + firstname + "', mi = '" + mi
                        + "', lastname = '" + lastname + "', phone = " + phone + ", birthDate = '" + birthday
                        + "', street = '" + street + "', zipCode = " + zipcode + ", deptID = '" + deptId
                        + "' WHERE ssn = "
                        + ssn;
                stmt.execute(updateString);
                tfSSN.clear();
                tffirstname.clear();
                tfmi.clear();
                tflastname.clear();
                tfphone.clear();
                tfbirthDate.setValue(null);
                tfstreet.clear();
                tfzipcode.clear();
                PickdeptID.getSelectionModel().clearSelection();
                btnClear.setDisable(true);
                btnDelete.setDisable(true);
                btnUpdate.setDisable(true);
                btnInsert.setDisable(false);
                lblStatus.setText("");
                ChartBuilder();
            }

        } catch (Exception e) {
            System.out.println("Failed to update!");
        }
    }

    private void Insert() {
        String firstname = tffirstname.getText();
        String mi = tfmi.getText().toUpperCase();
        String lastname = tflastname.getText();
        String phone = tfphone.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = tfbirthDate.getValue();
        boolean isPicked = PickdeptID.getSelectionModel().isEmpty();
        String checkDate = null;
        try {
            checkDate = birthday.format(formatter);
        } catch (NullPointerException e) {
            lblStatus.setText("Pick birth date");
        }
        String street = tfstreet.getText();
        String zipcode = tfzipcode.getText();
        String deptId = null;
        if (!isPicked) {
            deptId = (String) PickdeptID.getValue();
        } else {
            lblStatus.setText("Pick Dept ID");
        }
        try {
            ResultSet LastSSN = stmt.executeQuery("SELECT MAX(ssn) + 1 AS LastSSN FROM Student;");
            LastSSN.next();
            String ssn = LastSSN.getString(1);
            System.out.println(LastSSN);
            if (firstname.trim().isEmpty() || mi.trim().isEmpty() || lastname.trim().isEmpty() || phone.trim().isEmpty()
                    || street.trim().isEmpty() || zipcode.trim().isEmpty()
                    || deptId.trim().isEmpty()) {
                lblStatus.setText("There is empty field.");
            } else if (!isNumeric(zipcode)) {
                lblStatus.setText("Zipcode: only number!");
            } else if (!isNumeric(phone)) {
                lblStatus.setText("Phone: only number!");
            } else if (limiter(phone, 10, 10)) {
                lblStatus.setText("Phone: 10 number only!");
            } else if (!isValidDate(checkDate)) {
                lblStatus.setText("Invalide date entered!");
            } else if (limiter(zipcode, 5, 5)) {
                lblStatus.setText("Zipcode: 5 number only!");
            } else if (limiter(firstname, 10, 0)) {
                lblStatus.setText("Firstname is  too long!");
            } else if (limiter(lastname, 10, 0)) {
                lblStatus.setText("Lastname is  too long!");
            } else if (limiter(mi, 1, 0)) {
                lblStatus.setText("MiddleInitial only 1 char!");
            } else {
                String insertString = "INSERT INTO Student(ssn, firstname, mi, lastname, phone, birthDate, street, zipCode, deptID) VALUES ("
                        + ssn + ", '" + firstname + "', '" + mi + "', '" + lastname + "', " + phone + ", '" + birthday
                        + "', '" + street + "', " + zipcode + ", '" + deptId + "');";
                stmt.execute(insertString);
                tfSSN.clear();
                tffirstname.clear();
                tfmi.clear();
                tflastname.clear();
                tfphone.clear();
                tfbirthDate.setValue(null);
                tfstreet.clear();
                tfzipcode.clear();
                PickdeptID.getSelectionModel().clearSelection();
                lblStatus.setText("");
                ChartBuilder();
            }

        } catch (

        Exception e) {
            System.out.println("Failed to insert!");
        }
    }

    private void Delete() {
        String ssn = tfSSN.getText();
        try {
            String deleteString = "DELETE FROM Student WHERE ssn = " + ssn;
            stmt.execute(deleteString);
            tfSSN.clear();
            tffirstname.clear();
            tfmi.clear();
            tflastname.clear();
            tfphone.clear();
            tfbirthDate.setValue(null);
            tfstreet.clear();
            tfzipcode.clear();
            PickdeptID.getSelectionModel().clearSelection();
            btnClear.setDisable(true);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
            btnInsert.setDisable(false);
            lblStatus.setText("");
            ChartBuilder();
        } catch (Exception e) {
            System.out.println("Failed to delete!");
        }
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static boolean isValidDate(String str) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static boolean limiter(String str, Integer maxLength, Integer minLength) {
        if (str.length() > maxLength || str.length() < minLength) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}

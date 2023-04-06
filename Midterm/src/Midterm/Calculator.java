package Midterm;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Calculator extends Application {
    private TextField tfNumber1 = new TextField();
    private TextField tfNumber2 = new TextField();
    private TextField tfResult = new TextField();
    private Button btAdd = new Button("Add");

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        tfNumber1.setPrefWidth(50);
        tfNumber2.setPrefWidth(50);
        tfResult.setPrefWidth(100);
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.add(new Label("Number 1"), 0, 0);
        gridPane.add(tfNumber1, 1, 0);
        gridPane.add(new Label("Number 2"), 2, 0);
        gridPane.add(tfNumber2, 3, 0);
        gridPane.add(new Label("Result"), 4, 0);
        gridPane.add(tfResult, 5, 0);
        gridPane.add(btAdd, 3, 1);

        gridPane.setAlignment(Pos.TOP_CENTER);
        tfResult.setEditable(false);
        btAdd.setOnAction(e -> Calculate());

        Scene scene = new Scene(gridPane, 360, 60);
        primaryStage.setTitle("Caculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void Calculate() {
        try {
            int Number1 = Integer.parseInt(tfNumber1.getText());
            int Number2 = Integer.parseInt(tfNumber2.getText());
            int Result = Number1 + Number2;
            tfResult.setText(String.format("%d", Result));
        } catch (Exception e) {
            tfResult.setText("Accept int only!");
            System.err.println("Wrong input provided!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package Exam;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FinalQ2 extends Application {
        int responseCode;
        private Label date = new Label();
        private Label conmfirmCase = new Label("Confirmed case");
        private Label total = new Label();
        private Label NewCase = new Label();
        private Label Serious = new Label();
        private Label Deaths = new Label();
        private Label fromAboard = new Label(
                        "The confirmed case \n of Thai who returned from aboard \n in quarantine places");
        private Label Atotal = new Label();
        private Label ANewCase = new Label();
        private Label ADeaths = new Label();
        private Button refresh = new Button("Refresh");

        public void start(Stage primaryStage) {
                Connection();
                date.setTextFill(Color.WHITE);
                date.setStyle(
                                "-fx-background-color: blue; -fx-padding: 10px; -fx-border-width: 1px; -fx-border-radius: 5%; -fx-background-radius: 20px; -fx-min-width: 210; -fx-alignment: center");
                conmfirmCase.setStyle(
                                "-fx-background-color: white; -fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: red; -fx-border-radius: 5%; -fx-background-radius: 12%; -fx-min-width: 210; -fx-alignment: center");
                total.setMinWidth(210);
                total.setStyle(
                                "-fx-background-color: lightblue; -fx-padding: 10px; -fx-border-width: 1px; -fx-border-radius: 10%; -fx-background-radius: 20px; -fx-min-width: 210; -fx-alignment: center");
                NewCase.setMinWidth(70);
                NewCase.setTextAlignment(TextAlignment.CENTER);
                NewCase.setTextFill(Color.WHITE);
                NewCase.setStyle(
                                "-fx-background-color: blue; -fx-padding: 10px; -fx-border-width: 1px; -fx-border-radius: 10%; -fx-background-radius: 12%; -fx-alignment: center");
                Serious.setMinWidth(70);
                Serious.setTextAlignment(TextAlignment.CENTER);
                Serious.setTextFill(Color.WHITE);
                Serious.setStyle(
                                "-fx-background-color: cyan; -fx-padding: 10px; -fx-border-width: 1px; -fx-border-radius: 10%; -fx-background-radius: 12%; -fx-alignment: center");
                Deaths.setMinWidth(70);
                Deaths.setTextAlignment(TextAlignment.CENTER);
                Deaths.setTextFill(Color.WHITE);
                Deaths.setStyle(
                                "-fx-background-color: purple; -fx-padding: 10px; -fx-border-width: 1px; -fx-border-radius: 10%; -fx-background-radius: 12%; -fx-alignment: center");
                fromAboard.setMinWidth(210);
                fromAboard.setTextAlignment(TextAlignment.CENTER);
                fromAboard.setStyle(
                                "-fx-background-color: white; -fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: red; -fx-border-radius: 5%; -fx-background-radius: 12%; -fx-alignment: center");
                Atotal.setMinWidth(70);
                Atotal.setTextAlignment(TextAlignment.CENTER);
                Atotal.setTextFill(Color.WHITE);
                Atotal.setStyle(
                                "-fx-background-color: blue; -fx-padding: 10px; -fx-border-width: 1px; -fx-border-radius: 10%; -fx-background-radius: 12%; -fx-alignment: center");
                ANewCase.setMinWidth(70);
                ANewCase.setTextAlignment(TextAlignment.CENTER);
                ANewCase.setTextFill(Color.WHITE);
                ANewCase.setStyle(
                                "-fx-background-color: cyan; -fx-padding: 10px; -fx-border-width: 1px; -fx-border-radius: 10%; -fx-background-radius: 12%; -fx-alignment: center");
                ADeaths.setMinWidth(70);
                ADeaths.setTextAlignment(TextAlignment.CENTER);
                ADeaths.setTextFill(Color.WHITE);
                ADeaths.setStyle(
                                "-fx-background-color: purple; -fx-padding: 10px; -fx-border-width: 1px; -fx-border-radius: 10%; -fx-background-radius: 12%; -fx-alignment: center");
                VBox mainBox = new VBox();
                mainBox.setAlignment(Pos.CENTER);
                HBox hbox1 = new HBox();
                HBox hbox2 = new HBox();
                hbox1.getChildren().addAll(NewCase, Serious, Deaths);
                hbox1.setAlignment(Pos.CENTER);
                hbox1.minWidth(210);
                hbox1.widthProperty().addListener(observable -> setHvalue(getHmax()));
                hbox2.getChildren().addAll(Atotal, ANewCase, ADeaths);
                hbox2.setAlignment(Pos.CENTER);
                hbox2.minWidth(210);
                mainBox.getChildren().addAll(date, conmfirmCase, total, hbox1, fromAboard, hbox2, refresh);
                refresh.setOnAction(e -> Connection());
                Scene scene = new Scene(mainBox);
                primaryStage.setTitle("Final Exam Question 2 (2/2021)");
                primaryStage.setScene(scene);
                primaryStage.show();
        }

        private Object setHvalue(Object hmax) {
                return null;
        }

        private Object getHmax() {
                return null;
        }

        private void Connection() {
                String Ntotal;
                String NNew;
                String NSerious;
                String NDeath;
                String NCase;
                String NAtoal;
                String NANew;
                String NADeath;
                OkHttpClient client = new OkHttpClient();

                Request getRequest = new Request.Builder()
                                .url("http://covid19.ddc.moph.go.th/api/Cases/today-cases-all")
                                .build();
                try {
                        Response response = client.newCall(getRequest).execute();
                        ;
                        String jsonString = response.body().string();
                        JSONParser parser = new JSONParser();
                        Object jsonObj = null;
                        try {
                                jsonObj = parser.parse(jsonString);
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                        System.out.println(jsonObj);
                        JSONArray jsonArray = (JSONArray) jsonObj;
                        System.out.println(jsonArray.get(0));
                        JSONObject jsonData = (JSONObject) jsonArray.get(0);
                        date.setText((String) jsonData.get("txn_date"));
                        Ntotal = jsonData.get("total_case").toString();
                        NNew = jsonData.get("new_case").toString();
                        NSerious = jsonData.get("new_recovered").toString();
                        NDeath = jsonData.get("total_death").toString();
                        NAtoal = jsonData.get("new_recovered").toString();
                        NANew = jsonData.get("total_recovered").toString();
                        NADeath = jsonData.get("new_death").toString();
                        total.setText("   Total\n" + Ntotal);
                        NewCase.setText("  New Case \n" + NNew);
                        Serious.setText("Recovered\n" + NSerious);
                        Deaths.setText("Death\n" + NDeath);
                        Atotal.setText("New recovered\n" + NAtoal);
                        ANewCase.setText("Total recovered\n" + NANew);
                        ADeaths.setText("New Death\n" + NADeath);

                } catch (Exception e) {
                        System.out.println(e);
                }
        }

        public static void main(String[] args) {
                launch(args);
        }
}

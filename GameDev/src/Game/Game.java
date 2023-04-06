package Game;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class App extends Application {
    Button GameOver;
    Text point;
    int score = 0;
    double speed = 5000;

    public void Collision(Rectangle rectangle, Rectangle rectangle1, Rectangle Goal) {
        if (rectangle1.getBoundsInParent().intersects(rectangle.getBoundsInParent())) {
            GameOver.fire();
            score = 0;
        }
        if (rectangle.getBoundsInParent().intersects(Goal.getBoundsInParent())) {
            score = score + 1;
            point.setText("" + score);
            rectangle.setX(30);
            rectangle.setY(225);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Rectangle rectangle = new Rectangle();
        rectangle.setX(30);
        rectangle.setY(225);
        rectangle.setWidth(50);
        rectangle.setHeight(50);
        rectangle.setFill(Color.GREEN);
        Rectangle line = new Rectangle();
        line.setWidth(480);
        line.setHeight(300);
        line.setFill(Color.TRANSPARENT);
        line.setStroke(Color.BLACK);
        line.setX(10);
        line.setY(100);
        Rectangle Goal = new Rectangle();
        Goal.setWidth(50);
        Goal.setHeight(300);
        Goal.setFill(Color.RED);
        Goal.setStroke(Color.TRANSPARENT);
        Goal.setX(440);
        Goal.setY(100);
        Label Score = new Label("Score:");
        Score.setLayoutX(430);
        Score.setLayoutY(10);
        point = new Text();
        point.setX(470);
        point.setY(23);
        point.setText("0");
        Button button = new Button("Reset");
        button.setLayoutY(10);
        button.setLayoutX(10);

        pane.getChildren().add(line);
        pane.getChildren().add(rectangle);
        pane.getChildren().add(button);
        pane.getChildren().add(Goal);
        pane.getChildren().add(Score);
        pane.getChildren().add(point);

        Circle circle = new Circle();
        circle.setCenterX(250);
        circle.setCenterY(250);
        circle.setRadius(100);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.TRANSPARENT);
        Rectangle rectangle1 = new Rectangle();
        rectangle1.setWidth(10);
        rectangle1.setHeight(100);
        rectangle1.setFill(Color.ORANGE);

        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(speed));
        pt.setPath(circle);
        pt.setNode(rectangle1);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.play();

        pane.getChildren().addAll(circle, rectangle1);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                start(primaryStage);
            }
        });

        GameOver = new Button();
        GameOver.setOnAction(e -> {
            pt.pause();
            pane.setEffect(new GaussianBlur());
            VBox Game = new VBox();
            Game.getChildren().add(new Label("Game Over!"));
            Game.setAlignment(Pos.CENTER);
            Game.setPadding(new Insets(20));
            Game.setStyle("-fx-background-color: transparent;");
            Text scoreboard = new Text();
            scoreboard.setText("Score: " + score);
            Game.getChildren().add(scoreboard);
            Button TryAgain = new Button("Try Again");
            Game.getChildren().add(TryAgain);
            Stage OverStage = new Stage(StageStyle.TRANSPARENT);
            OverStage.initOwner(primaryStage);
            OverStage.initModality(Modality.APPLICATION_MODAL);
            OverStage.setScene(new Scene(Game, Color.TRANSPARENT));
            TryAgain.setOnAction(event -> {
                OverStage.hide();
                start(primaryStage);
            });
            OverStage.show();
        });
        EventHandler(pane, rectangle, rectangle1, Goal);

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        rectangle.requestFocus();

    }

    public void EventHandler(Node root, Rectangle rectangle, Rectangle rectangle1, Rectangle Goal) {
        root.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
                case W:
                    if (rectangle.getY() != 100) {
                        rectangle.setY(rectangle.getY() - 5);
                        Collision(rectangle, rectangle1, Goal);
                        break;
                    } else {
                        break;
                    }
                case S:
                    if (rectangle.getY() != 350) {
                        rectangle.setY(rectangle.getY() + 5);
                        Collision(rectangle, rectangle1, Goal);
                        break;
                    } else {
                        break;
                    }
                case A:
                    if (rectangle.getX() != 10) {
                        rectangle.setX(rectangle.getX() - 5);
                        Collision(rectangle, rectangle1, Goal);
                        break;
                    } else {
                        break;
                    }
                case D:
                    if (rectangle.getX() != 440) {
                        rectangle.setX(rectangle.getX() + 5);
                        Collision(rectangle, rectangle1, Goal);
                        break;
                    } else {
                        break;
                    }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}

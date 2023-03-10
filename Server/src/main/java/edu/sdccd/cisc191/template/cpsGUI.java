package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.*;

/** credit: <a href="https://www.youtube.com/watch?v=Lv27qRTxaSU">...</a> - Cloudsoft Consulting Limited "Video2; Creating Click Counter with JavaFX"
 **/
public class cpsGUI extends Application {
    Button btnClick;
    int clickCounter = 0;
    Label lblCount, playerNumber;
    boolean timeStart = false;
    int player, attemptNum;

    public static final int roundTime = 2000;
    public static final int cooldownTime = 2000;

    static StoredNumbers storedNumbers = new StoredNumbers();
    public static void main(String[] args) {
        launch(args);
    }
    // creates instance of object Application, calls start method

    public void start(Stage stage) {
        // initialize controls
        btnClick = new Button();
        btnClick.setText("Start clicking!");
        btnClick.setPrefWidth(400);
        btnClick.setPrefHeight(500);

        // configure the label
        lblCount = new Label();
        lblCount.setText("Player 1, you're up!        You have clicked the button 0 times.");
        lblCount.setTranslateX(150);

        //Create layout pane
        BorderPane pane = new BorderPane();
        pane.setTop(lblCount);
        pane.setCenter(btnClick);
        pane.setBottom(playerNumber);

        // Add functionalities
        btnClick.setOnAction(e -> ClickButton());

        // Create scene
        Scene scene = new Scene(pane, 700, 600);

        //Display stage
        stage.setScene(scene);
        stage.setTitle("Clicking Competition");
        stage.show();
        showWinner(1, 2);
    }
    public void showWinner(double average1, double average2) {

        StackPane r = new StackPane();
        Scene winnerScene = new Scene(r, 230, 100);
        Stage winnerStage = new Stage();

        Label w = new Label();

        Button b = new Button("Yay!");

        //Setting font to the label
        Font font = Font.font("Brush Script MT", FontWeight.BOLD, FontPosture.REGULAR, 25);
        w.setFont(font);
        //Filling color to the label
        w.setTextFill(Color.BLACK);
        //Setting the position
        w.setTranslateX(25);
        w.setTranslateY(25);
        Group root = new Group();
        root.getChildren().add(w);

        if (average1 == average2) {
            // set title for the stage
            winnerStage.setTitle("Tie");
            w.setText("It was a tie! Both players had an average of " + average1 + " clicks per second! Good game!");
        }
        else if (average1 > average2) {
            winnerStage.setTitle("Player 1 Wins!");
            w.setText("Player 1 wins with an average of " + average1 + " clicks per second! Good game!");
        }
        else
            winnerStage.setTitle("Player 2 Wins!");
        w.setText("Player 2 wins with an average of " + average2 + " clicks per second! Good game!");

        // add button
        r.getChildren().add(b);

        winnerStage.setScene(winnerScene);
        winnerStage.show();
    }

    public void ClickButton() {
        if (!timeStart) {
            clickCounter = 0;
            Timer timer = new Timer();
            TimerTask timerTask = new Helper();
            timer.schedule(timerTask, roundTime);
            timeStart = true;
        }
        lblCount.setText("Player " + (player + 1) + ", you're up!          You have clicked the button 0 times.");
        clickCounter++;
        // clickCounter adds one each time

        if (clickCounter == 1) {
            lblCount.setText("Player " + (player + 1) + ", you're up!          You have clicked the button 1 time.");
        } else {
            lblCount.setText("Player " + (player + 1) + ", you're up!          You have clicked the button " + clickCounter + " times.");
        }
    }

        class Helper extends TimerTask {
            public void run() {
                btnClick.setDisable(true);
                storedNumbers.clicksPerSecond(clickCounter, player, attemptNum);
                if (player == 1 && attemptNum == 2) {
                    showWinner(storedNumbers.averageCPS(0),
                    storedNumbers.averageCPS(1));
                }
                else {
                    attemptNum++;
                    if (attemptNum == 3 && player == 0) {
                        player++;
                        attemptNum = 0;
                    }

                    Timer cooldown = new Timer();
                    TimerTask coolTask = new Helper2();
                    cooldown.schedule(coolTask, cooldownTime);
                    timeStart = false;
                }
            }
        }
        class Helper2 extends TimerTask {
            public void run() {
                btnClick.setDisable(false);
            }
        }
    }
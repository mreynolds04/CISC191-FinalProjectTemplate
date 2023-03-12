package edu.sdccd.cisc191.template;

//import necessary classes
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class cpsGUI extends Application {
    // declare the variables throughout the class
    Button btnClick;
    int clickCounter = 0;
    Label lblCount, playerNumber;
    boolean timeStart = false;
    boolean gameOver = false;
    int player, attemptNum;

    // declare length of time in each attempt, along with cooldown period in between
    public static final int roundTime = 15000;
    public static final int cooldownTime = 3000;

    // calls storedNumbers class
    static StoredNumbers storedNumbers = new StoredNumbers();

    // creates instance of object Application, calls start method
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Window where the main Application will take place. Outputs will be displayed on the label.
     * credit: <a href="https://www.youtube.com/watch?v=Lv27qRTxaSU">...</a> - Cloudsoft Consulting Limited "Video2; Creating Click Counter with JavaFX"
     */

    public void start(Stage stage) {
        // initialize button
        btnClick = new Button();
        btnClick.setText("Start clicking!");
        btnClick.setPrefWidth(400);
        btnClick.setPrefHeight(500);

        // Add button functionality
        btnClick.setOnAction(e -> ClickButton());


        // configure the label at start of the program
        lblCount = new Label();
        lblCount.setText("Player 1, you're up!                                                                           " +
                "You have clicked the button 0 times.");

        // create layout of window pane
        BorderPane pane = new BorderPane();
        pane.setTop(lblCount);
        pane.setCenter(btnClick);
        pane.setBottom(playerNumber);

        // Create scene
        Scene scene = new Scene(pane, 700, 600);

        //Display stage
        stage.setScene(scene);
        stage.setTitle("Clicking Competition");
        stage.show();
    }

    /**
     * method used for each time the button is clicked
     * deals with what happens during each attempt per player
     * all values are sent to the array in StoredNumbers, where the average CPS is calculated
     */
    public void ClickButton() {
        double average1, average2;

        // if not all the attempts for both players were used, count the number of clicks
        if (!gameOver) {
            // start the timer once the player starts clicking
            if (!timeStart) {
                clickCounter = 0;
                Timer timer = new Timer();
                TimerTask timerTask = new Helper();
                timer.schedule(timerTask, roundTime);
                timeStart = true;
            }
            // what is displayed at the start of player 2's attempt. each time they click, clickCounter goes up by one.
            lblCount.setText("Player " + (player + 1) + ", you're up!                                                                           " +
                    "You have clicked the button 0 times.");
            clickCounter++;

            // what is displayed when the player clicks once
            if (clickCounter == 1) {
                lblCount.setText("Player " + (player + 1) + ", you're up!                                                                           " +
                        "You have clicked the button 1 time.");
            }

            // what is displayed when the player clicks 2 or more times. It shows the respective clickCounter value.
            else {
                lblCount.setText("Player " + (player + 1) + ", you're up!                                                                           " +
                        "You have clicked the button " + clickCounter + " times.");
            }
        }
        // after all the attempts where used up by both players
        // the values are sent to StoredNumbers class where the values are stored and the average is calculated
        else {
            // 0 = player 1, 1 = player 2
            // averages are taken from StoredNumbers, and are outputted here
            average1 = storedNumbers.averageCPS(0);
            average2 = storedNumbers.averageCPS(1);
            DecimalFormat value = new DecimalFormat("#.#");
            value.format(average1);
            value.format(average2);

            // displays the player who has the higher average CPS, or if the players tied
            if (average1 == average2)
                lblCount.setText("It was a tie! Both players had an average of " + average1 + " clicks per second! Good game!");
            else if (average1 > average2)
                lblCount.setText("Player 1 wins with an average of " + average1 + " clicks per second! Good game!");
            else lblCount.setText("Player 2 wins with an average of " + average2 + " clicks per second! Good game!");
        }
    }
        /** cooldown method for in between each attempt
        **/
         class Helper extends TimerTask {
            public void run() {
                btnClick.setDisable(true);
                storedNumbers.clicksPerSecond(clickCounter, player, attemptNum);
                if (player == 1 && attemptNum == 2) {
                    Timer cooldown = new Timer();
                    TimerTask coolTask = new Helper2();
                    cooldown.schedule(coolTask, cooldownTime);
                    timeStart = false;
                    gameOver = true;
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
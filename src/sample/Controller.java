package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.util.*;

import static javafx.scene.input.MouseEvent.*;


public class Controller implements Initializable, EventHandler<MouseEvent> {


    @FXML
    ImageView greenButton;
    @FXML
    ImageView redButton;
    @FXML
    ImageView yellowButton;
    @FXML
    ImageView blueButton;
    @FXML
    ImageView orangeButton;

    @FXML
    Text greenCoordLayer;

    @FXML
    Text redCoordLayer;
    @FXML
    Text yellowCoordLayer;
    @FXML
    Text blueCoordLayer;
    @FXML
    Text orangeCoordLayer;

    @FXML
    Button rockButton;

    private static int[] buttonXY = {   0, 0,   //  0 1   greenX, greenY
                                        0, 0,   //  2 3   redX, redY
                                        0, 0,   //  4 5   yellowX, yellowY
                                        0, 0,   //  6 7   blueX, blueY
                                        0, 0};  //  8 9   orangeX, orangeY

    public Controller() throws AWTException {
    }

    public static int[] getButtonXY() {
        return buttonXY;
    }

    Watcher myWatcher;
    private int CURRENT_BUTTON = 0;
    UltimateKeyPresser ultimateKeyPresser = new UltimateKeyPresser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        greenButton.addEventHandler(MOUSE_CLICKED, this);
        redButton.addEventHandler(MOUSE_CLICKED, this);
        yellowButton.addEventHandler(MOUSE_CLICKED, this);
        blueButton.addEventHandler(MOUSE_CLICKED, this);
        orangeButton.addEventHandler(MOUSE_CLICKED, this);

        rockButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("LETS GET DIS PARTY STARTED");

                ultimateKeyPresser.start();

                myWatcher.closeEyes();
            }
        });

    }

    @Override
    public void handle(MouseEvent event) {
        animationTimer.start();
        ImageView source = (ImageView) event.getSource();
        String colorButtonId = source.getId();
        switch (colorButtonId) {
            case "greenButton":
                CURRENT_BUTTON = 1;
                break;
            case "redButton":
                CURRENT_BUTTON = 2;
                break;
            case "yellowButton":
                CURRENT_BUTTON = 3;
                break;
            case "blueButton":
                CURRENT_BUTTON = 4;
                break;
            case "orangeButton":
                CURRENT_BUTTON = 5;
                break;
        }
        if (myWatcher == null || !myWatcher.isWatching()) {
            myWatcher = new Watcher(CURRENT_BUTTON);
            myWatcher.start();
        }
    }

    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            updateCoordLables();
        }
    };

    private void updateCoordLables() {
        switch (CURRENT_BUTTON) {
            case 1:
                buttonXY[0] = myWatcher.getX();
                buttonXY[1] = myWatcher.getY();
                greenCoordLayer.setText(buttonXY[0]+":"+buttonXY[1]);
                break;
            case 2:
                buttonXY[2] = myWatcher.getX();
                buttonXY[3] = myWatcher.getY();
                redCoordLayer.setText(buttonXY[2]+":"+buttonXY[3]);
                break;
            case 3:
                buttonXY[4] = myWatcher.getX();
                buttonXY[5] = myWatcher.getY();
                yellowCoordLayer.setText(buttonXY[4]+":"+buttonXY[5]);
                break;
            case 4:
                buttonXY[6] = myWatcher.getX();
                buttonXY[7] = myWatcher.getY();
                blueCoordLayer.setText(buttonXY[6]+":"+buttonXY[7]);
                break;
            case 5:
                buttonXY[8] = myWatcher.getX();
                buttonXY[9] = myWatcher.getY();
                orangeCoordLayer.setText(buttonXY[8]+":"+buttonXY[9]);
                break;

        }
    }

}

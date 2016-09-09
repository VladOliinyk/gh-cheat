package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        greenButton.addEventHandler(MOUSE_CLICKED, this);
        redButton.addEventHandler(MOUSE_CLICKED, this);
        yellowButton.addEventHandler(MOUSE_CLICKED, this);
        blueButton.addEventHandler(MOUSE_CLICKED, this);
        orangeButton.addEventHandler(MOUSE_CLICKED, this);
    }

    private int greenX = 0;
    private int greenY = 0;
    private int redX = 0;
    private int redY = 0;
    private int yellowX = 0;
    private int yellowY = 0;
    private int blueX = 0;
    private int blueY = 0;
    private int orangeX = 0;
    private int orangeY = 0;


    Watcher myWatcher;
    private int CURRENT_BUTTON = 0;

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
            System.out.println("Coords:\n" +
                    "     Green=" + greenX + ":" + greenY +
                    "     Red=" + redX + ":" + redY +
                    "     Yellow=" + yellowX + ":" + yellowY +
                    "     Blue=" + blueX + ":" + blueY +
                    "     Orange=" + orangeX + ":" + orangeY);
            updateCoordLables();
        }
    };

    private void updateCoordLables() {
        switch (CURRENT_BUTTON) {
            case 1:
                greenX = myWatcher.getX();
                greenY = myWatcher.getY();
                greenCoordLayer.setText(greenX+":"+greenY);
                break;
            case 2:
                redX = myWatcher.getX();
                redY = myWatcher.getY();
                redCoordLayer.setText(redX+":"+redY);
                break;
            case 3:
                yellowX = myWatcher.getX();
                yellowY = myWatcher.getY();
                yellowCoordLayer.setText(yellowX+":"+yellowY);
                break;
            case 4:
                blueX = myWatcher.getX();
                blueY = myWatcher.getY();
                blueCoordLayer.setText(blueX+":"+blueY);
                break;
            case 5:
                orangeX = myWatcher.getX();
                orangeY = myWatcher.getY();
                orangeCoordLayer.setText(orangeX+":"+orangeY);
                break;

        }
    }
}

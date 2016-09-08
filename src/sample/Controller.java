package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


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
    static Text coordsLabel;

    @FXML
    Text colorLabel;

    @FXML
    ImageView myBg;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myBg.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        greenButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        redButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        yellowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        blueButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        orangeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }

    @Override
    public void handle(MouseEvent event) {

        ImageView source = (ImageView) event.getSource();
        String id = source.getId();
        switch (source.getId()) {
            case "greenButton":
                greenButtonPressed();
                break;
            case "redButton":
                redButtonPressed();
                break;
            case "yellowButton":
                yellowButtonPressed();
                break;
            case "blueButton":
                blueButtonPressed();
                break;
            case "orangeButton":
                orangeButtonPressed();
                break;
        }
    }


    Watcher myWatcher;

    public static int greenX;
    public static int greenY;

    private void greenButtonPressed() {
        System.out.println("greenButtonPressed");
        if (myWatcher == null || !myWatcher.isWatching()) {
            colorLabel.setText("green");
            System.out.println("TRUE");
            myWatcher = new Watcher(1);
            myWatcher.start();
        }
    }

    private void redButtonPressed() {
        colorLabel.setText("red");
        System.out.println("redButtonPressed");

    }

    private void yellowButtonPressed() {
        colorLabel.setText("yellow");
        System.out.println("yellowButtonPressed");
    }

    private void blueButtonPressed() {
        colorLabel.setText("blue");
        System.out.println("blueButtonPressed");
    }

    private void orangeButtonPressed() {
        colorLabel.setText("orange");
        System.out.println("orangeButtonPressed");
    }

}

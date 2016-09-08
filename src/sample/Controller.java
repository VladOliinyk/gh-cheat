package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        greenButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        redButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        yellowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        blueButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        orangeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }

    @Override
    public void handle(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();
         switch (source.getId()) {
             case "greenButton" : greenButtonPressed(); break;
             case "redButton" : redButtonPressed(); break;
             case "yellowButton" : yellowButtonPressed(); break;
             case "blueButton" : blueButtonPressed(); break;
             case "orangeButton" : orangeButtonPressed(); break;
        }
    }

    private void greenButtonPressed() {
        System.out.println("greenButtonPressed");
    }

    private void redButtonPressed() {
        System.out.println("redButtonPressed");
    }

    private void yellowButtonPressed() {
        System.out.println("yellowButtonPressed");
    }

    private void blueButtonPressed() {
        System.out.println("blueButtonPressed");
    }

    private void orangeButtonPressed() {
        System.out.println("orangeButtonPressed");
    }

}

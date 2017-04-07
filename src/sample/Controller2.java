package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Controller2 {


    @FXML
    private Pane helpScenePane;

    @FXML
    private Button buttonHelpBack;

    @FXML
    public void initialize() {
        buttonHelpBack.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String switchScene = "./sample.fxml";
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource(switchScene));
                    Parent widget = loader.load();
                    helpScenePane.getChildren().clear();
                    helpScenePane.getChildren().add(widget);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @FXML
    public void onHelpBackClickMethod(ActionEvent actionEvent) {

    }
}

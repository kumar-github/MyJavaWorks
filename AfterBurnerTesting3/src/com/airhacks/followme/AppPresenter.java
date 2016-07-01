package com.airhacks.followme;

import com.airhacks.followme.hello.HelloView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author airhacks.com
 */
public class AppPresenter implements Initializable {

    @FXML
    AnchorPane north;

    @FXML
    AnchorPane south;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HelloView northView = new HelloView();
        north.getChildren().add(northView.getView());

        HelloView southView = new HelloView();
        south.getChildren().add(southView.getView());
    }
}

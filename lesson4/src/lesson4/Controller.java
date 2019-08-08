package lesson4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.applet.AudioClip;

public class Controller {
    @FXML
    public TextArea textArea;
    @FXML
    public TextField textField;

    AlsoSprichtTuring AST = new AlsoSprichtTuring();


    public void sendMSG(ActionEvent actionEvent) {
        textArea.appendText(textField.getText() + "\n");
        textField.setText("");
        textArea.appendText("        " + AST.alsoSprichtTuring() + "\n");
    }
}

package sample;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageWindow {
    // 展示一个小窗口，显示提示信息
    public static void display(String message)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("提示");
        window.setMinWidth(250);
        window.setMinHeight(250);
        Label label = new Label(message);
        label.setTextAlignment(TextAlignment.CENTER);

        VBox layout = new VBox(100);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}

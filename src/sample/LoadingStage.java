package sample;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Logger;

public class LoadingStage extends Stage {
    private ProgressIndicator progressIndicator;

    public LoadingStage(Stage primaryStage) throws IOException {
        Logger logger = sample.utils.Logger.getLogger();
        logger.info("初始化加载模块。。。");
        progressIndicator = new ProgressIndicator();

        this.initOwner(primaryStage);
        this.initStyle(StageStyle.UNDECORATED);
        this.initStyle(StageStyle.TRANSPARENT);
        this.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label("加载控件树中...");
        label.setTextFill(Color.BLACK);
        progressIndicator.setProgress(-1F);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setBackground(Background.EMPTY);
        vBox.getChildren().addAll(progressIndicator,label);
        Scene scene = new Scene(vBox);
        scene.setFill(null);
        this.setScene(scene);
    }
}

package teacherclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.ViewsNames;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(ViewsNames.FXML_MAIN_VIEW));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(ViewsNames.WINDOW_NAME_MAIN_VIEW);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
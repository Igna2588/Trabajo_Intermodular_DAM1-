import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gastos/ventana.fxml"));
        Scene scene = new Scene(loader.load(), 850, 620);
        scene.getStylesheets().add(getClass().getResource("/gastos/estilos.css").toExternalForm());
        stage.setTitle("Gastos Familiares");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
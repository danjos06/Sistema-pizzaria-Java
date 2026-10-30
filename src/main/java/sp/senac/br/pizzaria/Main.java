package sp.senac.br.pizzaria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sp.senac.br.pizzaria.factory.ConnectionFactory;
import sp.senac.br.pizzaria.factory.JpaFactory;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        JpaFactory.getEntityManager();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/inicial-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pizzaria");
        stage.setResizable(true);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
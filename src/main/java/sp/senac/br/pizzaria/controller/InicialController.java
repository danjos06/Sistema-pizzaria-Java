package sp.senac.br.pizzaria.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InicialController {
    public void sair(){ System.exit(0); }

    public void abrirCadCliente(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cliente-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setTitle("Pizzaria - Clientes");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirCadPizza(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pizza-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setTitle("Pizzaria - Pizzas");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void novoPedido(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pedido-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setTitle("Pizzaria - Novo Pedido");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verPedidos(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setTitle("Pizzaria - Clientes");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package sp.senac.br.pizzaria.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import jdk.jfr.DataAmount;
import sp.senac.br.pizzaria.model.Pizza;
import sp.senac.br.pizzaria.model.Tamanho;
import sp.senac.br.pizzaria.model.TipoPizza;

import java.net.URL;
import java.util.ResourceBundle;

public class PizzaController implements Initializable {
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfSabor;
    @FXML
    private ComboBox<TipoPizza> cbTipo;
    @FXML
    private ComboBox<Tamanho> cbTamanho;
    @FXML
    private TextField tfPreco;
    @FXML
    private TextArea taIngredientes;
    @FXML
    private TableView<Pizza> tbPizza;
    @FXML
    private TableColumn<Pizza, Long> colId;
    @FXML
    private TableColumn<Pizza, String> colSabor;
    @FXML
    private TableColumn<Pizza, Tamanho> colTamanho;

    public void salvar() {
    }

    public void excluir() {

    }

    public void limpar() {

    }

    public void buscar() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTamanho.getItems().setAll(Tamanho.values());
        cbTipo.getItems().setAll(TipoPizza.values());
    }
}

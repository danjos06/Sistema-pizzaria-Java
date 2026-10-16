package sp.senac.br.pizzaria.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import jdk.jfr.DataAmount;
import sp.senac.br.pizzaria.model.Cliente;
import sp.senac.br.pizzaria.model.Pizza;
import sp.senac.br.pizzaria.model.Tamanho;
import sp.senac.br.pizzaria.model.TipoPizza;
import sp.senac.br.pizzaria.service.PizzaService;

import java.net.URL;
import java.util.Optional;
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

    private ObservableList<Pizza> lista;
    private Pizza pizzaSelecionada;

    private PizzaService service = new PizzaService();

    private void configurarTabela(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSabor.setCellValueFactory(new PropertyValueFactory<>("sabor"));
        colTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
    }

    private void carregarTabela(){
        lista = FXCollections.observableArrayList(service.listar());
        tbPizza.setItems(lista);
        tbPizza.refresh();
    }

    public void salvar() {
        try {
            Pizza p;
            if(pizzaSelecionada == null){
                p = new Pizza();
            } else {
                p = pizzaSelecionada;
            }
            p.setTipo(cbTipo.getValue());
            p.setSabor(tfSabor.getText());
            p.setTamanho(cbTamanho.getValue());
            p.setIngredientes(taIngredientes.getText());
            p.setPreco(Double.parseDouble(tfPreco.getText()));

            service.salvar(p);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Pizza salva com sucesso!");
            alerta.setHeaderText("Sucesso");
            alerta.show();
            carregarTabela();
            limpar();
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText(e.getMessage());
            alerta.setHeaderText("Erro");
            alerta.show();
        }
    }

    public void excluir() {
        if(pizzaSelecionada == null){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Nenhuma pizza selecionada!");
            alerta.setHeaderText("Erro");
            alerta.show();
        } else {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmação de Exclusão");
            alerta.setHeaderText("Excluir pizza?");
            alerta.setContentText("Deseja realmente excluir a pizza selecionada?");

            alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> resultado = alerta.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
                service.excluir(pizzaSelecionada);
            }
        }
        carregarTabela();
    }

    public void limpar() {
        tfId.clear();
        tfPreco.clear();
        tfSabor.clear();
        taIngredientes.clear();
        cbTipo.setValue(null);
        cbTamanho.setValue(null);
        pizzaSelecionada = null;
    }

    public void buscar() {
        if(tfSabor.getText().isBlank()){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Digite um sabor para pesquisar");
            alerta.setHeaderText("Erro");
            alerta.show();
        } else {
            if (service.buscar(tfSabor.getText()) == null){
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setContentText("Nenhuma pizza desse sabor foi encontrada.");
                alerta.setHeaderText("Não encontrado");
                alerta.show();
            } else {
                lista = FXCollections.observableArrayList(service.buscar(tfSabor.getText()));
                tbPizza.setItems(lista);
                tbPizza.refresh();
            }
        }
    }

    private void listenerSelecao(){
        tbPizza.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
            pizzaSelecionada = newValue;
            if(pizzaSelecionada != null){
                tfId.setText(pizzaSelecionada.getId().toString());
                tfSabor.setText(pizzaSelecionada.getSabor());
                cbTipo.setValue(pizzaSelecionada.getTipo());
                cbTamanho.setValue(pizzaSelecionada.getTamanho());
                tfPreco.setText(String.valueOf(pizzaSelecionada.getPreco()));
                taIngredientes.setText(pizzaSelecionada.getIngredientes());
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTamanho.getItems().setAll(Tamanho.values());
        cbTipo.getItems().setAll(TipoPizza.values());
        configurarTabela();
        carregarTabela();
        listenerSelecao();
    }
}

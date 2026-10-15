package sp.senac.br.pizzaria.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sp.senac.br.pizzaria.model.Cliente;
import sp.senac.br.pizzaria.service.ClienteService;

import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTelefone;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfCpf;
    @FXML
    private DatePicker dpNascimento;
    @FXML
    private TextArea taEndereco;
    @FXML
    private TableView<Cliente> tbCliente;
    @FXML
    private TableColumn<Cliente, Long> colId;
    @FXML
    private TableColumn<Cliente, String> colNome;
    @FXML
    private TableColumn<Cliente, String> colTelefone;

    private ObservableList<Cliente> lista;
    private Cliente clienteSelecionado;

    private ClienteService service = new ClienteService();

    private void configurarTabela(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    }

    private void carregarTabela(){
        lista = FXCollections.observableArrayList(service.listar());
        tbCliente.setItems(lista);
        tbCliente.refresh();
    }

    public void salvar(){
        try {
            Cliente c;
            if(clienteSelecionado == null) {
                c = new Cliente();
            } else {
                c = clienteSelecionado;
            }
            c.setTelefone(tfTelefone.getText());
            c.setCpf(tfCpf.getText());
            c.setNome(tfNome.getText());
            c.setEndereco(taEndereco.getText());
            c.setNascimento(dpNascimento.getValue());
            // mandar para o service
            service.salvar(c);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Cliente salvo com sucesso!");
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

    public void excluir(){
        if(clienteSelecionado == null){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Nenhum cliente selecionado!");
            alerta.setHeaderText("Erro");
            alerta.show();
        } else {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmação de Exclusão");
            alerta.setHeaderText("Excluir cliente?");
            alerta.setContentText("Deseja realmente excluir o cliente selecionado?");

            alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> resultado = alerta.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
                service.excluir(clienteSelecionado);
            }
        }
        carregarTabela();
    }

    private void listenerSelecao(){
        tbCliente.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
            clienteSelecionado = newValue;
            if(clienteSelecionado != null){
                tfId.setText(clienteSelecionado.getId().toString());
                tfTelefone.setText(clienteSelecionado.getTelefone());
                tfNome.setText(clienteSelecionado.getNome());
                tfCpf.setText(clienteSelecionado.getCpf());
                taEndereco.setText(clienteSelecionado.getEndereco());
                dpNascimento.setValue(clienteSelecionado.getNascimento());
            }
        });
    }

    public void limpar(){
        tfId.clear();
        tfNome.clear();
        tfCpf.clear();
        tfTelefone.clear();
        taEndereco.clear();
        dpNascimento.setValue(null);
        clienteSelecionado = null;
    }

    public void buscar() {
        if(tfTelefone.getText().isBlank()){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Digite um telefone para pesquisar");
            alerta.setHeaderText("Erro");
            alerta.show();
        } else {
            if (service.buscar(tfTelefone.getText()) == null){
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setContentText("Nenhum cliente foi encontrado com esse telefone.");
                alerta.setHeaderText("Não encontrado");
                alerta.show();
            } else {
                lista = FXCollections.observableArrayList(service.buscar(tfTelefone.getText()));
                tbCliente.setItems(lista);
                tbCliente.refresh();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabela();
        carregarTabela();
        listenerSelecao();
    }
}

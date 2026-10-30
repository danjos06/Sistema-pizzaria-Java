package sp.senac.br.pizzaria.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sp.senac.br.pizzaria.model.*;
import sp.senac.br.pizzaria.service.PedidoService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PedidoController implements Initializable {
    @FXML
    private ComboBox<FormaPagamento> cbPagamento;
    @FXML
    private TextField tfTelefone, tfCliente, tfIngredientes, tfEndereco;
    @FXML
    private ListView<Pizza> lvPizza;
    @FXML
    private ListView<ItemPedido> lvItem;
    @FXML
    private Spinner<Integer> spQtd;
    @FXML
    private Label lbTotal;

    private Cliente cliente;
    private ObservableList<Pizza> listaPizza;
    private ObservableList<ItemPedido> listaItem;
    private PedidoService service = new PedidoService();
    private List<ItemPedido> itens = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbPagamento.getItems().setAll(FormaPagamento.values());
        buscarPizza();
        listaItem = FXCollections.observableArrayList(itens);
        lvItem.setItems(listaItem);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        spQtd.setValueFactory(valueFactory);
    }

    public void buscarPizza(){
        listaPizza = FXCollections.observableArrayList(service.buscarPizza(tfIngredientes.getText()));
        lvPizza.setItems(listaPizza);
    }

    public void addItem(){
        int qtd = spQtd.getValue();
        Pizza pizza;
        if((pizza = lvPizza.getSelectionModel().getSelectedItem()) != null){
            ItemPedido item = new ItemPedido();
            item.setPizza(pizza);
            item.setQtd(qtd);
            item.setPreco(pizza.getPreco());
//            boolean add = false;
//            for(ItemPedido itemL : listaItem){
//                if(itemL.equals(item)){
//                    itemL.setQtd(itemL.getQtd() + 1);
//                    add = true;
//                }
//            }
//            if(!add) {
//                listaItem.add(item);
//            }
            listaItem.add(item);
            atualizaTotal();
        }
    }

    public void removeItem(){
        ItemPedido item = lvItem.getSelectionModel().getSelectedItem();
        if(item != null) {
            listaItem.remove(lvItem.getSelectionModel().getSelectedItem());
            atualizaTotal();
        }
    }

    public void atualizaTotal(){
        double total = 0;
        for(ItemPedido item : lvItem.getItems()){
            total += item.getTotal();
        }
        lbTotal.setText(String.format("R$ %5.2f", total));
    }

    public void buscarCliente(){
        if(!tfTelefone.getText().isBlank()){
            try {
                cliente = service.buscarCliente(tfTelefone.getText());
                tfCliente.setText(cliente.getNome());
                tfEndereco.setText(cliente.getEndereco());
            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setContentText("Nenhum cliente foi encontrado com esse telefone.");
                alerta.setHeaderText("Não encontrado");
                alerta.show();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Digite um telefone para pesquisar");
            alerta.setHeaderText("Erro");
            alerta.show();
        }
    }

    public void gerarPedido(){
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setItens(listaItem);
        pedido.setFormaPagamento(cbPagamento.getValue());
        service.inserir(pedido);
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText("Pedido gerado com sucesso!\nNúmero do pedido: "  + pedido.getNumero());
        alerta.setHeaderText("Sucesso");
        alerta.show();
        limparCampos();
    }

    public void limparCampos(){
        cliente = null;
        tfCliente.clear();
        tfTelefone.clear();
        tfEndereco.clear();
        cbPagamento.setValue(null);
        listaItem.clear();
        lvPizza.getSelectionModel().clearSelection();
        tfIngredientes.clear();
        buscarPizza();
        atualizaTotal();
    }
}

package sp.senac.br.pizzaria.service;

import sp.senac.br.pizzaria.model.Cliente;
import sp.senac.br.pizzaria.model.ItemPedido;
import sp.senac.br.pizzaria.model.Pedido;
import sp.senac.br.pizzaria.model.Pizza;
import sp.senac.br.pizzaria.repository.ClienteRepository;
import sp.senac.br.pizzaria.repository.PedidoRepository;
import sp.senac.br.pizzaria.repository.PizzaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoService {
    private final ClienteRepository repCliente = new ClienteRepository();
    private final PizzaRepository repPizza = new PizzaRepository();
    private final PedidoRepository repPedido = new PedidoRepository();

    public Cliente buscarCliente(String telefone){
        return repCliente.buscarPorTel(telefone);
    }

    public List<Pizza> buscarPizza(String valor){
        return repPizza.buscarPizza(valor);
    }

    public void inserir(Pedido p){
        p.setData(LocalDateTime.now());
        int tempo = 30;
        for(ItemPedido item : p.getItens()){
            tempo += item.getQtd() * 5;
            item.setPedido(p);
        }
        p.setPrazoEntrega(p.getData().plusMinutes(tempo));
        repPedido.inserir(p);
    }
}

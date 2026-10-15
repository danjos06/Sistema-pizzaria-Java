package sp.senac.br.pizzaria.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Pedido {
    private long numero;
    private Cliente cliente;
    private LocalDateTime prazoEntrega;
    private LocalDateTime data;
    private FormaPagamento formaPagamento;
    private List<ItemPedido> itens;

    public double getTotal(){
        double total = 0;
        for(ItemPedido item: itens){
            total += item.getTotal();
        }
        return total;
    }
}

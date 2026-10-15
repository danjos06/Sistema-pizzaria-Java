package sp.senac.br.pizzaria.model;

import lombok.Data;

@Data
public class ItemPedido {
    private Long id;
    private Pizza pizza;
    private int qtd;
    private double preco;
    private Pedido pedido;

    public double getTotal(){
        return qtd * preco;
    }
}

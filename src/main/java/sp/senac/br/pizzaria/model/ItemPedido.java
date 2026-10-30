package sp.senac.br.pizzaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pizza pizza;

    private int qtd;
    private double preco;

    @ManyToOne
    private Pedido pedido;

    public double getTotal(){
        return qtd * preco;
    }

    @Override
    public String toString(){
        return String.format("Qtd: %s - Sabor: %s", getQtd(), pizza.getSabor());
    }
}

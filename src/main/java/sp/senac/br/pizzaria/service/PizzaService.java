package sp.senac.br.pizzaria.service;

import sp.senac.br.pizzaria.dao.ClienteDao;
import sp.senac.br.pizzaria.dao.InterfaceDao;
import sp.senac.br.pizzaria.model.Cliente;
import sp.senac.br.pizzaria.model.Pizza;
import sp.senac.br.pizzaria.repository.PizzaRepository;

import java.util.List;

public class PizzaService {
    private final InterfaceDao<Pizza> rep = new PizzaRepository();

    public void salvar(Pizza pizza){
        if(pizza.getSabor().isBlank()){
            throw new RuntimeException("O sabor é obrigatório");
        } else if(pizza.getPreco() == 0){
            throw new RuntimeException("O preço é obrigatório");
        } else if(pizza.getTipo() == null){
            throw new RuntimeException("O tipo é obrigatório");
        } else if(pizza.getTamanho() == null){
            throw new RuntimeException("O tamanho é obrigatório.");
        } else {
            if(pizza.getId() == null) {
                rep.inserir(pizza);
            } else {
                rep.alterar(pizza);
            }
        }
    }

    public List<Pizza> listar(){
        return rep.listar();
    }

    public void excluir(Pizza pizza){
        rep.excluir(pizza.getId());
    }

    public List<Pizza> buscar(String sabor){
        return rep.buscar(sabor);
    }
}

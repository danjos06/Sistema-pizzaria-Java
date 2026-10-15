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
        if(pizza.getTelefone().isBlank()){
            throw new RuntimeException("O telefone é obrigatório");
        } else if(cliente.getNome().isBlank()){
            throw new RuntimeException("O nome é obrigatório");
        } else if(cliente.getEndereco().isBlank()){
            throw new RuntimeException("O endereço é obrigatório.");
        } else {
            if(pizza.getId() == null) {
                rep.inserir(pizza);
            } else {
                rep.alterar(pizza);
            }
        }
    }

    public List<Cliente> listar(){
        return rep.listar();
    }

    public void excluir(Cliente cliente){
        rep.excluir(cliente.getId());
    }

    public List<Cliente> buscar(String telefone){
        return rep.buscar(telefone);
    }
}

package sp.senac.br.pizzaria.service;

import sp.senac.br.pizzaria.dao.ClienteDao;
import sp.senac.br.pizzaria.dao.InterfaceDao;
import sp.senac.br.pizzaria.model.Cliente;

import java.util.List;

public class ClienteService {
    private final InterfaceDao<Cliente> dao = new ClienteDao();

    public void salvar(Cliente cliente){
        if(cliente.getTelefone().isBlank()){
            throw new RuntimeException("O telefone é obrigatório");
        } else if(cliente.getNome().isBlank()){
            throw new RuntimeException("O nome é obrigatório");
        } else if(cliente.getEndereco().isBlank()){
            throw new RuntimeException("O endereço é obrigatório.");
        } else {
            if(cliente.getId() == null) {
                dao.inserir(cliente);
            } else {
                dao.alterar(cliente);
            }
        }
    }

    public List<Cliente> listar(){
        return dao.listar();
    }

    public void excluir(Cliente cliente){
        dao.excluir(cliente.getId());
    }

    public List<Cliente> buscar(String telefone){
        return dao.buscar(telefone);
    }
}

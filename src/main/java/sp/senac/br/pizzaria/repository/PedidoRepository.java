package sp.senac.br.pizzaria.repository;

import jakarta.persistence.EntityManager;
import sp.senac.br.pizzaria.dao.InterfaceDao;
import sp.senac.br.pizzaria.factory.JpaFactory;
import sp.senac.br.pizzaria.model.Pedido;
import sp.senac.br.pizzaria.model.Pizza;

import java.util.List;

public class PedidoRepository implements InterfaceDao<Pedido> {
    @Override
    public void inserir(Pedido objeto) {
        try(EntityManager manager = JpaFactory.getEntityManager()){
            manager.getTransaction().begin();
            manager.persist(objeto);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alterar(Pedido objeto) {

    }

    @Override
    public void excluir(Long id) {

    }

    @Override
    public List<Pedido> listar() {
        return List.of();
    }

    @Override
    public List<Pedido> buscar(String valor) {
        return List.of();
    }
}

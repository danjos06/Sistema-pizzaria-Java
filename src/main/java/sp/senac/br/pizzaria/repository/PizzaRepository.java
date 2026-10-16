package sp.senac.br.pizzaria.repository;

import jakarta.persistence.EntityManager;
import sp.senac.br.pizzaria.dao.InterfaceDao;
import sp.senac.br.pizzaria.factory.JpaFactory;
import sp.senac.br.pizzaria.model.Pizza;

import java.util.List;

public class PizzaRepository implements InterfaceDao<Pizza> {

    @Override
    public void inserir(Pizza objeto) {
        try(EntityManager manager = JpaFactory.getEntityManager()){
            manager.getTransaction().begin();
            manager.persist(objeto);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alterar(Pizza objeto) {
        try(EntityManager manager = JpaFactory.getEntityManager()){
            manager.getTransaction().begin();
            manager.merge(objeto);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluir(Long id) {
        try(EntityManager manager = JpaFactory.getEntityManager()){
            Pizza p = manager.find(Pizza.class, id);
            manager.getTransaction().begin();
            manager.remove(p);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Pizza> listar() {
        try(EntityManager manager = JpaFactory.getEntityManager()){
            return manager.createQuery("SELECT p FROM Pizza p ORDER BY p.sabor", Pizza.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Pizza> buscar(String valor) {
        try (EntityManager manager = JpaFactory.getEntityManager()) {
            return manager.createQuery(
                            "SELECT p FROM Pizza p WHERE LOWER(p.sabor) LIKE :sabor ORDER BY p.sabor",
                            Pizza.class)
                    .setParameter("sabor", "%" + valor.toLowerCase() + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

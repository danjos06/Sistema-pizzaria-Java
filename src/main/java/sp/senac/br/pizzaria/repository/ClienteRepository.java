package sp.senac.br.pizzaria.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sp.senac.br.pizzaria.factory.JpaFactory;
import sp.senac.br.pizzaria.model.Cliente;

public class ClienteRepository {
    public Cliente buscarPorTel(String telefone){
        try (EntityManager manager = JpaFactory.getEntityManager()){
            TypedQuery<Cliente> query = manager.createQuery("FROM Cliente c WHERE c.telefone = :tel", Cliente.class);
            query.setParameter("tel", telefone);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

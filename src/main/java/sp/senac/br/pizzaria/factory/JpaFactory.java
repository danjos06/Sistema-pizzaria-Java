package sp.senac.br.pizzaria.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaFactory {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("pizzaria");

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}

package sample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
    static EntityManager entityManager;

    private Connection(){

    }

    public static EntityManager getConnection(){
        if(entityManager == null) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("App");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }
}

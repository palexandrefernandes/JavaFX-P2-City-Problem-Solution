package repository;

import models.Admin;
import models.Issue;
import org.hibernate.Criteria;
import sample.Connection;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class AdminRepository {
    private EntityManager entityManager;

    public AdminRepository(){
        entityManager = Connection.getConnection();
    }

    public boolean save(Admin admin){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(admin);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Admin login(String username, String password) {
        Admin temp = null;

        try{
            temp = entityManager.createQuery("Select a from Admin a where a.email = ?1 and a.password = ?2", Admin.class).setParameter(1, username).setParameter(2, password).getSingleResult();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Not found!");
        }

        return temp;
    }

}

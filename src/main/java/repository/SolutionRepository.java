package repository;

import models.Issue;
import models.Solution;
import sample.Connection;

import javax.persistence.EntityManager;
import java.util.List;

public class SolutionRepository {
    private EntityManager entityManager;

    public SolutionRepository(){
        entityManager = Connection.getConnection();
    }

    public boolean save(Solution solution){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(solution);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Solution> findAllOpen(){
        return entityManager.createQuery("Select s from Solution s where s.closedAt = null").getResultList();
    }

    public List<Solution> findAllClosed(){
        return entityManager.createQuery("Select s from Solution s where s.closedAt != null").getResultList();
    }


}

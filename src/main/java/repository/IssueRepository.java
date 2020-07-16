package repository;

import models.Issue;
import sample.Connection;

import javax.persistence.EntityManager;
import java.util.List;

public class IssueRepository {
    private EntityManager entityManager;

    public IssueRepository(){
        entityManager = Connection.getConnection();
    }

    public List<Issue> findAllPending(){
        return entityManager.createQuery("Select i from Issue i where i.solution = null and i.denied = false").getResultList();
    }

    public boolean save(Issue issue){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(issue);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deny(Issue issue) {
        issue.setDenied(true);
        save(issue);
    }
}

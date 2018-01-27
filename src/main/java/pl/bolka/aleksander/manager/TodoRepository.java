package pl.bolka.aleksander.manager;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@ApplicationScoped
public class TodoRepository {

    @PersistenceContext
    private EntityManager entityManager;



}

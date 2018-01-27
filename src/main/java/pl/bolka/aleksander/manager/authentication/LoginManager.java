package pl.bolka.aleksander.manager.authentication;

import pl.bolka.aleksander.model.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginManager implements Serializable{

    @PersistenceContext
    private EntityManager em;

    public User getUserByLogin(String login){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        query.where(cb.equal(root.get("login"),login));
        try{
            return em.createQuery(query).getSingleResult();
        }catch (Exception ex){
            return null;
        }
    }

}

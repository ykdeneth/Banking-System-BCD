package com.ydm.j2ee.ejb.bean;

import com.ydm.j2ee.core.model.*;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import com.ydm.j2ee.core.service.UService;

import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

@Stateless
public class USessionBean implements UService {

    @PersistenceContext
    private EntityManager em;


    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) {

            List<User> users = em.createNamedQuery("User.findByEmail", User.class)
                    .setParameter("email", email)
                    .getResultList();
//            User u = users.isEmpty() ? null : users.get(0);

            return users.isEmpty() ? null : users.get(0);

    }

    @Override
    public void addUser(User user) {
        em.persist(user);
        System.out.println("User " + user.getName() + " added to database successfully");
    }

    @PermitAll
    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public void deleteUser(User user) {
//        em.remove(user);
        try {
            em.remove(user);
        } catch (EJBAccessException ex) {
            // Log the event
            System.out.println("Unauthorized loan approval attempt: " + ex.getMessage());
            // Notify security team or display an error to the user
        }

    }

    @Override
    public boolean validate(String email, String password) {
       try {
           User user = em.createNamedQuery("User.findByEmail", User.class)
                   .setParameter("email", email).getSingleResult();

           return user != null && user.getPassword().equals(password) && user.getVerifyState().equals(VerifyState.VERIFIED_ACCOUNT) ;
       } catch (Exception e) {
           return false;
       }

        ///

//        User u = em.createNamedQuery("User.findByEmailAndPassword", User.class)
//                .setParameter("email", email)
//                .setParameter("password", password)
//                .getSingleResult();
//
//        return u!=null;

    }

    @Override
    public boolean validAccount(String email, String accountNo) {
        System.out.println("email: " + email + " accountNo: " + accountNo);
        try {
            Account user = em.createNamedQuery("Account.findByAccountAndEmail", Account.class)
                    .setParameter("email", email)
                    .setParameter("accountNo2", accountNo)
                    .getSingleResult();
//        return user != null && user.getAccountNo().equals(accountNo);
            return user != null;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<User> getUserByEmail2(String email) {
        return em.createNamedQuery("UserAll.findByEmail", User.class)
                .setParameter("email", email)
                .getResultList();
    }

    @RolesAllowed("ADMIN")
    @Override
    public List<User> allUsers() {
        return em.createNamedQuery("UserAll.getAllUsers", User.class)
                .setParameter("userType", UserType.ADMIN)
                .getResultList();

    }

    @Override
    public boolean getUserById2(Long id) {

        User user = em.find(User.class, id);
        if (user.getStatus().equals(Status.ACTIVE)) {
            user.setStatus(Status.INACTIVE);
            em.merge(user);
            return true;
        }else if (user.getStatus().equals(Status.INACTIVE)) {
            user.setStatus(Status.ACTIVE);
            em.merge(user);
            return true;
        }

        return false;
    }

}

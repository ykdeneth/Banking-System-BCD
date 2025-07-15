package com.ydm.j2ee.ejb.bean;

import com.ydm.j2ee.core.model.Account;
import com.ydm.j2ee.core.model.Status;
import com.ydm.j2ee.core.model.VerifyState;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import com.ydm.j2ee.core.model.User;
import com.ydm.j2ee.core.service.UService;

import java.util.List;

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

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void deleteUser(User user) {
        em.remove(user);
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

}

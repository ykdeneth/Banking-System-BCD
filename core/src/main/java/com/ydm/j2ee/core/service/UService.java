package com.ydm.j2ee.core.service;

import com.ydm.j2ee.core.model.User;
import jakarta.ejb.Remote;

import java.util.List;


@Remote
public interface UService {
    User getUserById(Long id);
    User getUserByEmail(String email);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    boolean validate(String email, String password);
    boolean validAccount(String email, String accountNo);
    List<User> getUserByEmail2(String email);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.repository;

import com.shaheen.model.User;
import javax.persistence.EntityManager;

/**
 *
 * @author lts
 */
public class UserRepoImpl extends CrudImpl<User> implements UserRepo {

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        EntityManager entityManager = getEntityManager();
        User user = (User) entityManager.createQuery("from User u where u.username =:username and u.password =:password")
                .setParameter("username", username)
                .setParameter("password", password).getSingleResult();
        entityManager.close();
        return user;
    }
}

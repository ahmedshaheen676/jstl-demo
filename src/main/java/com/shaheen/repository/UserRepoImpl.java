/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.repository;

import com.shaheen.model.User;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author lts
 */
public class UserRepoImpl extends CrudImpl<User> implements UserRepo {

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        System.out.println(this.getClass().getName() + " findByUsernameAndPassword "+username + " " + password);
        EntityManager entityManager = getEntityManager();
        User user = null ;
        try{
            user  = (User) entityManager.createQuery("from User u where u.username =:username and u.password =:password")
                .setParameter("username", username)
                .setParameter("password", password).getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
        }       
        return user;
    }
}

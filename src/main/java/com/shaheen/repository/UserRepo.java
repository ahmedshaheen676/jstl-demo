/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.repository;

import com.shaheen.model.User;

/**
 *
 * @author lts
 */
public interface UserRepo extends Crud<User>{
    User findByUsernameAndPassword(String username,String password);
}

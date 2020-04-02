/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.repository;

import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author lts
 * @param <T>
 */
public interface Crud<T> {

    List<T> findAll();

    T findById(int id);

    T save(T t);

    void delete(T t);
}

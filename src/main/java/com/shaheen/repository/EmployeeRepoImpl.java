/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.repository;

import com.shaheen.model.Employee;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author lts
 */
public class EmployeeRepoImpl extends CrudImpl<Employee> implements EmployeeRepo {

    @Override
    public List<Employee> findByName(String name) {

        EntityManager entityManager = getEntityManager();
        System.out.println("entityManager =" + entityManager);

        List<Employee> resultList = entityManager.createQuery("from Employee e where e.fristName =:firstname OR e.lastName = :lastname")
                .setParameter("firstname", name)
                .setParameter("lastname", name)
                .getResultList();
        entityManager.close();
        System.out.println("entityManager =" + entityManager);
        return resultList;
    }

}

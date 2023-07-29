package com.dev.expensetrackerapi.repository;

import com.dev.expensetrackerapi.ehandler.EtAuthException;
import com.dev.expensetrackerapi.entity.User;

public interface UserRepository {

    //creating user
    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    //find by email and password, returns user
    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    //to check if email exists
    Integer getCountByEmail(String email);

    //To find user by userId
    User findById(Integer userId);

}

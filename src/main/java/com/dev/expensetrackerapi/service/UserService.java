package com.dev.expensetrackerapi.service;

import com.dev.expensetrackerapi.ehandler.EtAuthException;
import com.dev.expensetrackerapi.entity.User;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;
}

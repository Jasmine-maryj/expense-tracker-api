package com.dev.expensetrackerapi.service;

import com.dev.expensetrackerapi.ehandler.EtAuthException;
import com.dev.expensetrackerapi.entity.User;
import com.dev.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if(email != null)email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if(email != null) email = email.toLowerCase();

        if(!pattern.matcher(email).matches())throw  new EtAuthException("Invalid email format");

        Integer count = userRepository.getCountByEmail(email);

        if(count > 0)throw  new EtAuthException("Email already in user");

        Integer userId = userRepository.create(firstName, lastName, email, password);

        return userRepository.findById(userId);
    }
}

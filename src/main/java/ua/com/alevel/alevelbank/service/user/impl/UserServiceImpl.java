package ua.com.alevel.alevelbank.service.user.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.alevelbank.persistence.repository.user.UserRepository;
import ua.com.alevel.alevelbank.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
}

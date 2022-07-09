package ru.job4j.forum.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserRepository;

import java.util.Optional;

@ThreadSafe
@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    public Optional<User> findUserByEmailAndPwd(User user) {
        return users.findUserByEmailAndPwd(user.geteMail(), user.getPassword());
    }

    public Optional<User> findUserByEmail(User user) {
        return users.findUserByEmail(user.geteMail());
    }

    public void addUser(User user) {
        users.save(user);
    }
}

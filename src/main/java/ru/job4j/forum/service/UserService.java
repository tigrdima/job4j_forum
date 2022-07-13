package ru.job4j.forum.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
public class UserService {
    private final UserRepository users;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository users, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findUser(String eMail) {
        return users.findByEmail(eMail);
    }

    public void addUser(User user) {
        users.save(user);
    }

    public List<User> findAllUsers() {
        List<User> usersList = new ArrayList<>();
        users.findAll().forEach(usersList::add);
        return usersList;
    }

    public Optional<User> findUserById(int id) {
        return users.findById(id);
    }

    public void enabledUser(int id) {
        User rsl = users.findById(id).get();
        rsl.setEnabled(!rsl.getEnabled());
        users.save(rsl);
    }

    public void deleteUser(int id) {
        users.deleteById(id);
    }

    public void save(User user) {
        users.save(user);
    }

    public void editAuthorityUser(int userId, int authorityId) {
        User rsl = users.findById(userId).get();
        rsl.setAuthority(authorityRepository.findById(authorityId).get());
        users.save(rsl);
    }
}

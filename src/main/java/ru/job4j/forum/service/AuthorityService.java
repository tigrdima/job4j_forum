package ru.job4j.forum.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.repository.AuthorityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ThreadSafe
@Controller
public class AuthorityService {
    private final AuthorityRepository authority;

    public AuthorityService(AuthorityRepository authority) {
        this.authority = authority;
    }

    public Optional<Authority> findAuthority(String role) {
        return authority.find(role);
    }

    public List<Authority> findAllAuthorities() {
        List<Authority> authorities = new ArrayList<>();
        authority.findAll().forEach(authorities::add);
        return authorities;
    }
}

package ru.job4j.forum.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.User;

import java.util.Optional;

@ThreadSafe
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("select u from User u where u.eMail = ?1 and u.password = ?2")
    Optional<User> findUserByEmailAndPwd(String eMail, String password);

    @Query("select u from User u where u.eMail = ?1")
    Optional<User> findUserByEmail(String eMail);
}

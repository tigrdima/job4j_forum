package ru.job4j.forum.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.forum.model.User;

import java.util.Optional;

@ThreadSafe
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("from User u where u.eMail = :uEmail")
    Optional<User> findByEmail(@Param("uEmail") String eMail);
}

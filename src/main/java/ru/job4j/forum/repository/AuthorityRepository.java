package ru.job4j.forum.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.forum.model.Authority;

import java.util.Optional;

@ThreadSafe
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    @Query("from Authority a where a.authority = :ruRole")
    Optional<Authority> find(@Param("ruRole")String role);
}

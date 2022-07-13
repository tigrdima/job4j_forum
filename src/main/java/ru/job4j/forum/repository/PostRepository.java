package ru.job4j.forum.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.forum.model.Post;

@ThreadSafe
public interface PostRepository extends CrudRepository<Post, Integer> {
    @Query("select p from Post p")
    Iterable<Post> findAll(Sort sort);

    @Query("select distinct p from Post p join fetch p.user u where u.id = :pUser")
    Iterable<Post> findPostsByUserId(@Param("pUser") int id);
}
